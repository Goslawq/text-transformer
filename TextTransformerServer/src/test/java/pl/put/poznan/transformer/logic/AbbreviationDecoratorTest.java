package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    public void mockTest_shorten_1(){
        TextInterface mock_text_interface = mock(TextInterface.class);
        when(mock_text_interface.getTransformedText()).thenReturn("na przykład Na przykład ciąg dalszy nastąpi");

        abbDecInstance=new AbbreviationDecorator(mock_text_interface,false);
        assertEquals("np. Np. cdn.",abbDecInstance.getTransformedText());
    }

    @Test
    public void mockTest_shorten_2(){
        TextInterface mock_text_interface = mock(TextInterface.class);
        when(mock_text_interface.getTransformedText()).thenReturn("Bieżącego roku profesorski profesor i tym podobne i tak dalej.");

        abbDecInstance=new AbbreviationDecorator(mock_text_interface,false);
        assertEquals("Br. profesorski prof. itp. itd..",abbDecInstance.getTransformedText());
    }

    @Test
    public void mockTest_shorten_3(){
        TextInterface mock_text_interface = mock(TextInterface.class);
        when(mock_text_interface.getTransformedText()).thenReturn("Numer numerowy język językowy");

        abbDecInstance=new AbbreviationDecorator(mock_text_interface,false);
        assertEquals("Nr numerowy jęz. językowy",abbDecInstance.getTransformedText());
    }

    @Test
    public void mockTest_shorten_4(){
        TextInterface mock_text_interface = mock(TextInterface.class);
        when(mock_text_interface.getTransformedText()).thenReturn("Między innymi doktor magister Nowak jest tu dziś z nami");

        abbDecInstance=new AbbreviationDecorator(mock_text_interface,false);
        assertEquals("M. in. dr mgr Nowak jest tu dziś z nami",abbDecInstance.getTransformedText());
    }

    @Test
    public void mockTest_extend_1(){
        TextInterface mock_text_interface = mock(TextInterface.class);
        when(mock_text_interface.getTransformedText()).thenReturn("nr prof. itp. itd. kadr.");

        abbDecInstance=new AbbreviationDecorator(mock_text_interface,true);
        assertEquals("numer profesor i tym podobne i tak dalej kadr.",abbDecInstance.getTransformedText());
    }
    @Test
    public void mockTest_extend_2(){
        TextInterface mock_text_interface = mock(TextInterface.class);
        when(mock_text_interface.getTransformedText()).thenReturn("....nr nr nr nr nra nr....");

        abbDecInstance=new AbbreviationDecorator(mock_text_interface,true);
        assertEquals("....numer numer numer numer nra numer....",abbDecInstance.getTransformedText());
    }
    @Test
    public void mockTest_extend_3(){
        TextInterface mock_text_interface = mock(TextInterface.class);
        when(mock_text_interface.getTransformedText()).thenReturn("Br Br. br br. np. Np..");

        abbDecInstance=new AbbreviationDecorator(mock_text_interface,true);
        assertEquals("Br Bieżącego roku br bieżącego roku na przykład Na przykład.",abbDecInstance.getTransformedText());
    }

    @Test
    public void mockTest_extend_4(){
        TextInterface mock_text_interface = mock(TextInterface.class);
        when(mock_text_interface.getTransformedText()).thenReturn("Mgr, dr, prof. to przykładowe stopnie naukowe");

        abbDecInstance=new AbbreviationDecorator(mock_text_interface,true);
        assertEquals("Magister, doktor, profesor to przykładowe stopnie naukowe",abbDecInstance.getTransformedText());
    }

}