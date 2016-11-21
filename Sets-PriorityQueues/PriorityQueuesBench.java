//David Kleinberg
//dkleinb1@jhu.edu

import com.github.phf.jb.Bench;
import com.github.phf.jb.Bee;

import java.util.Random;
import java.io.IOException;

/**
 * Compare performance of ArraySet and ListSet.
 */
public final class PriorityQueuesBench {
    private static final int SIZE = 100;
    private static final Random RAND = new Random();

    private PriorityQueuesBench() {}

    // First some basic "compound operations" to benchmark. Note that each
    // of these is carefully dimensioned (regarding the range of elements)
    // to allow combining them.

    // Insert a number of "consecutive" strings into the given set.
    private static void insertLinear(PriorityQueue<String> s) {
        for (int i = 0; i < SIZE; i++) {
            s.insert(Integer.toString(i));
        }
    }

    // Insert a number of "random" strings into the given set.
    private static void insertRandom(PriorityQueue<String> s) {
        for (int i = 0; i < SIZE; i++) {
            s.insert(Integer.toString(RAND.nextInt(SIZE * 4)));
        }
    }

    // Now the benchmarks we actually want to run.


    @Bench
    public static void insertLinearBHPQ(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            PriorityQueue<String> s = new BinaryHeapPriorityQueue<>();
            b.start();
            insertLinear(s);
        }
    }

    @Bench
    public static void insertLinearSAPQ(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            PriorityQueue<String> s = new SortedArrayPriorityQueue<>();
            b.start();
            insertLinear(s);
        }
    }

    @Bench
    public static void insertRandomSAPQ(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            PriorityQueue<String> s = new SortedArrayPriorityQueue<>();
            b.start();
            insertRandom(s);
        }
    }

    @Bench
    public static void insertRandomBHPQ(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            PriorityQueue<String> s = new BinaryHeapPriorityQueue<>();
            b.start();
            insertRandom(s);
        }
    }
}
