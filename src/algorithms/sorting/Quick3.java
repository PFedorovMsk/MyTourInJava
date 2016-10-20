package algorithms.sorting;

/**
 *  Класс {@code Quick3} содержит алгоритм быстрой сортировки (с делением массива на 3 части).
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

public class Quick3 extends Quick {

    @Override
    void algorithm(Comparable[] arr, int left, int right) {
        if (right <= left) {
            return;
        }
        int lt = left;
        int i = left + 1;
        int gt = right;
        Comparable value = arr[left];
        while (i <= gt) {
            if (less(arr[i], value)) {
                swap(arr, lt++, i++);
            } else if (more(arr[i], value)) {
                swap(arr, i, gt--);
            } else {
                i++;
            }
        }
        algorithm(arr, left, lt - 1);
        algorithm(arr, gt + 1, right);
    }

    @Override
    public String toString() {
        return "algorithms.sorting.Quick3";
    }
}