package pl.put.poznan.transformer.logic;

/**
 * Main logic here - this the part that creates and executes given transformations
 */
public class TextTransformer {
    private final String[] commands;

    /**
     * Creates TextTransformer instance
     * @param params a list of transformations in the desired order.
     * Available transformations are:
     * upper - consult documentation of LowerUpperDecorator (mode=0)
     * lower - consult documentation of LowerUpperDecorator (mode=1)
     * capitalize - consult documentation of LowerUpperDecorator (mode=2)
     * latex - consult documentation of LaTeXDecorator
     * shorten - consult documentation of AbbreviationDecorator (mode=false)
     * extend - consult documentation of AbbreviationDecorator (mode=true)
     * double - consult documentation of DeleteDoubleDecorator
     * number - consult documentation of NumToTextDecorator
     * invert - consult documentation of InverseTextDecorator
     * reverse - consult documentation of ReverseCaseDecorator
     */
    public TextTransformer(String[] params){
        this.commands=params;
    }

    /**
     * The function returns given text after transformations (chosen when creating the instance).
     * @param text text for transformation
     * @return Text after transformations have been applied
     */
    public String transform(String text){
        TextInterface transformer = new ConcreteText(text);
        for (String command : this.commands) {
            switch (command) {
                case "upper":
                    transformer = new LowerUpperDecorator(transformer, 0);
                    break;
                case "lower":
                    transformer = new LowerUpperDecorator(transformer, 1);
                    break;
                case "capitalize":
                    transformer = new LowerUpperDecorator(transformer, 2);
                    break;
                case "latex":
                    transformer = new LaTeXDecorator(transformer);
                    break;
                case "shorten":
                    transformer = new AbbreviationDecorator(transformer,false);
                    break;
                case "extend":
                    transformer = new AbbreviationDecorator(transformer,true);
                    break;
                case "double":
                    transformer= new DeleteDoubleDecorator(transformer);
                    break;
                case "number":
                    transformer = new NumToTextDecorator(transformer);
                    break;
                case "invert":
                    transformer = new InverseTextDecorator(transformer);
                    break;
                case "reverse":
                    transformer = new ReverseCaseDecorator(transformer);
                    break;
            }
        }
        return transformer.getTransformedText();
    }
}


