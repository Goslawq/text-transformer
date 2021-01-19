package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    void mockTestLaTeX(){
        TextInterface mock_interface = mock(TextInterface.class);
        when(mock_interface.getTransformedText()).thenReturn("Oper@tor {change}, b#t n_t a$$!");

        input = new LaTeXDecorator(mock_interface);
        assertEquals("Oper\\@tor \\{change\\}, b#t n\\_t a\\$\\$!",input.getTransformedText());
    }

}