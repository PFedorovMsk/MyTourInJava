package algorithms.sorting;

/**
 *  Класс {@code QuickOpt} содержит оптимизированный алгоритм быстрой сортировки.
 *  <p>
 *  См. <a href="http://algs4.cs.princeton.edu/23quicksort/QuickX.java.html">QuickX.java</a>
 */

public class QuickOpt extends Sort {

    private static final int INSERTION_SORT_CUTOFF = 8;
    private static final int MEDIAN_OF_3_CUTOFF = 40;

    protected void algorithm(Comparable[] array) {
        algorithm(array, 0, array.length - 1);
    }

    private void algorithm(Comparable[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= INSERTION_SORT_CUTOFF) {
            insertionSort(arr, left, right);
            return;
        } else if (n <= MEDIAN_OF_3_CUTOFF) {
            int m = median3(arr, left, left + n / 2, right);
            swap(arr, m, left);
        } else {
            int eps = n / 8;
            int middle = left + n / 2;
            int m1 = median3(arr, left, left + eps, left + 2 * eps);
            int m2 = median3(arr, middle - eps, middle, middle + eps);
            int m3 = median3(arr, right - 2 * eps, right - eps, right);
            int ninther = median3(arr, m1, m2, m3);
            swap(arr, ninther, left);
        }

        int i = left;
        int j = right + 1;
        int p = i;
        int q = j;
        Comparable v = arr[left];
        while (true) {
            while (less(arr[++i], v)) {
                if (i == right) {
                    break;
                }
            }
            while (less(v, arr[--j])) {
                if (j == left) {
                    break;
                }
            }
            if (i == j && equal(arr[i], v)) {
                swap(arr, ++p, i);
            }
            if (i >= j) {
                break;
            }
            swap(arr, i, j);
            if (equal(arr[i], v)) {
                swap(arr, ++p, i);
            }
            if (equal(arr[j], v)) {
                swap(arr, --q, j);
            }
        }

        i = j + 1;
        for (int k = left; k <= p; k++) {
            swap(arr, k, j--);
        }
        for (int k = right; k >= q; k--) {
            swap(arr, k, i++);
        }

        algorithm(arr, left, j);
        algorithm(arr, i, right);
    }

    private int partition(Comparable[] arr, int left, int right) {
        int i = left;
        int j = right + 1;
        Comparable value = arr[left];
        while (true) {
            while (less(arr[++i], value)) {
                if (i == right) {
                    break;
                }
            }
            while (less(value, arr[--j])) {
                if (j == left) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(arr, i, j);
        }
        swap(arr, left, j);
        return j;
    }

    private void insertionSort(Comparable[] array, int left, int right) {
        for (int i = left + 1; i < right; ++i) {
            Comparable x = array[i];
            int j = i;
            while (j > 0 && less(x, array[j - 1])) {
                array[j] = array[j - 1];
                --j;
            }
            array[j] = x;
        }
    }

    private int median3(Comparable[] array, int i, int j, int k) {
        return (less(array[i], array[j]) ?
                (less(array[j], array[k]) ? j : less(array[i], array[k]) ? k : i) :
                (less(array[k], array[j]) ? j : less(array[k], array[i]) ? k : i));
    }

    @Override
    public String toString() {
        return "algorithms.sorting.QuickOpt";
    }
}

