package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.ConcreteText;
import pl.put.poznan.transformer.logic.TextTransformer;

import java.util.Arrays;


@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                              @RequestParam(value="transforms", defaultValue="") String[] transforms) {
        //stop spam in console, when browser requests site icon
        if (text.equals("favicon.ico"))
            return "";
        logger.info("Got 'GET' request");

        // log the parameters
        logger.debug("Given text: "+text+" with parameters: "+Arrays.toString(transforms));


        // perform the transformation, you should run your logic here
        TextTransformer transformer = new TextTransformer(transforms);
        String output = transformer.transform(text);
        logger.debug("Returning output: "+output);
        return output;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                      @RequestBody String[] transforms) {
        logger.info("Got 'POST' request");
        // log the parameters
        logger.debug("Given text: "+text+" with parameters: "+Arrays.toString(transforms));

        // perform the transformation, you should run your logic here
        TextTransformer transformer = new TextTransformer(transforms);
        String output = transformer.transform(text);
        logger.debug("Returning output: "+output);
        return output;
    }



}


