//David Kleinberg
//dkleinb1@jhu.edu

import java.util.Scanner;

/** Takes standard input
 *  removes duplicates
 *  prompts for any non-integer argument.
 */
public final class Unique {
    // how many slots in data are used?
    private static int used;
    // array of unique numbers
    private static SimpleArray<Integer> data;

    // make checkstyle happy
    private Unique() {}

    // position of given value in data[], -1 if not found
    private static int find(int value) {
        for (int i = 0; i < used; i++) {
            if (data.get(i) == value) {
                return i;
            }
        }
        return -1;
    }

    // insert value into data[] if not already present
    private static void insert(int value) {
        int position = find(value);
        if (position < 0) {
            if (data.length() == used) {
                grow();
            }
            data.put(used, value);
            used += 1;
        }
    }

    private static void grow() {
        SimpleArray<Integer> temp =
            new SimpleArray<Integer>(data.length() * 2, 0);
        for (int i = 0; i < data.length(); i++) {
            temp.put(i, data.get(i));
        }
        data = temp;
    }

    /**
        Print only unique integers out of given command line arguments.

        @param args Command line arguments.
    */
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        data = new SimpleArray<Integer>(1, 0);

        while (kb.hasNext()) {
            String arg = kb.next();
            try {
                int i = Integer.parseInt(arg);
                insert(i);
            } catch (NumberFormatException e) {
                System.err.printf("Ignored non-integer argument %s\n", arg);
            }
        }
        // output unique numbers in array order
        for (int i = 0; i < used; i++) {
            System.out.println(data.get(i));
        }
    }
}
