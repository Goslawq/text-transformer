package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformerServer;

import java.util.Arrays;

/**
 * This is the main controller class, which binds and handles the request
 */
@RestController
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);
    /**
     * Binding responsible for handling 'transform text' GET requests
     * Mapping: /{text}
     * @param text input text
     * @param transforms array of strings describing wanted transformations
     * @return output Text after transformations
     */
    @RequestMapping(value="/{text}",method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                      @RequestParam(value="transforms", defaultValue="") String[] transforms) {
        //stop spam in console, when browser requests site icon
        if (text.equals("favicon.ico"))
            return "";
        logger.info("Got 'GET' request (transform text)");

        // log the parameters
        logger.debug("Given text: "+text+" with parameters: "+Arrays.toString(transforms));


        // perform the transformation, you should run your logic here
        TextTransformerServer transformer = new TextTransformerServer(transforms);
        String output = transformer.transform(text);

        logger.debug("Returning output: "+output);
        return output;
    }

    /**
     * Binding responsible for handling 'get available transforms' GET request
     * Mapping: /transforms/
     * @return output String containing names of available transforms
     */
    @RequestMapping(value="/transforms/",method = RequestMethod.GET, produces = "application/json")
    public String get() {


        logger.debug("Got 'GET' request (get available transforms)");

        // perform the transformation, you should run your logic here

        String output="upper,lower,capital,latex,shorten,extend,double,number,invert,reverse";


        logger.debug("Returning output: "+output);
        return output;
    }

    /**
     * Binding responsible for handing POST requests
     * @param text input text
     * @param transforms array of strings describing wanted transformations
     * @return output Text after transformations
     */
    @RequestMapping(value="/{text}",method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                       @RequestBody String[] transforms) {
        logger.info("Got 'POST' request");
        // log the parameters
        logger.debug("Given text: "+text+" with parameters: "+Arrays.toString(transforms));

        // perform the transformation, you should run your logic here
        TextTransformerServer transformer = new TextTransformerServer(transforms);
        String output = transformer.transform(text);
        logger.debug("Returning output: "+output);
        return output;
    }



}