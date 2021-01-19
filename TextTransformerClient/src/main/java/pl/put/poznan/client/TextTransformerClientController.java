package pl.put.poznan.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.Locale;
import static javax.swing.JOptionPane.showMessageDialog;


public class TextTransformerClientController {
    public ListView<String> leftList;
    public TextArea inputText;
    public ListView rightList;
    public Button transformButton;
    public TextArea outputText;
    public Button addButton;
    public Button removeButton;
    public TextArea IP;
    public Button getTransformationsButton;
    private String [] availableTransformations={};
    public void initialize() {
        inputText.setText("Startowy tekst");
        transformButton.setDisable(true);

        ObservableList<String> right=FXCollections.observableArrayList(availableTransformations);
        rightList.setItems(right);

        rightList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    leftList.getItems().add((String) rightList.getSelectionModel().getSelectedItem());
                }
            }
        });
        leftList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    int selIndex = leftList.getSelectionModel().getSelectedIndex();
                    if(selIndex!=-1) {
                        leftList.getItems().remove(selIndex);
                    }
                }
            }
        });



    }
    private String getSelectedTransforms(){
        String transforms="";
        transforms=leftList.getItems().toString();
        transforms=transforms.replaceAll(" ","");
        transforms=transforms.substring(1,transforms.length()-1);
        return transforms;
    }

    public void connectionErrorAlert(String text){
        JOptionPane.showMessageDialog(null, text);
    }

    @FXML protected void transform(ActionEvent event){
        URL url = null;
        try {
            String inputTextToSend=inputText.getText();
            inputTextToSend=inputTextToSend.replaceAll("[\n]"," ");
            inputTextToSend=URLEncoder.encode(inputTextToSend, StandardCharsets.UTF_8.toString());
            inputTextToSend=inputTextToSend.replaceAll("[+]","%20");

            url=new URL("http",IP.getText(),8080,"/"+inputTextToSend+"?transforms="+getSelectedTransforms());
            System.out.println(url.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(500);
            con.setReadTimeout(500);

            int status = con.getResponseCode();

            if(status==200){
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),StandardCharsets.UTF_8));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                String response = content.toString();
                outputText.setText(response);
                in.close();
            }else{
                connectionErrorAlert("Connection failed");
            }
            con.disconnect();
        } catch (IOException e) {
           connectionErrorAlert("Connection error");
        }
    }

    public void addItem(ActionEvent actionEvent) {
        String sel = (String) rightList.getSelectionModel().getSelectedItem();
        //System.out.println(sel);
        if (sel!=null)
            leftList.getItems().add(sel);
    }

    public void removeItem(ActionEvent actionEvent) {
        int selIndex = leftList.getSelectionModel().getSelectedIndex();
        if(selIndex!=-1){
            leftList.getItems().remove(selIndex);
        }
    }
    public void moveUp(){
        int index=leftList.getSelectionModel().getSelectedIndex();
        ObservableList<String> test = leftList.getItems();
        if(index>0){
            String old_up=test.get(index-1);
            String old_down=test.get(index);
            test.set(index-1,old_down);
            test.set(index,old_up);
            leftList.setItems(test);
            leftList.getSelectionModel().select(index-1);
        }
    }
    public void moveDown(){
        int index=leftList.getSelectionModel().getSelectedIndex();
        ObservableList<String> test = leftList.getItems();
        if(index<test.size()-1){
            String old_up=test.get(index);
            String old_down=test.get(index+1);
            test.set(index,old_down);
            test.set(index+1,old_up);
            leftList.setItems(test);
            leftList.getSelectionModel().select(index+1);
        }
    }

    public void getAvailiabeTransformations(ActionEvent actionEvent) {
        URL url = null;
        try {
            url = new URL("http://"+IP.getText()+":8080/transforms/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(500);
            con.setReadTimeout(500);
            int status = con.getResponseCode();
            if(status==200){
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                String transformations= content.toString();
                String [] tansf_array=transformations.split(",");
                rightList.getItems().clear();
                for (String t: tansf_array) {
                    rightList.getItems().add(t);
                }
                in.close();
                transformButton.setDisable(false);
                getTransformationsButton.setDisable(true);
                IP.setEditable(false);
            }else{
                connectionErrorAlert("Connection failed");
            }
            con.disconnect();
        }  catch (IOException e) {
            connectionErrorAlert("Connection error");
        }
    }
}
