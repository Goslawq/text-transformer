package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class is responsible for inversing the text while preserving its Upper/Lower case formatting.
 * For example "AaBbBcccc" should be transformed to "CcCcBbbaa"
 * This class extends TextInterfaceDecorator
 */
public class InverseTextDecorator extends TextInterfaceDecorator {
    private static final Logger logger = LoggerFactory.getLogger(InverseTextDecorator.class);

    /**
     * Creates InverseTextDecorator instance
     * @param text_input_instance deeper TextInterface instance
     */
    public InverseTextDecorator(TextInterface text_input_instance) {
        super(text_input_instance);
    }

    /**
     * Method to get text after the desired transformation
     * @return String
     */
    @Override
    public String getTransformedText() {
        logger.debug("Computing deeper transformations");
        String input = super.getTransformedText();
        logger.debug("Got input: "+input);
        char[] output_array = new char[input.length()];
        char[] input_array = input.toCharArray();
        for (int i = 0; i < input_array.length; i++) {
            if (Character.isUpperCase(input_array[i])) {
                output_array[i] = Character.toUpperCase(input_array[input_array.length - i - 1]);
            } else {
                output_array[i] = Character.toLowerCase(input_array[input_array.length - i - 1]);
            }
        }
        String output= new String(output_array);
        logger.debug("Returning output: " + output);
        return output;
    }
}

