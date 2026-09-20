import java.util.Random;

public class QuickSort {
    private static final Random RNG = new Random();

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) {
            return;
        }
        long startTime = System.nanoTime();
        sortImpl(a, 0, a.length - 1, metrics);
        long endTime = System.nanoTime();
        metrics.setExecutionTimeNs(endTime - startTime);
    }

    private static void sortImpl(int[] a, int low, int high, Metrics metrics) {
        while (low < high) {
            metrics.enterRecursion();
            int pivotIndex = low + RNG.nextInt(high - low + 1);
            int pivot = a[pivotIndex];

            int lt = low;
            int gt = high;
            int i = low;

            while (i <= gt) {
                metrics.incrementComparisons();
                if (a[i] < pivot) {
                    swap(a, lt++, i++);
                } else if (a[i] > pivot) {
                    metrics.incrementComparisons();
                    swap(a, i, gt--);
                } else {
                    metrics.incrementComparisons();
                    i++;
                }
            }

            if (lt - low < high - gt) {
                sortImpl(a, low, lt - 1, metrics);
                metrics.exitRecursion();
                low = gt + 1;
            } else {
                sortImpl(a, gt + 1, high, metrics);
                metrics.exitRecursion();
                high = lt - 1;
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}