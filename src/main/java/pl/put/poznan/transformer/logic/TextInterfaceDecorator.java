package pl.put.poznan.transformer.logic;


/**
 * This the class that allows for easy creation and execution of various text-transformers
 */
public abstract class TextInterfaceDecorator implements TextInterface {
    private final TextInterface text_instance;

    /**
     * Creates TextInterfaceDecorator instance
     * @param text_input deeper TextInterface instance
     */
    public TextInterfaceDecorator(TextInterface text_input) {
        text_instance = text_input;
    }

    /**
     * Method to get text after the desired transformations
     * @return String
     */
    @Override
    public String getTransformedText() {
        return text_instance.getTransformedText();
    }
}

