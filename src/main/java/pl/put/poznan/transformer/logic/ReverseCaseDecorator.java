package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.DecimalFormat;

/**
 * Class reverses Case big letters to small letter and vice versa
 */

public class ReverseCaseDecorator extends TextInterfaceDecorator{
    private static final Logger logger = LoggerFactory.getLogger(ReverseCaseDecorator.class);
    public ReverseCaseDecorator(TextInterface text_input){super(text_input);}

    /**
     * Method to get text after the desired transformation
     * @return String
     */
    @Override
    public String getTransformedText() {
        logger.debug("Computing deeper transformations");
        String text = super.getTransformedText();
        logger.debug("Got input: "+text);
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++)
        {
            char c = chars[i];
            if (Character.isUpperCase(c))
            {
                chars[i] = Character.toLowerCase(c);
            }
            else if (Character.isLowerCase(c))
            {
                chars[i] = Character.toUpperCase(c);
            }
        }
        logger.debug("Returning output: " + new String(chars));
        return new String(chars);
    }
}
