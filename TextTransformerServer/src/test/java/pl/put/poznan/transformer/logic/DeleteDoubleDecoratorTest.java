package pl.put.poznan.transformer.logic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteDoubleDecoratorTest {
    TextTransformerServer _decorator;
    DeleteDoubleDecorator dd_decorator;

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
        _decorator = new TextTransformerServer(new String[]{"double"});
        assertEquals("hi", _decorator.transform("hi hi"));
    }

    @Test
    void testDeleteDoubleDecorator_2(){
        _decorator = new TextTransformerServer(new String[]{"double"});
        assertEquals("hi", _decorator.transform("hi Hi hi"));
    }

    @Test
    void testDeleteDoubleDecorator_3(){
        _decorator = new TextTransformerServer(new String[]{"double"});
        assertEquals("hi hej hi", _decorator.transform("hi Hi hej hi"));
    }

    @Test
    void testDeleteDoubleDecoratorMock_1(){
        TextInterface mock_textInterface=mock(TextInterface.class);
        when(mock_textInterface.getTransformedText()).thenReturn("9 9");
        dd_decorator =new DeleteDoubleDecorator(mock_textInterface);
        assertEquals("9", dd_decorator.getTransformedText());
    }

    @Test
    void testDeleteDoubleDecoratorMock_2(){
        TextInterface mock_textInterface=mock(TextInterface.class);
        when(mock_textInterface.getTransformedText()).thenReturn("9 0 9");
        dd_decorator =new DeleteDoubleDecorator(mock_textInterface);
        assertEquals("9 0 9", dd_decorator.getTransformedText());
    }

    @Test
    void testDeleteDoubleDecoratorMock_3(){
        TextInterface mock_textInterface=mock(TextInterface.class);
        when(mock_textInterface.getTransformedText()).thenReturn("Cz cz");
        dd_decorator =new DeleteDoubleDecorator(mock_textInterface);
        assertEquals("Cz", dd_decorator.getTransformedText());
    }
}
