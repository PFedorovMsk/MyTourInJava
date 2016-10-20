package algorithms.sorting;

/**
 *  Класс {@code Sort} - абстрактный класс для классов-сортировщиков.
 *
 *  @author Павел Федоров
 */

@SuppressWarnings("unchecked")
public abstract class Sort {

    public static boolean isSorted(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; ++i) {
            if (!less(arr[i], arr[i + 1])) {
                return false;
            }
        }
        return true;
    }

    static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    static boolean more(Comparable a, Comparable b) {
        return a.compareTo(b) > 0;
    }

    static boolean equal(Comparable a, Comparable b) {
        return a.compareTo(b) == 0;
    }

    static void swap(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void show(Comparable[] arr) {
        for (Comparable element : arr) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    protected abstract void algorithm(Comparable[] arr);

    public long sort(Comparable[] arr) {
        long timeBegin = System.nanoTime();
        algorithm(arr);
        long timeEnd = System.nanoTime();
        assert isSorted(arr);
        return timeEnd - timeBegin;
    }

    public String toString() {
        return "algorithms.sorting.Sort[Abstract]";
    }
}
