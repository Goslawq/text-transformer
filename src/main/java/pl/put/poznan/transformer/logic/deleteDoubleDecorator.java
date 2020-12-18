package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class deleteDoubleDecorator extends TextInterfaceDecorator {
    private static final Logger logger = LoggerFactory.getLogger(deleteDoubleDecorator.class);

    public deleteDoubleDecorator(TextInterface text_input){super(text_input);}

    @Override
    public String getTransformedText() {
        logger.debug("Entered Abbreviation method, var mode="+mode);
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
        return text;
    }
}
