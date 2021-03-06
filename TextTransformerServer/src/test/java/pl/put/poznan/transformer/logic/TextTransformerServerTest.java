package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextTransformerServerTest {

    TextTransformerServer transformer = null;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        transformer = null;
    }

    @Test
    void transformUpperLower() {
        transformer = new TextTransformerServer(new String[]{"upper", "lower"});
        assertEquals("stringgg testowy", transformer.transform("StRiNgGG tESTOWY"));
    }

    @Test
    void transformLowerCapitalize() {
        transformer = new TextTransformerServer(new String[]{"lower", "capitalize"});
        assertEquals("Stringgg Testowy", transformer.transform("StRiNgGG tESTOWY"));
    }

    @Test
    void transformCapitalizeLatex() {
        transformer = new TextTransformerServer(new String[]{"capitalize", "latex"});
        assertEquals("Łap\\@ Mnie \\$zczypie", transformer.transform("łap@ mnie $zczypie"));
    }

    @Test
    void transformLatexExtend() {
        transformer = new TextTransformerServer(new String[]{"latex", "extend"});
        assertEquals("Magister, doktor \\& profesor to przykł\\@dowe \\$topnie\\_naukowe", transformer.transform("Mgr, dr & prof. to przykł@dowe $topnie_naukowe"));
    }

    @Test
    void transformExtendDouble() {
        transformer = new TextTransformerServer(new String[]{"extend", "double"});
        assertEquals("Doktor nie jest magister", transformer.transform("Dr dr nie jest mgr mgr"));
    }

    @Test
    void transformDoubleNumber() {
        transformer = new TextTransformerServer(new String[]{"double", "number"});
        assertEquals("kosz5uje dziewięć dziewiędziesiąt osiem", transformer.transform("kosz5uje 9 9 9 9 98"));
    }

    /*
    @Test
    void transform() {
        transformer = new TextTransformer(new String[]{});
        assertEquals("", transformer.transform(""));
    }
    */
}