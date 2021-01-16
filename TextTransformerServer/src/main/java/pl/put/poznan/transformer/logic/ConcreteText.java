package pl.put.poznan.transformer.logic;

/**
 * This is the simplest TextInterface implementation - used to convert String into format acceptable by decorators
 */
public class ConcreteText implements TextInterface {
    String text_field;

    /**
     * Creates ConcreteText instance with a given string, which can be used to provide starting text for decorators
     * @param startingText returned, when getTransformedText() is called
     */
    public ConcreteText(String startingText) {
        this.text_field = startingText;
    }

    /**
     * Method used to get text after transformations
     * @return text after transformations
     */
    @Override
    public String getTransformedText() {
        return this.text_field;
    }
}

