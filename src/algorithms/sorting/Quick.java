package algorithms.sorting;

import java.util.Random;

/**
 *  Класс {@code Quick} содержит алгоритм быстрой сортировки.
 *  <p>
 *  Худшее время: O(n^2).
 *  <p>
 *  Среднее время: O(n*log(n)).
 *  <p>
 *  Затраты памяти: O(n).
 *  <p>
 *  См. <a href="https://ru.wikipedia.org/wiki/%D0%91%D1%8B%D1%81%D1%82%D1%80%D0%B0%D1%8F_%D1%81%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0">Быстрая сортировка</a>
 *
 *  @author Павел Федоров
 */

public class Quick extends Sort {

    private static void shuffleArray(Comparable[] array) {
        Random rnd = new Random(System.currentTimeMillis());
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            swap(array, i, index);
        }
    }

    protected void algorithm(Comparable[] arr) {
        shuffleArray(arr);
        algorithm(arr, 0, arr.length - 1);
    }

    void algorithm(Comparable[] arr, int left, int right) {
        if (right <= left) {
            return;
        }
        int j = partition(arr, left, right);
        algorithm(arr, left, j - 1);
        algorithm(arr, j + 1, right);
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

    @Override
    public String toString() {
        return "algorithms.sorting.Quick";
    }
}
