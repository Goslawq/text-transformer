package pl.put.poznan.transformer.logic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import pl.put.poznan.transformer.logic.DeleteDoubleDecorator;
import pl.put.poznan.transformer.logic.TextTransformer;

import static org.junit.jupiter.api.Assertions.*;
public class DeleteDoubleDecoratorTest {
    TextTransformer _decorator;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        _decorator = null;
    }

    @Test
    void testgetTransforemedText(){
    }

    @Test
    void testDeleteDoubleDecorator_1(){
        _decorator = new TextTransformer(new String[]{"double"});
        assertEquals("hi", _decorator.transform("hi hi"));
    }

    @Test
    void testDeleteDoubleDecorator_2(){
        _decorator = new TextTransformer(new String[]{"double"});
        assertEquals("hi", _decorator.transform("hi Hi hi"));
    }

    @Test
    void testDeleteDoubleDecorator_3(){
        _decorator = new TextTransformer(new String[]{"double"});
        assertEquals("hi hej hi", _decorator.transform("hi Hi hej hi"));
    }
}
