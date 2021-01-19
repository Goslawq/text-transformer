package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseCaseDecoratorTest {
    TextTransformerServer _decorator;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        _decorator = null;
    }

    @Test
    void testReverseCaseDecorator_1(){
        _decorator = new TextTransformerServer(new String[]{"reverse"});
        assertEquals("hI", _decorator.transform("Hi"));
    }

    @Test
    void testReverseCaseDecorator_2(){
        _decorator = new TextTransformerServer(new String[]{"reverse"});
        assertEquals("czeSC WszYstkim", _decorator.transform("CZEsc wSZySTKIM"));
    }

    @Test
    void testReverseCaseDecorator_3(){
        _decorator = new TextTransformerServer(new String[]{"reverse"});
        assertEquals("HaLo", _decorator.transform("hAlO"));
    }

    @Test
    void testReverseCaseDecorator_4(){
        _decorator = new TextTransformerServer(new String[]{"reverse"});
        assertEquals("DUZE", _decorator.transform("duze"));
    }

    @Test
    void testReverseCaseDecorator_5(){
        _decorator = new TextTransformerServer(new String[]{"reverse"});
        assertEquals("Małe", _decorator.transform("mAŁE"));
    }


}
