package algorithms.sorting;

/**
 * Класс {@code Heap} содержит алгоритм пиромидальной сортировки.
 * <p>
 * Худшее время: O(n*log(n)).
 * <p>
 * Среднее время: O(n*log(n)).
 * <p>
 * Затраты памяти: O(1).
 * <p>
 * См. <a href="https://ru.wikipedia.org/wiki/%D0%9F%D0%B8%D1%80%D0%B0%D0%BC%D0%B8%D0%B4%D0%B0%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F_%D1%81%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0">Пиромидальнаясортировка</a>
 *
 * @author Павел Федоров
 */

public class Heap extends Sort {

    protected void algorithm(Comparable[] array) {
        int n = array.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(array, k, n);
        }
        while (n > 1) {
            swap(array, 0, n-- - 1);
            sink(array, 1, n);
        }
    }

    private void sink(Comparable[] array, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(array[j - 1], array[j])) {
                j++;
            }
            if (!less(array[k - 1], array[j - 1])) {
                break;
            }
            swap(array, k - 1, j - 1);
            k = j;
        }
    }

    @Override
    public String toString() {
        return "algorithms.sorting.Heap";
    }
}
