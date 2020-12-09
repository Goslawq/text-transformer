package pl.put.poznan.transformer.logic;


/**
 * This the class that allows for easy creation and execution of various text-transformers
 */
public abstract class TextInterfaceDecorator implements TextInterface {
    private TextInterface text_instance;

    public TextInterfaceDecorator(TextInterface text_input) {
        text_instance = text_input;
    }

    @Override
    public String getTransformedText() {
        return text_instance.getTransformedText();
    }
}

