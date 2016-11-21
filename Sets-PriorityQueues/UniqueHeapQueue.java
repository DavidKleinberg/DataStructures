//David Kleinberg
//dkleinb1@jhu.edu

import java.util.Scanner;

/**
 * Filter unique integers from standard input to standard output.
 *
 * If you're benchmarking this program, you may want to suppress
 * the output by redirecting it to /dev/null.
 */
public final class UniqueHeapQueue {
    private static PriorityQueue<Integer> data;

    // Make checkstyle happy.
    private UniqueHeapQueue() {}

    /**
     *  Main method.
     *  @param args Command line arguments (ignored).
     */
    public static void main(String[] args) {
        data = new BinaryHeapPriorityQueue<Integer>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            int i = scanner.nextInt();
            data.insert(i);
        }

        int last = -1;
        while (!data.empty()) {
            if (!data.top().equals(last)) {
                System.out.println(data.top());
                last = data.top();
            }
            data.remove();
        }
    }
}
