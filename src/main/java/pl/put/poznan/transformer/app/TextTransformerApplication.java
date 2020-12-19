package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Text;
import pl.put.poznan.transformer.logic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;

/**
 * This is main app class
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {
    /**
     * Main function is responsible only for launching REST service on port 8080
     * Common usage in browser: 127.0.0.1:8080/TekstDoZmiany?transforms=capitalize,invert,capitalize
     * @param args command line arguments (not used)
     */
    public static void main(String[] args){
        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
