package pl.put.poznan.transformer.logic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class NumToTextDecoratorTest{
    TextTransformerServer _decorator;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        _decorator = null;
    }

    @Test
    void testDeleteDoubleDecorator_1(){
        _decorator = new TextTransformerServer(new String[]{"number"});
        assertEquals("cz3ść", _decorator.transform("cz3ść"));
    }

    @Test
    void testDeleteDoubleDecorator_2(){
        _decorator = new TextTransformerServer(new String[]{"number"});
        assertEquals("siedemdziesiąt siedem", _decorator.transform("77"));
    }

    @Test
    void testDeleteDoubleDecorator_3(){
        _decorator = new TextTransformerServer(new String[]{"number"});
        assertEquals("99999999999999999999", _decorator.transform("99999999999999999999"));
    }

    @Test
    void testDeleteDoubleDecorator_4(){
        _decorator = new TextTransformerServer(new String[]{"number"});
        assertEquals("osiem siedem osiem", _decorator.transform("8 siedem 8"));
    }

    @Test
    void testDeleteDoubleDecorator_5(){
        _decorator = new TextTransformerServer(new String[]{"number"});
        assertEquals("trzydzieści trzy tysiące", _decorator.transform("33 tysiące"));
    }
}
