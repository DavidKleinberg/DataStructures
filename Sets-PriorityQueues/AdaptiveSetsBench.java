//David Kleinberg
//dkleinb1@jhu.edu

import com.github.phf.jb.Bench;
import com.github.phf.jb.Bee;

import java.util.Random;
import java.io.IOException;

/**
 * Compare performance of ArraySet and ListSet.
 */
public final class AdaptiveSetsBench {
    private static final int SIZE = 100;
    private static final Random RAND = new Random();

    private AdaptiveSetsBench() {}

    // First some basic "compound operations" to benchmark. Note that each
    // of these is carefully dimensioned (regarding the range of elements)
    // to allow combining them.

    // Insert a number of "consecutive" strings into the given set.
    private static void insertLinear(Set<String> s) {
        for (int i = 0; i < SIZE; i++) {
            s.insert(Integer.toString(i));
        }
    }

    // Insert a number of "random" strings into the given set.
    private static void insertRandom(Set<String> s) {
        for (int i = 0; i < SIZE; i++) {
            s.insert(Integer.toString(RAND.nextInt(SIZE * 4)));
        }
    }

    // Remove a number of "random" strings from the given set.
    private static void removeRandom(Set<String> s) {
        for (int i = 0; i < SIZE; i++) {
            s.remove(Integer.toString(RAND.nextInt(SIZE * 2)));
        }
    }

    // Lookup a number of "consecutive" strings in the given set.
    private static void lookupLinear(Set<String> s) {
        for (int i = 0; i < SIZE; i++) {
            boolean x = s.has(Integer.toString(i));
        }
    }

    // Lookup a number of "random" strings in the given set.
    private static void lookupRandom(Set<String> s) {
        for (int i = 0; i < SIZE; i++) {
            boolean x = s.has(Integer.toString(RAND.nextInt(SIZE)));
        }
    }

    // Lookup a number of "random" strings in the given set.
    private static void lookupLessRandom(Set<String> s) {
        for (int i = 0; i < SIZE; i++) {
            boolean x = s.has(Integer.toString(RAND.nextInt(SIZE/10)));
        }
    }

    private static void lookupRepeated(Set<String> s) {
        for (int i = 0; i < SIZE/2; i+=2) {
            boolean x = s.has(Integer.toString(i));
            x = s.has(Integer.toString(i+1));
        }
    }

    private static void lookupConstant(Set<String> s) {
        for (int i = 0; i < SIZE; i++) {
            boolean x = s.has(Integer.toString(99));
        }
    }

    // Now the benchmarks we actually want to run.

/**
    INSERTS
*/

    @Bench
    public static void insertLinearMTF(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new MoveToFrontListSet<>();
            b.start();
            insertLinear(s);
        }
    }

    @Bench
    public static void insertLinearTranspose(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new TransposeArraySet<>();
            b.start();
            insertLinear(s);
        }
    }

    @Bench
    public static void insertLinearArraySet(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new ArraySet<>();
            b.start();
            insertLinear(s);
        }
    }

    @Bench
    public static void insertLinearListSet(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new ListSet<>();
            b.start();
            insertLinear(s);
        }
    }

    @Bench
    public static void insertRandomMTF(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new MoveToFrontListSet<>();
            b.start();
            insertRandom(s);
        }
    }

    @Bench
    public static void insertRandomTranspose(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new TransposeArraySet<>();
            b.start();
            insertRandom(s);
        }
    }

    @Bench
    public static void insertRandomListSet(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new ListSet<>();
            b.start();
            insertRandom(s);
        }
    }

    @Bench
    public static void insertRandomArraySet(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new ArraySet<>();
            b.start();
            insertRandom(s);
        }
    }

