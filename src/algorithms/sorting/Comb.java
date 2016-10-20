package algorithms.sorting;

/**
 *  Класс {@code Comb} содержит алгоритм "сортировки расчёской".
 *  <p>
 *  Худшее время: O(n^2).
 *  <p>
 *  Среднее время: O(n^2).
 *  <p>
 *  Затраты памяти: O(1).
 *  <p>
 *  См. <a href="https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D1%80%D0%B0%D1%81%D1%87%D1%91%D1%81%D0%BA%D0%BE%D0%B9">Сортировка расчёской</a>
 *
 *  @author Павел Федоров
 */

public class Comb extends Bubble {

    @Override
    protected void algorithm(Comparable[] arr) {
        int step = arr.length - 1;
        double factor = 1.2473309;
        while (step > 1) {
            for (int i = 0; i + step < arr.length; ++i) {
                if (less(arr[i + step], arr[i])) {
                    swap(arr, i, i + step);
                }
            }
            step /= factor;
        }
        super.algorithm(arr);
    }

    @Override
    public String toString() {
        return "algorithms.sorting.Comb";
    }
}