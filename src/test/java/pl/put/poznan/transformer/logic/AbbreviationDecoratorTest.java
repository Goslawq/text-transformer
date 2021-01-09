package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class AbbreviationDecoratorTest {

    AbbreviationDecorator abbDecInstance = null;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void cleanUp() {
        abbDecInstance=null;
    }

    @Test
    public void testExtend_1() {


        abbDecInstance = new AbbreviationDecorator(new ConcreteText("cdn. np. Np. br. dr Mgr."),true );
        assertEquals("ciąg dalszy nastąpi na przykład Na przykład bieżącego roku doktor Magister.",abbDecInstance.getTransformedText());
    }

    @Test
    public void testExtend_2() {

        abbDecInstance = new AbbreviationDecorator(new ConcreteText("Pan dr mgr inż. dzisiaj w formie."),true );
        assertEquals("Pan doktor magister inżynier dzisiaj w formie.",(abbDecInstance.getTransformedText()));
    }

    @Test
    public void testExtend_3() {
        abbDecInstance = new AbbreviationDecorator(new ConcreteText("Mgr mgr mgr Mgr."),true );
        assertEquals("Magister magister magister Magister.",abbDecInstance.getTransformedText());
    }

    @Test
    public void testExtend_4() {

        abbDecInstance = new AbbreviationDecorator(new ConcreteText("Jęz. polski to br. m. in. popularny jęz.."),true );
        assertEquals("Język polski to bieżącego roku między innymi popularny język.",abbDecInstance.getTransformedText());
    }

    @Test
    public void testExtend_5() {

        abbDecInstance = new AbbreviationDecorator(new ConcreteText("ten tekst nie powinien zostać zmieniony"),true );
        assertEquals("ten tekst nie powinien zostać zmieniony",abbDecInstance.getTransformedText());
    }

    @Test
    public void testShorten_1() {
        abbDecInstance = new AbbreviationDecorator(new ConcreteText("ten tekst nie powinien zostać zmieniony"),false );
        assertEquals("ten tekst nie powinien zostać zmieniony",abbDecInstance.getTransformedText());
    }

    @Test
    public void testShorten_2() {
        abbDecInstance = new AbbreviationDecorator(new ConcreteText("Doktor doktor magister bieżącego roku."),false );
        assertEquals("Dr dr mgr br..",abbDecInstance.getTransformedText());
    }

    @Test
    public void testShorten_3() {
        abbDecInstance = new AbbreviationDecorator(new ConcreteText("i tym podobne I tak dalej, a ciąg dalszy nastąpi"),false );
        assertEquals("itp. Itd., a cdn.",abbDecInstance.getTransformedText());
    }

    @Test
    public void testShorten_4() {
        abbDecInstance = new AbbreviationDecorator(new ConcreteText("doktor Doktor doktor doktor doktor Doktor Doktor"),false );
        assertEquals("dr Dr dr dr dr Dr Dr",abbDecInstance.getTransformedText());
    }

    @Test
    public void testShorten_5() {
        abbDecInstance = new AbbreviationDecorator(new ConcreteText("takie słowa jak między innymi doktorski nie powinny być zamieniane na drski, ale samo doktor już na przykład tak."),false );
        assertEquals("takie słowa jak m. in. doktorski nie powinny być zamieniane na drski, ale samo dr już np. tak.",abbDecInstance.getTransformedText());
    }

}