/**
    REMOVES
*/
    @Bench
    public static void removeRandomMTF(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new MoveToFrontListSet<>();
            insertRandom(s);
            b.start();
            removeRandom(s);
        }
    }

    @Bench
    public static void removeRandomTranspose(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new TransposeArraySet<>();
            insertRandom(s);
            b.start();
            removeRandom(s);
        }
    }

    @Bench
    public static void removeRandomArraySet(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new ArraySet<>();
            insertRandom(s);
            b.start();
            removeRandom(s);
        }
    }

    @Bench
    public static void removeRandomListSet(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new ListSet<>();
            insertRandom(s);
            b.start();
            removeRandom(s);
        }
    }

/**
    LOOK UPS
*/

    @Bench
    public static void lookupConstantMTF(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new MoveToFrontListSet<>();
            insertLinear(s);
            b.start();
            lookupConstant(s);
        }
    }

    @Bench
    public static void LookupConstantTranspose(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new TransposeArraySet<>();
            insertLinear(s);
            b.start();
            lookupConstant(s);
        }
    }

    @Bench
    public static void lookupConstantArraySet(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new ArraySet<>();
            insertLinear(s);
            b.start();
            lookupConstant(s);
        }
    }

    @Bench
    public static void LookupConstantListSet(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new ListSet<>();
            insertLinear(s);
            b.start();
            lookupConstant(s);
        }
    }

    @Bench
    public static void lookupLinearArraySet(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new ArraySet<>();
            insertLinear(s);
            b.start();
            lookupLinear(s);
        }
    }

    @Bench
    public static void lookupLinearListSet(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new ListSet<>();
            insertLinear(s);
            b.start();
            lookupLinear(s);
        }
    }


    @Bench
    public static void lookupLinearMTF(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new MoveToFrontListSet<>();
            insertLinear(s);
            b.start();
            lookupLinear(s);
        }
    }

    @Bench
    public static void lookupLinearTranspose(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new TransposeArraySet<>();
            insertLinear(s);
            b.start();
            lookupLinear(s);
        }
    }


    @Bench
    public static void lookupRandomMTF(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new MoveToFrontListSet<>();
            insertLinear(s);
            b.start();
            lookupRandom(s);
        }
    }

    @Bench
    public static void lookupRandomTranspose(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new TransposeArraySet<>();
            insertLinear(s);
            b.start();
            lookupRandom(s);
        }
    }

    @Bench
    public static void lookupRandomArraySet(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new ArraySet<>();
            insertLinear(s);
            b.start();
            lookupRandom(s);
        }
    }

    @Bench
    public static void lookupRandomListSet(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new ListSet<>();
            insertLinear(s);
            b.start();
            lookupRandom(s);
        }
    }

    @Bench
    public static void lookupLessRandomMTF(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new MoveToFrontListSet<>();
            insertLinear(s);
            b.start();
            lookupLessRandom(s);
        }
    }

    @Bench
    public static void lookupLessRandomTranspose(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new TransposeArraySet<>();
            insertLinear(s);
            b.start();
            lookupLessRandom(s);
        }
    }

    @Bench
    public static void lookupLessRandomListSet(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new ListSet<>();
            insertLinear(s);
            b.start();
            lookupLessRandom(s);
        }
    }

    @Bench
    public static void lookupLessRandomArraySet(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new ArraySet<>();
            insertLinear(s);
            b.start();
            lookupLessRandom(s);
        }
    }


    @Bench
    public static void lookupRepeatedTranspose(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new TransposeArraySet<>();
            insertLinear(s);
            b.start();
            lookupRepeated(s);
        }
    }

    @Bench
    public static void lookupRepeatedMTF(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new MoveToFrontListSet<>();
            insertLinear(s);
            b.start();
            lookupRepeated(s);
        }
    }

    @Bench
    public static void lookupRepeatedArraySet(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new ArraySet<>();
            insertLinear(s);
            b.start();
            lookupRepeated(s);
        }
    }

    @Bench
    public static void lookupRepeatedListSet(Bee b) {
        for (int n = 0; n < b.reps(); n++) {
            b.stop();
            Set<String> s = new ListSet<>();
            insertLinear(s);
            b.start();
            lookupRepeated(s);
        }
    }

}
