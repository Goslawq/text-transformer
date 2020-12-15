package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;

public class LaTeXDecorator extends TextInterfaceDecorator {
    private static final Logger logger = LoggerFactory.getLogger(LowerUpperDecorator.class);

    public LaTeXDecorator(TextInterface text_input){super(text_input);}

    @Override
    public String getTransformedText(){
        logger.debug("Computing deeper transformations");
        String input = super.getTransformedText();
        logger.debug("Got input: "+input);
        String symbols = "#$&{}_";
        int j = 0;
        char[] output_array = new char[2*input.length()];
        char[] input_array = input.toCharArray();
        for (char c : input_array) {
            if (symbols.contains("" + c)) {
                output_array[j++] = '\\';
            }
            output_array[j++] = c;
        }
        String output= new String(Arrays.copyOfRange(output_array,0,j));
        logger.debug("Returning output: " + output);
        return output;
    }


}
