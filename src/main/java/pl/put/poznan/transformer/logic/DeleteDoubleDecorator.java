package pl.put.poznan.transformer.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class responssible for removal of repetition of words written next to each other.
 * Example "a a abc bc bc" transforms to "a abc bc"
 */
public class DeleteDoubleDecorator extends TextInterfaceDecorator {
    private static final Logger logger = LoggerFactory.getLogger(DeleteDoubleDecorator.class);

    /**
     * Creates DeleteDoubleDecorator
     * @param text_input deeper TextInterface instance
     */
    public DeleteDoubleDecorator(TextInterface text_input){super(text_input);}
    /**
     * Method to get text after the desired transformations
     * @return text after the desired transformations
     */
    @Override
    public String getTransformedText()
    {
        logger.debug("Entered DeleteDouble method");
        String text = super.getTransformedText();
        String _double = "\\b(\\w+)(?:\\W+\\1\\b)+";
        Pattern p
                = Pattern.compile(_double,Pattern.CASE_INSENSITIVE);
        //String dict_path="short_to_full.txt";
        //Map<String, String> words_map = new HashMap<String, String>();
        Matcher m = p.matcher(text);


        while (m.find()) {//przeszukuje input, doputy są dwa identyczne wyrazy obok siebie
            text = text.replaceAll(
                    m.group(),
                    m.group(1));
        }
        logger.debug("Returning output: " + text);
        return text;
    }
}
