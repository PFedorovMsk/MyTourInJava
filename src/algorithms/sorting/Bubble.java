package algorithms.sorting;

/**
 *  Класс {@code Bubble} содержит алгоритм "сортировки пузырьком".
 *  <p>
 *  Худшее время: O(n^2).
 *  <p>
 *  Среднее время: O(n^2).
 *  <p>
 *  Затраты памяти: O(1).
 *  <p>
 *  См. <a href="https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D0%BF%D1%83%D0%B7%D1%8B%D1%80%D1%8C%D0%BA%D0%BE%D0%BC">Сортировка пузырьком</a>
 *
 *  @author Павел Федоров
 */

public class Bubble extends Sort {

    protected void algorithm(Comparable[] arr) {
        boolean swapped;
        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (less(arr[j + 1], arr[j])) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "algorithms.sorting.Bubble";
    }
}
