package pl.put.poznan.transformer.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class the repetition of words written next to each other.
 * Halo hAlo hej halo do Halo hej hlo
 */
public class DeleteDoubleDecorator extends TextInterfaceDecorator {
    private static final Logger logger = LoggerFactory.getLogger(DeleteDoubleDecorator.class);

    public DeleteDoubleDecorator(TextInterface text_input){super(text_input);}
    /**
     * Method to get text after the desired transformation
     * @return String
     */
    @Override
    public String getTransformedText()
    {
        logger.debug("Entered DeleteDouble method");
        String text = super.getTransformedText();
        String _double = "\\b([a-zA-Z0-9ąĄćĆęĘśŚłŁźŹóÓżŻ]+)(?:\\W+\\1\\b)+";
        Pattern p
                = Pattern.compile(_double,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);


        while (m.find()) {//przeszukuje input, doputy są dwa identyczne wyrazy obok siebie
            text = text.replaceAll(
                    m.group(),
                    m.group(1));
        }
        logger.debug("Returning output: " + text);
        return text.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }
}