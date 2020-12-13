package pl.put.poznan.transformer.logic;

/**
 * This is the simlpest TextInterface implementation - used to convert String into format acceptable by decorators
 */
public class ConcreteText implements TextInterface {
    String text_field;

    /**
     * Creates ConcreteText instance with a given string, which can be used to provide starting text for decorators
     * @param startingText
     */
    public ConcreteText(String startingText) {
        this.text_field = startingText;
    }

    @Override
    public String getTransformedText() {
        return this.text_field;
    }
}

