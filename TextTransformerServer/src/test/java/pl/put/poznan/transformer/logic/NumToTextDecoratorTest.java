package pl.put.poznan.transformer.logic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumToTextDecoratorTest{
    TextTransformerServer _decorator;
    NumToTextDecorator nt_decorator;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        _decorator = null;
    }

    @Test
    void testNumToTextDecorator_1(){
        _decorator = new TextTransformerServer(new String[]{"number"});
        assertEquals("cz3ść", _decorator.transform("cz3ść"));
    }

    @Test
    void testNumToTextDecorator_2(){
        _decorator = new TextTransformerServer(new String[]{"number"});
        assertEquals("siedemdziesiąt siedem", _decorator.transform("77"));
    }

    @Test
    void testNumToTextDecorator_3(){
        _decorator = new TextTransformerServer(new String[]{"number"});
        assertEquals("99999999999999999999", _decorator.transform("99999999999999999999"));
    }

    @Test
    void testNumToTextDecorator_4(){
        _decorator = new TextTransformerServer(new String[]{"number"});
        assertEquals("osiem siedem osiem", _decorator.transform("8 siedem 8"));
    }

    @Test
    void testNumToTextDecorator_5(){
        _decorator = new TextTransformerServer(new String[]{"number"});
        assertEquals("trzydzieści trzy tysiące", _decorator.transform("33 tysiące"));
    }

    @Test
    void testNumToTextDecoratorMock_1(){
        TextInterface mock_textInterface=mock(TextInterface.class);
        when(mock_textInterface.getTransformedText()).thenReturn("33 tysiące");
        nt_decorator =new NumToTextDecorator(mock_textInterface);
        assertEquals("trzydzieści trzy tysiące", nt_decorator.getTransformedText());
    }

    @Test
    void testNumToTextDecoratorMock_2(){
        TextInterface mock_textInterface=mock(TextInterface.class);
        when(mock_textInterface.getTransformedText()).thenReturn("99999999999999999999");
        nt_decorator =new NumToTextDecorator(mock_textInterface);
        assertEquals("99999999999999999999", nt_decorator.getTransformedText());
    }

    @Test
    void testNumToTextDecoratorMock_3(){
        TextInterface mock_textInterface=mock(TextInterface.class);
        when(mock_textInterface.getTransformedText()).thenReturn("8 siedem 8");
        nt_decorator =new NumToTextDecorator(mock_textInterface);
        assertEquals("osiem siedem osiem", nt_decorator.getTransformedText());
    }

    @Test
    void testNumToTextDecoratorMock_4(){
        TextInterface mock_textInterface=mock(TextInterface.class);
        when(mock_textInterface.getTransformedText()).thenReturn("nic");
        nt_decorator =new NumToTextDecorator(mock_textInterface);
        assertEquals("nic", nt_decorator.getTransformedText());
    }
}
