package algorithms.sorting;

/**
 *  Класс {@code Insertion} содержит алгоритм сортировки вставками.
 *  <p>
 *  Худшее время: O(n^2).
 *  <p>
 *  Среднее время: O(n^2).
 *  <p>
 *  Затраты памяти: O(n).
 *  <p>
 *  См. <a href="https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D0%B2%D1%81%D1%82%D0%B0%D0%B2%D0%BA%D0%B0%D0%BC%D0%B8">Сортировка вставками</a>
 *
 *  @author Павел Федоров
 */

public class Insertion extends Sort {

    protected void algorithm(Comparable[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            Comparable x = arr[i];
            int j = i;
            while (j > 0 && less(x, arr[j - 1])) {
                arr[j] = arr[j - 1];
                --j;
            }
            arr[j] = x;
        }
    }

    @Override
    public String toString() {
        return "algorithms.sorting.Insertion";
    }
}