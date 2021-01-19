package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseCaseDecoratorTest {
    TextTransformerServer _decorator;
    ReverseCaseDecorator rc_decorator;

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

    @Test
    void testReverseCaseDecoratorMock_1(){
        TextInterface mock_textInterface=mock(TextInterface.class);
        when(mock_textInterface.getTransformedText()).thenReturn("Hi");
        rc_decorator =new ReverseCaseDecorator(mock_textInterface);
        assertEquals("hI", rc_decorator.getTransformedText());
    }

    @Test
    void testReverseCaseDecoratorMock_2(){
        TextTransformerServer mock_decorator = new TextTransformerServer(new String[]{"reverse"});
        assertEquals("czeSC WszYstkim", mock_decorator.transform("CZEsc wSZySTKIM"));
    }

    @Test
    void testReverseCaseDecoratorMock_3(){
        TextTransformerServer mock_decorator = new TextTransformerServer(new String[]{"reverse"});
        assertEquals("HaLo", mock_decorator.transform("hAlO"));
    }

    @Test
    void testReverseCaseDecoratorMock_4(){
        TextTransformerServer mock_decorator = new TextTransformerServer(new String[]{"reverse"});
        assertEquals("DUZE", mock_decorator.transform("duze"));
    }

    @Test
    void testReverseCaseDecoratorMock_5(){
        TextTransformerServer mock_decorator = new TextTransformerServer(new String[]{"reverse"});
        assertEquals("Małe", mock_decorator.transform("mAŁE"));
        //verify(mock_decorator).transform(anyString());
    }


}
