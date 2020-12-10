package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Text;
import pl.put.poznan.transformer.logic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Arrays;

/** main function. As arguments takes string you want to transform put in "<>" and transformation commands.
 *     ex. "<Untransformed text example> upper invert"
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(TextTransformerApplication.class.getName());
        logger.info("Starting app...");

        String input = String.join(" ", args);
        String text = input.substring(input.indexOf("<")+1,input.indexOf(">"));
        TextInterface primeText = new ConcreteText(text);
        String[] commands = input.substring(input.indexOf(">")+2).split(" ");

        logger.debug("Showing text before transformation:" + text);
        logger.debug("Creating nested decorators");
        TextInterface output = transformer(commands, primeText);
        logger.debug("Starting transformations");
        String finalText = output.getTransformedText();
        logger.info("Showing output:" + finalText);
    }

    /**
     * The function returns nested decorators.
     * @param commands a list of parsed string commands
     * @param textForTransformation string parsed for transformation
     * @return nested TextInterface object
     */

    public static TextInterface transformer(String[] commands, TextInterface textForTransformation){
        TextInterface transformer = textForTransformation;
        for (String command : commands) {
            switch (command) {
                case "upper":
                    transformer = new LowerUpperDecorator(transformer, 0);
                    break;
                case "lower":
                    transformer = new LowerUpperDecorator(transformer, 1);
                    break;
                case "capitalize":
                    transformer = new LowerUpperDecorator(transformer, 2);
                    break;
                case "invert":
                    transformer = new InverseTextDecorator(transformer);
                    break;
            }
        }
        return transformer;
    }

    /**
     * Temporary demo function for showing everyone the required syntax/concepts
     */
    /**
    public static void runDemo(){
        System.out.println("Hello World!");
        Logger logger = LoggerFactory.getLogger("DemoLogger");
        logger.info("Starting demo example...");

        logger.debug("Creating nested decorators");
        TextInterface tester = new DecoratorB(
                new DecoratorB(
                        new DecoratorA(
                                new LowerUpperDecorator(
                                new ConcreteText("tekst -do- dekorowania"),1))));
        String strr = tester.getTransformedText();
        logger.debug("Showing output...");
        System.out.println(strr);
        strr=new InverseTextDecorator(tester).getTransformedText();
        System.out.println(strr);
        System.out.println(new InverseTextDecorator(new ConcreteText("AaBbBcccc")).getTransformedText());
    }*/

}
