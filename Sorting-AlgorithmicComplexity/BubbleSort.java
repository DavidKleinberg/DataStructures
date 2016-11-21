/**
    The basic Bubble Sort algorithm.

    @param <T> Element type.
*/
public final class BubbleSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {

    // Helper to make code more readable.
    private boolean greater(T a, T b) {
        return b.compareTo(a) < 0;
    }

    @Override
    public void sort(Array<T> array) {

        for (int n = 0; n < array.length() - 1; n++) {
            int swaps = 0;
            for (int i = 0; i < array.length() - 1; i++) {
                if (this.greater(array.get(i), array.get(i + 1))) {
                    T t = array.get(i);
                    array.put(i, array.get(i + 1));
                    array.put(i + 1, t);
                    swaps++;
                }
            }
            if (swaps == 0) {
                break;
            }
        }
    }

    @Override
    public String name() {
        return "Bubble Sort";
    }
}