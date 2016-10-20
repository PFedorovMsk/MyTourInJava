package algorithms.sorting;

/**
 *  Класс {@code MergeBU} содержит алгоритм сортировки слиянием (восходящей).
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

public class MergeBU extends Merge {

    @Override
    protected void algorithm(Comparable[] arr) {
        int arrSize = arr.length;
        buffer = new Comparable[arr.length];
        for (int size = 1; size < arrSize; size *= 2) {
            for (int left = 0; left < arrSize - size; left += 2 * size) {
                merge(arr, left, left + size - 1, Math.min(left + 2 * size - 1, arrSize - 1));
            }
        }
    }

    @Override
    public String toString() {
        return "algorithms.sorting.MergeBU";
    }
}
