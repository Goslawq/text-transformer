package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaTeXDecoratorTest {
    TextInterface transformer = null;
    LaTeXDecorator input = null;

    @AfterEach
    void tearDown(){
        transformer = null;
        input = null;
    }

    @Test
    void testLateX(){
        transformer = new ConcreteText("Oper@tor {change}, b#t n_t a$$!");
        input = new LaTeXDecorator(transformer);
        assertEquals("Oper\\@tor \\{change\\}, b#t n\\_t a\\$\\$!",input.getTransformedText());
    }

}