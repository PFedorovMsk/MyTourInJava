package algorithms.sorting;

/**
 *  Класс {@code Shaker} содержит алгоритм шейкерной сортировки.
 *  <p>
 *  Худшее время: O(n^2).
 *  <p>
 *  Среднее время: O(n^2).
 *  <p>
 *  Затраты памяти: O(n).
 *  <p>
 *  См. <a href="https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D0%BF%D0%B5%D1%80%D0%B5%D0%BC%D0%B5%D1%88%D0%B8%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%D0%BC">Сортировка перемешиванием</a>
 *
 *  @author Павел Федоров
 */

public class Shaker extends Sort {

    public void algorithm(Comparable[] arr) {
        int left = 0;
        int right = arr.length - 1;
        do {
            boolean swapped = false;
            for (int i = left; i < right; i++) {
                if (less(arr[i + 1], arr[i])) {
                    swap(arr, i, i + 1);
                    swapped = true;
                }
            }
            right--;
            if (!swapped) {
                break;
            }

            swapped = false;
            for (int i = right; i > left; i--) {
                if (less(arr[i + 1], arr[i])) {
                    swap(arr, i, i + 1);
                    swapped = true;
                }
            }
            left++;
            if (!swapped) {
                break;
            }
        } while (left <= right);
    }

    @Override
    public String toString() {
        return "algorithms.sorting.Shaker";
    }
}