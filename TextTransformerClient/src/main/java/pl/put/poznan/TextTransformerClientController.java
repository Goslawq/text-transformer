package pl.put.poznan;

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

import java.util.Locale;

public class TextTransformerClientController {
    public ListView<String> leftList;
    public TextArea inputText;
    public ListView rightList;
    public Button transformButton;
    public TextArea outputText;
    public Button addButton;
    public Button removeButton;
    public TextArea IP;
    private String [] availableTransformations={"Capitalize","Invert","Expand"};
    public void initialize() {
        inputText.setText("Startowy tekst");

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

    @FXML protected void transform(ActionEvent event){
        System.out.println(inputText.getText());
        outputText.setText(inputText.getText()+"?transformations="+leftList.getItems().toString());
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
}
