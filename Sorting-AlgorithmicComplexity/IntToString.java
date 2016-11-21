import java.util.Scanner;

/**
 * Converts to integers to strings for the PolySort class.
 * @author David
 *
 */
public final class IntToString {

    /**
     * Takes integer input and prints the String.
     * @param args input from file at command line
     */
    public static void main(String[]args) {

        Scanner input = new Scanner(System.in);

        String data = "";

        while (input.hasNext()) {
            data = data + lexicographic(input.nextInt()) + "\n";
        }

        System.out.println(data);

    }

    /**
     * Changes the digits to corresponding characters.
     * @param n one integer
     * @return in the form of a string
     */
    public static String lexicographic(int n) {
        int r = n;
        int count = 0;

        while (r >= 10) {
            r = r / 10;
            count++;
        }

        int buffer = 4 - count;

        String lexicographic = "";

        for (int c = 0; c < buffer; c++) {
            lexicographic = lexicographic + "a";
        }

        String s = "" + n;

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '0':
                    lexicographic = lexicographic + "a";
                    break;
                case '1':
                    lexicographic = lexicographic + "b";
                    break;
                case '2':
                    lexicographic = lexicographic + "c";
                    break;
                case '3':
                    lexicographic = lexicographic + "d";
                    break;
                case '4':
                    lexicographic = lexicographic + "e";
                    break;
                case '5':
                    lexicographic = lexicographic + "f";
                    break;
                case '6':
                    lexicographic = lexicographic + "g";
                    break;
                case '7':
                    lexicographic = lexicographic + "h";
                    break;
                case '8':
                    lexicographic = lexicographic + "i";
                    break;
                case '9':
                    lexicographic = lexicographic + "j";
                    break;
                default:
                    break;
            }
        }
        return lexicographic;
    }
}