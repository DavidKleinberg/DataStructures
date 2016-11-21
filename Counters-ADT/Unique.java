//David Kleinberg
//dkleinb1@jhu.edu

/** Finds the Unique numbers. */
final class Unique {

    private static int numInts;
    private static int[] input;

    /** Constructor. */
    private Unique() {
    }

    /** takes out repeated arguments. */
    private static void findUnique() {

        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] != 0 && input[j] != 0
                    && input[i] == input[j]) {
                    input[j] = 0;
                    numInts--;
                }
            }
        }
    }

    /** Prints the Unique numbers.
     * @param args */
    public static void main(String[]args) {

        numInts = args.length;
        input = new int[args.length];

        for (int i = 0; i < numInts; i++) {
            try {
                input[i] = Integer.parseInt(args[i]);
            } catch (NumberFormatException e) {
                input[i] = 0;
            }
        }

        findUnique();

        int counter = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] != 0) {
                System.out.println(input[i]);
                counter++;
            }
        }
        if (counter < numInts) {
            System.out.println("0");
        }
    }
}
