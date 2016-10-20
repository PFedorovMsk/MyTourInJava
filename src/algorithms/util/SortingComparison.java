package algorithms.util;

import java.util.Random;

import algorithms.sorting.Sort;

public class SortingComparison {


    private static long time(Sort algorithm, Integer[] array) {
        return algorithm.sort(array);
    }

    private static void print(Sort[] algorithms, long[] times) {
        long min = Long.MAX_VALUE;
        for (long val : times) {
            if (val < min) {
                min = val;
            }
        }
        int percents[] = new int[times.length];
        for (int i = 0; i < times.length; ++i) {
            percents[i] = (int) (100.0 * (double) times[i] / (double) min);
        }

        int maxLength = 0;
        for (Sort alg : algorithms) {
            int length = alg.toString().length();
            if (length > maxLength) {
                maxLength = length;
            }
        }

        for (int i = 0; i < algorithms.length; ++i) {
            String comment = "   ";
            if (times[i] == min) {
                comment = " * ";
            }
            System.out.print(comment + algorithms[i]);
            for (int j = 0; j < maxLength - algorithms[i].toString().length(); ++j) {
                System.out.print(" ");
            }
            System.out.println(" ~ " + times[i] + " (" + percents[i] + "% of best)");
        }
        System.out.println();
    }

    public static void testOnReversedSortedArray(Sort[] algorithms, int arraySize) {
        TestArray.Randomized = false;
        TestArray.RandomSeed = 123456789;
        Integer[] array = TestArray.getReversedSortedArray(arraySize);
        long[] times = new long[algorithms.length];
        for (int i = 0; i < algorithms.length; ++i) {
            times[i] = time(algorithms[i], TestArray.getCopyOf(array));
        }
        System.out.println("Test on reversed array[" + arraySize + "]:");
        print(algorithms, times);
    }

    public static void testOnRandomArray(Sort[] algorithms, int arraySize) {
        TestArray.Randomized = false;
        TestArray.RandomSeed = 123456789;
        Integer[] array = TestArray.getRandomArray(arraySize);
        long[] times = new long[algorithms.length];
        for (int i = 0; i < algorithms.length; ++i) {
            times[i] = time(algorithms[i], TestArray.getCopyOf(array));
        }
        System.out.println("Test on random array[" + arraySize + "]:");
        print(algorithms, times);
    }

    public static void testOnNearlySortedArray(Sort[] algorithms, int arraySize, int k) {
        TestArray.Randomized = false;
        TestArray.RandomSeed = 123456789;
        Integer[] array = TestArray.getNearlySortedArray(arraySize, k);
        long[] times = new long[algorithms.length];
        for (int i = 0; i < algorithms.length; ++i) {
            times[i] = time(algorithms[i], TestArray.getCopyOf(array));
        }
        System.out.println("Test on nearly sorted (k = " + k + ") array[" + arraySize + "]:");
        print(algorithms, times);
    }

    public static void testOnFewUniqueArray(Sort[] algorithms, int arraySize, int uniqueCount) {
        TestArray.Randomized = false;
        TestArray.RandomSeed = 123456789;
        Integer[] array = TestArray.getFewUniqueArray(arraySize, uniqueCount);
        long[] times = new long[algorithms.length];
        for (int i = 0; i < algorithms.length; ++i) {
            times[i] = time(algorithms[i], TestArray.getCopyOf(array));
        }
        System.out.println("Test on array[" + arraySize + "] with few unique values (count = " +
                uniqueCount + "):");
        print(algorithms, times);
    }

    //--------------------------------------------------------------------------------------------//

    private static double[][] getPercentsInCols(long[][] table) {
        int rows = table.length;
        int cols = table[0].length;
        double[][] percentsInCol = new double[rows][cols];
        for (int j = 0; j < cols; j++) {
            long min = Long.MAX_VALUE;
            for (int i = 0; i < rows; i++) {
                if (table[i][j] < min) {
                    min = table[i][j];
                }
            }
            for (int i = 0; i < rows; i++) {
                long tmp = (long) (10000.0 * (double) table[i][j] / (double) min);
                percentsInCol[i][j] = (double) tmp / 10000.0;
            }
        }
        return percentsInCol;
    }

