import algorithms.sorting.Bubble;
import algorithms.sorting.Comb;
import algorithms.sorting.Insertion;
import algorithms.sorting.Merge;
import algorithms.sorting.MergeBU;
import algorithms.sorting.MergeOpt;
import algorithms.sorting.Quick;
import algorithms.sorting.Quick3;
import algorithms.sorting.QuickOpt;
import algorithms.sorting.Selection;
import algorithms.sorting.Shaker;
import algorithms.sorting.Shell;
import algorithms.sorting.Sort;
import algorithms.util.SortingComparison;

class Main {

    public static void main(String[] args) {
        sortCompare();
    }

    private static void sortCompare() {
        Sort[] algorithms = new Sort[]{new Shell(), new Comb(), new Merge(), new MergeBU(),
                new Quick(), new Quick3(), new QuickOpt()};
        int count = 200;
        for (int i = 2; i < 5; i++) {
            int size = (int) Math.pow(10, i);
            SortingComparison.fullTest(algorithms, size, size / 10, 10, count);
        }
    }
}
