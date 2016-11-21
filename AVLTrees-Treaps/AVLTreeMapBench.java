//David Kleinberg
//dkleinb1@jhu.edu

import com.github.phf.jb.Bench;
import com.github.phf.jb.Bee;

import java.util.Random;

/**
 * Compare performance of ArraySet and ListSet.
 */
public final class AVLTreeMapBench {
    private static final int SIZE = 1000;
    private static final Random RAND = new Random();

    private AVLTreeMapBench() {}

    // First some basic "compound operations" to benchmark. Note that each
    // of these is carefully dimensioned (regarding the range of elements)
    // to allow combining them.

    // Insert a number of "consecutive" strings into the given set.
    private static void insertLinear(AVLTreeMap<String, String> bst) {
        for (int i = 0; i < SIZE; i++) {
            bst.insert(Integer.toString(i), "A");
        }
    }

    // Insert a number of "random" strings into the given set.
    private static void insertRandom(AVLTreeMap<String, String> bst) {
        for (int i = 0; i < SIZE; i++) {
            bst.insert(Integer.toString(RAND.nextInt(SIZE * 4)), "A");
        }
    }

    // Remove a number of "random" strings from the given set.
    private static void removeRandom(AVLTreeMap<String, String> bst) {
        for (int i = 0; i < SIZE; i++) {
            bst.remove(Integer.toString(RAND.nextInt(SIZE * 2)));
        }
    }

    // Remove a number of "random" strings from the given set.
    private static void removeLinear(AVLTreeMap<String, String> bst) {
        for (int i = 0; i < SIZE; i++) {
            bst.remove(Integer.toString(i));
        }
    }

    // Lookup a number of "consecutive" strings in the given set.
    private static void lookupLinear(AVLTreeMap<String, String> bst) {
        for (int i = 0; i < SIZE; i++) {
            boolean x = bst.has(Integer.toString(i));
        }
    }

    // Lookup a number of "random" strings in the given set.
    private static void lookupRandom(AVLTreeMap<String, String> bst) {
        for (int i = 0; i < SIZE; i++) {
            boolean x = bst.has(Integer.toString(RAND.nextInt(SIZE)));
        }
    }

    // Now the benchmarks we actually want to run.

    @Bench
    public static void insertLinearBST(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            AVLTreeMap<String, String> bst = new AVLTreeMap<>();
            b.start();
            insertLinear(bst);
        }
    }

    @Bench
    public static void insertRandomBST(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            AVLTreeMap<String, String> bst = new AVLTreeMap<>();
            b.start();
            insertRandom(bst);
        }
    }


    @Bench
    public static void removeLinearBST(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            AVLTreeMap<String,String> bst = new AVLTreeMap<>();
            insertLinear(bst);
            b.start();
            removeLinear(bst);
        }
    }

    @Bench
    public static void removeRandomBST(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            AVLTreeMap<String, String> bst = new AVLTreeMap<>();
            insertLinear(bst);
            b.start();
            removeRandom(bst);
        }
    }

    @Bench
    public static void lookupLinearBST(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            AVLTreeMap<String, String> bst = new AVLTreeMap<>();
            insertLinear(bst);
            b.start();
            lookupLinear(bst);
        }
    }

    @Bench
    public static void lookupRandomBST(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            AVLTreeMap<String, String> bst = new AVLTreeMap<>();
            insertLinear(bst);
            b.start();
            lookupRandom(bst);
        }
    }

}