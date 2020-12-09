package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.logic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    public static void main(String[] args) {
        //SpringApplication.run(TextTransformerApplication.class, args);
        runDemo();
    }

    /**
     * Temporary demo function for showing everyone the required syntax/concepts
     */
    public static void runDemo(){
        System.out.println("Hello World!");
        Logger logger = LoggerFactory.getLogger("DemoLogger");
        logger.info("Starting demo example...");

        logger.debug("Creating nested decorators");
        TextInterface tester = new DecoratorB(
                new DecoratorB(
                        new DecoratorA(
                                new ConcreteText("tekst -do- dekorowania"))));
        String strr = tester.getTransformedText();
        logger.debug("Showing output...");
        System.out.println(strr);
        strr=new InverseTextDecorator(tester).getTransformedText();
        System.out.println(strr);
        System.out.println(new InverseTextDecorator(new ConcreteText("AaBbBcccc")).getTransformedText());
    }

}
