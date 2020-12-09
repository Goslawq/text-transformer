package pl.put.poznan.transformer.logic;

/**
 * Just for testing - adds 'A' to the end of a given input
 */
public class DecoratorA extends TextInterfaceDecorator {
    public DecoratorA(TextInterface text_input_instance) {
        super(text_input_instance);
    }

    @Override
    public String getTransformedText() {
        return super.getTransformedText() + 'A';
    }
}
