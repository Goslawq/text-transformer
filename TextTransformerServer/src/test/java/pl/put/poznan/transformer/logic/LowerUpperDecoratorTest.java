package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LowerUpperDecoratorTest {
    TextInterface transformer = null;
    LowerUpperDecorator input = null;

    @AfterEach
    void tearDown(){
        transformer = null;
        input = null;
    }

    @Test
    void testTransformLower(){
        transformer = new ConcreteText("I aM aN USER num 1!");
        input = new LowerUpperDecorator(transformer,1);
        assertEquals("i am an user num 1!",input.getTransformedText());
    }

    @Test
    void testTransformUpper(){
        transformer = new ConcreteText("I aM aN USER num 1!");
        input = new LowerUpperDecorator(transformer,0);
        assertEquals("I AM AN USER NUM 1!",input.getTransformedText());
    }

    @Test
    void testTransformCapitalize(){
        transformer = new ConcreteText("I aM aN USER num 1!");
        input = new LowerUpperDecorator(transformer,2);
        assertEquals("I AM AN USER Num 1!",input.getTransformedText());
    }

    @Test
    void mockTransformLower(){
        TextInterface mock_interface = mock(TextInterface.class);
        when(mock_interface.getTransformedText()).thenReturn("I aM aN USER num 1!");

        input = new LowerUpperDecorator(mock_interface, 1);
        assertEquals("i am an user num 1!", input.getTransformedText());
    }

    @Test
    void mockTransformUpper(){
        TextInterface mock_interface = mock(TextInterface.class);
        when(mock_interface.getTransformedText()).thenReturn("I aM aN USER num 1!");

        input = new LowerUpperDecorator(mock_interface, 0);
        assertEquals("I AM AN USER NUM 1!", input.getTransformedText());
    }

    @Test
    void mockTransformCapitalize(){
        TextInterface mock_interface = mock(TextInterface.class);
        when(mock_interface.getTransformedText()).thenReturn("I aM aN USER num 1!");

        input = new LowerUpperDecorator(mock_interface, 2);
        assertEquals("I AM AN USER Num 1!", input.getTransformedText());
    }


}