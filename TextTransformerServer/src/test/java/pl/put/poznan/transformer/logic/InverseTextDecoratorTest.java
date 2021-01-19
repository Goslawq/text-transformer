package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InverseTextDecoratorTest {
    TextInterface transformer = null;
    InverseTextDecorator input = null;

    @AfterEach
    void tearDown(){
        transformer = null;
        input = null;
    }

    @Test
    void testInverse(){
        transformer = new ConcreteText("I am tesTing strinG!!!");
        input = new InverseTextDecorator(transformer);
        assertEquals("!!!gnirtS gnitset Ma i", input.getTransformedText());
    }

    @Test
    void mockTestInverse(){
        TextInterface mock_interface = mock(TextInterface.class);
        when(mock_interface.getTransformedText()).thenReturn("I am tesTing strinG!!!");

        input = new InverseTextDecorator(mock_interface);
        assertEquals("!!!gnirtS gnitset Ma i", input.getTransformedText());
    }


}