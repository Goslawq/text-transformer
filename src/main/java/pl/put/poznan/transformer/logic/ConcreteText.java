package pl.put.poznan.transformer.logic;

/**
 * This is the simlpest TextInterface implementation - used to convert String into format acceptable by decorators
 */
public class ConcreteText implements TextInterface {
    String text_field;

    public ConcreteText(String startingText) {
        this.text_field = startingText;
    }

    @Override
    public String getTransformedText() {
        return this.text_field;
    }
}

