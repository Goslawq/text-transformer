package pl.put.poznan.transformer.logic;

/**
 * Just for testing - adds 'B' to the end of a given input
 */
public class DecoratorB extends TextInterfaceDecorator {
    public DecoratorB(TextInterface text_input_instance) {
        super(text_input_instance);
    }

    @Override
    public String getTransformedText() {
        return super.getTransformedText() + 'B';
    }
}
