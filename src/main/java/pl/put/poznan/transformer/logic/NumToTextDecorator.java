package pl.put.poznan.transformer.logic;



import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.DecimalFormat;

/**
 * Class that changes the number written to written in text
 */
public class NumToTextDecorator extends TextInterfaceDecorator{
    private static final Logger logger = LoggerFactory.getLogger(NumToTextDecorator.class);
    public NumToTextDecorator(TextInterface text_input){super(text_input);}

    private static final String[] setki = {//setki
            " ",
            " sto",
            " dwieście",
            " trzysta",
            " czterysta",
            " pięćset",
            " sześćset",
            " siedemset",
            " osiemset",
            " dzięćset"
    };

    private static final String[] tensNames = {//dziesiątki
            "",
            " dziesięć",
            " dwadzieścia",
            " trzydzieści",
            " czterdzieści",
            " pięćdziesiąt",
            " sześćdziesiąt",
            " siedemdziesiąt",
            " osiemdziesiąt",
            " dziewiędziesiąt"
    };

    private static final String[] numNames = {//jednostki+nastki
            "",
            " jeden",
            " dwa",
            " trzy",
            " czter",
            " pięć",
            " sześć",
            " siedem",
            " osiem",
            " dziewięć",
            " dziesięć",
            " jedenaście",
            " dwanaście",
            " trzynaście",
            " czternaście",
            " piętnaście",
            " szesnaście",
            " siedemnaście",
            " osiemnaście",
            " dziewiętnaście"
    };

    //private numToTextDecorator() {}
    /**
     * class for bilions and milions that uses convertLessThenOneThousand to change them into text
     */
    private static String return_num_part(int number,String num){//usławnia miliony+miliardy
        String _trad;
        if(number<2){ _trad= num+" ";}
        else if(number<10){ _trad = convertLessThanOneThousand(number)
                + " "+num+"y ";}
        else{
            _trad = convertLessThanOneThousand(number)
                    + " "+num+"ów ";}
        return _trad;
    }
    /**
     * Class for changing numbers less then 1000 into text
     */
    private static String convertLessThanOneThousand(int number) {//do stówy

        String soFar;

        if (number % 100 < 20){
            soFar = numNames[number % 100];
            number /= 100;

            soFar =setki[number] +soFar;
        }
        else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;

            soFar =setki[number] +soFar;
        }
        //if (number == 0) return soFar;
        return soFar;
    }

    /**
     * Method to get text after the desired transformation
     * @return String
     */
    @Override
    public String getTransformedText() {
        logger.debug("Entered number to text method");
        String[] text = super.getTransformedText().split(" ");//splits words
        String output = "";
        for (String t : text) {
            try {
                Long number = Long.parseLong(t);//checks if word is a number

                logger.debug("Found number");
                // od 0 do 999 999 999 999
                if (number == 0) {
                    return "zero";
                }
                if (number > 999999999999L) {
                    return Long.toString(number);
                }
                String snumber = Long.toString(number);

                // maskujemy zerami
                String mask = "000000000000";
                DecimalFormat df = new DecimalFormat(mask);
                snumber = df.format(number);

                // XXX000000000
                int miliardy = Integer.parseInt(snumber.substring(0, 3));
                // 000XXX000000
                int miliony = Integer.parseInt(snumber.substring(3, 6));
                // 000000XXX000
                int tysiace = Integer.parseInt(snumber.substring(6, 9));
                // 000000000XXX
                int reszta = Integer.parseInt(snumber.substring(9, 12));

                String tradBillions;
                switch (miliardy) {
                    case 0:
                        tradBillions = "";
                        break;
                    case 1:
                        tradBillions = return_num_part(miliardy, "miliard");
                        break;
                    default:
                        tradBillions = return_num_part(miliardy, "miliard");
                }
                String result = tradBillions;

                String tradMillions;
                switch (miliony) {
                    case 0:
                        tradMillions = "";
                        break;
                    case 1:
                        tradMillions = return_num_part(miliony, "milion");
                        break;
                    default:
                        tradMillions = return_num_part(miliony, "milion");
                }
                result = result + tradMillions;

                String tradHundredThousands;
                switch (tysiace) {
                    case 0:
                        tradHundredThousands = "";
                        break;
                    case 1:
                        tradHundredThousands = "tysiąc ";
                        break;
                    default:
                        if (tysiace < 2) {
                            tradHundredThousands = "tysiąc ";
                        } else if (tysiace < 5) {
                            tradHundredThousands = convertLessThanOneThousand(tysiace)
                                    + " tysięce ";
                        } else {
                            tradHundredThousands = convertLessThanOneThousand(tysiace)
                                    + " tysięcy ";
                        }
                }
                result = result + tradHundredThousands;

                String tradThousand;
                tradThousand = convertLessThanOneThousand(reszta);
                result = result + tradThousand;

                // usuwamy podwójne spacje
                //return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
                output = output+" "+result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
            } catch (NumberFormatException n) {
                logger.debug("not a number");
                output = output+" "+t;
                //throw new InvalidInputException(strings[t]);
            }}
        logger.debug("Returning output: " + output);
        return output;
    }
}