package algorithms.sorting;

/**
 *  Класс {@code Shaker} содержит алгоритм сортировки Шелла.
 *  <p>
 *  Худшее время: зависит от данных.
 *  <p>
 *  Среднее время: зависит от данных.
 *  <p>
 *  Затраты памяти: O(n).
 *  <p>
 *  См. <a href="https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D0%A8%D0%B5%D0%BB%D0%BB%D0%B0">Сортировка Шелла</a>
 *
 *  @author Павел Федоров
 */

public class Shell extends Sort {

    public void algorithm(Comparable[] arr) {
        // 1. выбор шага:
        int step = 1;
        while (step < arr.length / 3) {
            step = 3 * step + 1;
        }
        // 2. сортировка:
        while (step > 0) {
            for (int i = step; i < arr.length; ++i) {
                Comparable x = arr[i];
                int j = i;
                while (j >= step && less(x, arr[j - step])) {
                    arr[j] = arr[j - step];
                    j -= step;
                }
                arr[j] = x;
            }
            step /= 3;
        }
    }

    @Override
    public String toString() {
        return "algorithms.sorting.Shell";
    }
}