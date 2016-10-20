package algorithms.sorting;

/**
 *  Класс {@code MergeOpt} содержит алгоритм сортировки слиянием
 *  с использованием сортировки вставками для малых частей.
 *  <p>
 *  Худшее время: O(n*log(n)).
 *  <p>
 *  Среднее время: O(n*log(n)).
 *  <p>
 *  Затраты памяти: O(n).
 *  <p>
 *  См. <a href="https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D1%81%D0%BB%D0%B8%D1%8F%D0%BD%D0%B8%D0%B5%D0%BC">Сортировка слиянием</a>
 *
 *  @author Павел Федоров
 */

public class MergeOpt extends Sort {

    private static final int CUTOFF = 7;

    protected void algorithm(Comparable[] array) {
        Comparable[] buffer = array.clone();
        algorithm(buffer, array, 0, array.length - 1);
    }

    private void algorithm(Comparable[] buffer, Comparable[] array, int left, int right) {
        if (right <= left + CUTOFF) {
            insertionSort(array, left, right);
            return;
        }
        int middle = left + (right - left) / 2;
        algorithm(array, buffer, left, middle);
        algorithm(array, buffer, middle + 1, right);

        if (less(buffer[middle], buffer[middle + 1])) {
            System.arraycopy(buffer, left, array, left, right - left + 1);
            return;
        }
        merge(buffer, array, left, middle, right);
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

    private void merge(Comparable[] buffer, Comparable[] array, int left, int middle, int right) {
        int i = left;
        int j = middle + 1;
        for (int k = left; k <= right; k++) {
            if (i > middle) {
                array[k] = buffer[j++];
            } else if (j > right) {
                array[k] = buffer[i++];
            } else if (less(buffer[j], buffer[i])) {
                array[k] = buffer[j++];
            } else {
                array[k] = buffer[i++];
            }
        }
    }

    @Override
    public String toString() {
        return "algorithms.sorting.MergeOpt";
    }
}


