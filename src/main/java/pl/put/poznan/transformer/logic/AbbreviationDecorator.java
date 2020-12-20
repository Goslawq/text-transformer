package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/***
 * Class that shortens words to their abbreviations and extends them to full form.
 * Takes boolean 'mode' attribute which specify operation that should be performed:
 * mode = true - extend abbreviations
 * mode = false - shorten words to abbreviations
 * The dictionary file (user_dictionary.txt), containing words to shorten/extend
 * is placed in the main directory of this program - feel free to edit it so that the program lives up to your expectations.
 * All words in user_dictionary should be in lowercase - replacement will be performed automatically also with their first-capital-letter form.
 * It extends TextInterfaceDecorator.
 * @author Szymon
 */

public class AbbreviationDecorator extends TextInterfaceDecorator{

    private static final Logger logger = LoggerFactory.getLogger(AbbreviationDecorator.class);
    private final boolean mode;


    public AbbreviationDecorator (TextInterface text_input,boolean mode)
    {
        super(text_input);
        this.mode=mode;
    }


    /***
     * The method that performs desired transformation.
     * There are two modes avaliable.
     * @return Transformed String
     */

    @Override
    public String getTransformedText() {
        logger.debug("Entered Abbreviation method, var mode="+mode);
        String text = super.getTransformedText();

        String dict_path="./user_dictionary.txt";
        logger.debug("dictionary path="+dict_path);
        Map<String, String> words_map = new HashMap<String, String>();

        try {
            File myObj = new File(dict_path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] result = data.split(";");
                if(mode)words_map.put(result[0], result[1]);
                else words_map.put(result[1], result[0]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            logger.debug("An error occured while opening file.");
        }

        for (Map.Entry<String, String> entry : words_map.entrySet()) {

            String from =entry.getKey();
            String to =entry.getValue();
            String _from=from.substring(0, 1).toUpperCase() + from.substring(1);
            String _to=to.substring(0, 1).toUpperCase() + to.substring(1);

            //if mode==True (extend)
            if(mode){
                //replace
                text = text.replace(from,to);
                text = text.replace(_from,_to);

            }
            //mode==False (shorten)
            else
            {
                //replace
                text = text.replace(from,to);
                text = text.replace(_from,_to);
            }

        }
        return text;
    }

}