    private static void setValuesToTable(String[][] table, double[][] percentsInCol) {
        table[0][1] = "Random";
        table[0][2] = "Reversed";
        table[0][3] = "Nearly sorted";
        table[0][4] = "Few unique";
        for (int i = 1; i < table.length; i++) {
            for (int j = 1; j < table[0].length; j++) {
                if (Math.abs(1.0 - percentsInCol[i - 1][j - 1]) < 0.00000001) {
                    table[i][j] = "best";
                } else {
                    table[i][j] = percentsInCol[i - 1][j - 1] + " x best";
                }
            }
        }

        int maxLength = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 1; j < table[0].length; j++) {
                if (table[i][j].length() > maxLength) {
                    maxLength = table[i][j].length();
                }
            }
        }
        System.out.println();
        for (int i = 0; i < table.length; i++) {
            for (int j = 1; j < table[0].length; j++) {
                String str = new String(table[i][j]);
                for (int k = 0; k < maxLength - table[i][j].length(); k++) {
                    str += " ";
                }
                table[i][j] = new String(str);
            }
        }
    }

    private static void setLeftLabelsToTable(String[][] table, Sort[] algorithms) {
        int maxLength = "    Algorithm  \\  Array type    ".length();
        for (Sort algorithm : algorithms) {
            if (algorithm.toString().length() > maxLength) {
                maxLength = algorithm.toString().length();
            }
        }
        String str00 = "    Algorithm  \\  Array type    ";
        for (int i = 0; i < maxLength - "    Algorithm  \\  Array type    ".length(); ++i) {
            str00 += " ";
        }
        table[0][0] = str00;
        for (int i = 1; i < algorithms.length + 1; ++i) {
            table[i][0] = algorithms[i - 1].toString();
            for (int j = 0; j < maxLength - algorithms[i - 1].toString().length(); ++j) {
                table[i][0] += " ";
            }
        }
    }

    private static void printTable(Sort[] algorithms, long[][] times) {
        String[][] table = new String[algorithms.length + 1][5];
        setLeftLabelsToTable(table, algorithms);
        setValuesToTable(table, getPercentsInCols(times));
        for (int i = 0; i < table.length; i++) {
            String row = "";
            for (int j = 0; j < table[0].length; j++) {
                row += table[i][j] + " | ";
            }
            System.out.println(row);
            for (int k = 0; k < row.length() - 1; k++) {
                System.out.print("-");
            }
            System.out.println();
        }
    }

    public static void fullTest(Sort[] algorithms, int arraySize, int k, int uniqueCount) {
        fullTest(algorithms, arraySize, k, uniqueCount, 1);
    }

    public static void fullTest(Sort[] algorithms, int arraySize, int k, int uniqueCount, int count) {
        TestArray.Randomized = false;
        TestArray.RandomSeed = 123456789;
        Integer[] arrayRand = TestArray.getRandomArray(arraySize);
        Integer[] arrayRev = TestArray.getReversedSortedArray(arraySize);
        Integer[] arrayNear = TestArray.getNearlySortedArray(arraySize, k);
        Integer[] arrayFewUn = TestArray.getFewUniqueArray(arraySize, uniqueCount);

        long[][] times = new long[algorithms.length][4];

        int ss = 1;
        if (count > 100) {
            ss = count / 20;
        }
        System.out.print("SortingComparison. Full test with parameters: ");
        System.out.println("s = " + arraySize + ", k = " + k + ", c = " + uniqueCount);
        System.out.println("(s - size of arrays, k - parameter of nearly sorting, c - count of unique values");
        System.out.print("Started:.");
        for (int p = 0; p < count; p++) {
            for (int i = 0; i < algorithms.length; ++i) {
                times[i][0] += time(algorithms[i], TestArray.getCopyOf(arrayRand));
                times[i][1] += time(algorithms[i], TestArray.getCopyOf(arrayRev));
                times[i][2] += time(algorithms[i], TestArray.getCopyOf(arrayNear));
                times[i][3] += time(algorithms[i], TestArray.getCopyOf(arrayFewUn));
            }
            if (p % ss == 0) {
                System.out.print(".");
            }
        }
        System.out.println("Finished");
        for (int i = 0; i < algorithms.length; ++i) {
            times[i][0] /= count;
            times[i][1] /= count;
            times[i][2] /= count;
            times[i][3] /= count;
        }
        printTable(algorithms, times);
        System.out.println();
    }
}

class TestArray {

    public static long RandomSeed = 0;
    public static boolean Randomized = true;

    public static Integer[] getReversedSortedArray(int size) {
        Integer[] arr = new Integer[size];
        for (int i = size - 1; i >= 0; --i) {
            arr[i] = size - i - 1;
        }
        return arr;
    }

    public static Integer[] getRandomArray(int size) {
        Integer[] arr = new Integer[size];
        long seed = System.currentTimeMillis();
        if (!Randomized) {
            seed = RandomSeed;
        }
        Random rand = new Random(seed);
        int max = 100000;
        int min = -max;
        for (int i = 0; i < size; ++i) {
            arr[i] = rand.nextInt((max - min) + 1) + min;
        }
        return arr;
    }

    public static Integer[] getNearlySortedArray(int size, int count) {
        Integer[] arr = new Integer[size];
        long seed = System.currentTimeMillis();
        if (!Randomized) {
            seed = RandomSeed;
        }
        Random rand = new Random(seed);
        int max = 100000;
        int min = -max;
        int step = size / count;
        if (size - step * count > 0.5 * step) {
            ++count;
        }
        int minMaxStep = (max - min) / count;

        int j = 1;
        max = min + minMaxStep;
        for (int i = 0; i < size; ++i) {
            if (i == j * step) {
                ++j;
                min = max + 1;
                max = min + minMaxStep;
            }
            arr[i] = rand.nextInt((max - min) + 1) + min;
        }
        return arr;
    }

    public static Integer[] getFewUniqueArray(int size, int uniqueCount) {
        Integer[] arr = new Integer[size];
        Integer[] uniqueValues = new Integer[uniqueCount];
        for (int i = 0; i < uniqueCount; ++i) {
            uniqueValues[i] = i - uniqueCount / 2;
        }
        long seed = System.currentTimeMillis();
        if (!Randomized) {
            seed = RandomSeed;
        }
        Random rand = new Random(seed);
        for (int i = 0; i < size; ++i) {
            int j = rand.nextInt(uniqueCount);
            arr[i] = uniqueValues[j];
        }
        return arr;
    }

    public static Integer[] getCopyOf(Integer[] array) {
        Integer[] cpArray = new Integer[array.length];
        System.arraycopy(array, 0, cpArray, 0, array.length);
        return cpArray;
    }
}