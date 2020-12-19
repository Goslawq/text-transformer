package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to upper, lower or capitalize the input.
 * This class extends TextInterfaceDecorator
 * Has a 'mode' attribute that is responsible for assigning right transformation.
 * @author Eliza
 *
 */

public class LowerUpperDecorator extends TextInterfaceDecorator {
    private static final Logger logger = LoggerFactory.getLogger(LowerUpperDecorator.class);
    private final int mode;
    /**
     * Constructor method
     * @param text_input_instance
     * @param mode value from(0 - upper all characters;1 - lower all characters; 2 - capitalize)
     */
    public LowerUpperDecorator(TextInterface text_input_instance, int mode) {
        super(text_input_instance);
        this.mode = mode;
    }

    /**
     * Method to get text after the desired transformation.
     * @return Transformed String
     */

    @Override
    public String getTransformedText() {
        logger.debug("Computing deeper transformations");
        String input = super.getTransformedText();
        String output = "";

        logger.debug("Got input: " + input);
        if (this.mode == 0) {
            logger.debug("Upper all characters");
            output = input.toUpperCase();
        } else if (this.mode == 1) {
            logger.debug("Lower all characters");
            output = input.toLowerCase();
        } else if (this.mode ==2) {
            logger.debug("Capitalize input");
            int i = 0;
            String[] words = input.split(" ");
            for(String word: words){
                words[i] = word.substring(0,1).toUpperCase() + word.substring(1);
                i = i+1;
            }
            output = String.join(" ",words);
        }
        logger.debug("Returning output: " + output);
        return output;
    }
}
