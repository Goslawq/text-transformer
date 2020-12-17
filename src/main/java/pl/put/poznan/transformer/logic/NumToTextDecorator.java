package pl.put.poznan.transformer.logic;



import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.DecimalFormat;

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


    @Override
    public String getTransformedText() {
        String[] text = super.getTransformedText().split(" ");
        String output = "";
        for (String t : text) {
            try {
                Long number = Long.parseLong(t);

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
                int billions = Integer.parseInt(snumber.substring(0, 3));
                // 000XXX000000
                int millions = Integer.parseInt(snumber.substring(3, 6));
                // 000000XXX000
                int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
                // 000000000XXX
                int thousands = Integer.parseInt(snumber.substring(9, 12));

                String tradBillions;
                switch (billions) {
                    case 0:
                        tradBillions = "";
                        break;
                    case 1:
                        tradBillions = return_num_part(billions, "miliard");
                        break;
                    default:
                        tradBillions = return_num_part(billions, "miliard");
                }
                String result = tradBillions;

                String tradMillions;
                switch (millions) {
                    case 0:
                        tradMillions = "";
                        break;
                    case 1:
                        tradMillions = return_num_part(millions, "milion");
                        break;
                    default:
                        tradMillions = return_num_part(millions, "milion");
                }
                result = result + tradMillions;

                String tradHundredThousands;
                switch (hundredThousands) {
                    case 0:
                        tradHundredThousands = "";
                        break;
                    case 1:
                        tradHundredThousands = "tysiąc ";
                        break;
                    default:
                        if (hundredThousands < 2) {
                            tradHundredThousands = "tysiąc ";
                        } else if (hundredThousands < 5) {
                            tradHundredThousands = convertLessThanOneThousand(hundredThousands)
                                    + " tysięce ";
                        } else {
                            tradHundredThousands = convertLessThanOneThousand(hundredThousands)
                                    + " tysięcy ";
                        }
                }
                result = result + tradHundredThousands;

                String tradThousand;
                tradThousand = convertLessThanOneThousand(thousands);
                result = result + tradThousand;

                // usuwamy podwójne spacje
                //return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
                output = output+" "+result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
            } catch (NumberFormatException n) {
                output = output+" "+t;
                //throw new InvalidInputException(strings[t]);
            }}
        return output;
    }
}