package algorithms.sorting;

/**
 *  Класс {@code Merge} содержит алгоритм сортировки слиянием (низходящей).
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

public class Merge extends Sort {

    static Comparable[] buffer;

    protected void algorithm(Comparable[] arr) {
        buffer = new Comparable[arr.length];
        algorithm(arr, 0, arr.length - 1);
    }

    private void algorithm(Comparable[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = left + (right - left) / 2;
        algorithm(array, left, middle);
        algorithm(array, middle + 1, right);
        merge(array, left, middle, right);
    }

    void merge(Comparable[] array, int left, int middle, int right) {
        int i = left;
        int j = middle + 1;
        System.arraycopy(array, left, buffer, left, right + 1 - left);
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
        return "algorithms.sorting.Merge";
    }
}

