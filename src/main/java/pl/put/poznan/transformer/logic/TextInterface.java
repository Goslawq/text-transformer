package pl.put.poznan.transformer.logic;

/**
 * text interface required for TextInterfaceDecorator and ConcreteText
 */
public interface TextInterface {
    /**
     *
     * @return text after tansformations
     */
    String getTransformedText();
}

