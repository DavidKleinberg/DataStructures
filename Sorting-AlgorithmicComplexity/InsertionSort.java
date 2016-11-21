/**
    The basic Insertion Sort algorithm.

    @param <T> Element type.
*/
public final class InsertionSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {

    // Helper to make code more readable.
    private boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    @Override
    public void sort(Array<T> array) {

        int x = 0;
        for (int n = 0; n < array.length(); n++) {
            for (int i = x; i > 0; i--) {
                if (this.less(array.get(i), array.get(i - 1))) {
                    T t = array.get(i);
                    array.put(i, array.get(i - 1));
                    array.put(i - 1, t);
                }
            }
            x++;
        }
    }

    @Override
    public String name() {
        return "Insertion Sort";
    }
}