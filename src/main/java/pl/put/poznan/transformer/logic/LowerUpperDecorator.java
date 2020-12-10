package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to upper, lower or capitalize the input.
 * This class extends TextInterfaceDecorator
 * Has a 'mode' attribute that is responsible for assigning right transformation in getTransformedText().
 * 3 modes:
 * mode = 0 - Upper all characters
 * mode = 1 - Lower all characters
 * mode = 2 - Capitalize first character
 * @author Eliza
 *
 */

public class LowerUpperDecorator extends TextInterfaceDecorator {
    private final int mode;
    public LowerUpperDecorator(TextInterface text_input_instance, int mode) {
        super(text_input_instance);
        this.mode = mode;
    }

    /**
     * Method to get text after the desired transformation.
     * There are 3 modes, right transformation
     * @return Transformed String
     */

    @Override
    public String getTransformedText() {
        Logger logger = LoggerFactory.getLogger("UpperLowerCapitalize");
        logger.debug("Computing deeper transformations");
        String input = super.getTransformedText();
        String output = null;
        logger.debug("Got input: " + input);
        if (this.mode == 0) {
            logger.debug("Upper all characters");
            output = input.toUpperCase();
        } else if (this.mode == 1) {
            logger.debug("Lower all characters");
            output = input.toLowerCase();
        } else if (this.mode ==2) {
            logger.debug("Capitalize input");
            output = input.substring(0, 1).toUpperCase() + input.substring(1);
        }
        logger.debug("Returning output: " + output);
        return output;
    };
}
