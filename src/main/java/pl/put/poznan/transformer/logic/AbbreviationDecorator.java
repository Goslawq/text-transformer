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
 * mode = true -> extend abbreviations
 * mode = false -> shorten words to abbreviations
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

            //if mode==True (extend)
            if(mode){
                //escape dot character and add boundaries, \W in regex means [^a-zA-Z0-9_] (to prevent replacements such as 'drużyna'->'doktorużyna')
                String from_a1="[\\W]"+from.replace(".","\\.")+"[\\W]";
                String to_a1= " "+to+" ";

                //replace
                text = text.replaceAll(from_a1,to_a1);
                logger.debug(from_a1+"--->"+ to_a1);

                //escape dot, add boundaries and change first character to upper
                String from_b1 = from.substring(0, 1).toUpperCase() + from.substring(1);
                from_b1="[\\W]"+from_b1.replace(".","\\.")+"[\\W]";

                String to_b1=" "+to.substring(0, 1).toUpperCase() + to.substring(1)+" ";

                //replace, but with first character uppercase
                text = text.replaceAll(from_b1, to_b1);
                logger.debug(from_b1+"--->"+ to_b1);

            }
            //mode==False (shorten)
            else
            {
                //add boundaries to match only whole words(to prevent replacements such as 'kadr'->'kadoktor')
                String from_a2="\\b"+from+"\\b";

                //replace
                text = text.replaceAll(from_a2,to);
                logger.debug(from_a2+"--->"+ to);

                //add boundaries and change first character to upper
                String from_b2 = "\\b"+from.substring(0, 1).toUpperCase() + from.substring(1)+"\\b";
                String to_b2 = to.substring(0, 1).toUpperCase() + to.substring(1);

                //replace, but with uppercase first character
                text = text.replaceAll(from_b2, to_b2);
                logger.debug(from_b2+"--->"+ to_b2);
            }

        }
        return text;
    }

}
