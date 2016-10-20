package algorithms.sorting;

/**
 *  Класс {@code Selection} содержит алгоритм сортировки выбором.
 *  <p>
 *  Худшее время: O(n^2).
 *  <p>
 *  Среднее время: O(n^2).
 *  <p>
 *  Затраты памяти: O(n).
 *  <p>
 *  См. <a href="https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D0%B2%D1%8B%D0%B1%D0%BE%D1%80%D0%BE%D0%BC">Сортировка выбором</a>
 *
 *  @author Павел Федоров
 */

public class Selection extends Sort {

    public void algorithm(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; ++i) {
            int indexOfMin = i;
            for (int j = i + 1; j < arr.length; ++j) {
                if (less(arr[j], arr[indexOfMin])) {
                    indexOfMin = j;
                }
            }
            swap(arr, i, indexOfMin);
        }
    }

    @Override
    public String toString() {
        return "algorithms.sorting.Selection";
    }
}