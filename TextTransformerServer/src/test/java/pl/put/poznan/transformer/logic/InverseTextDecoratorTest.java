package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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


}