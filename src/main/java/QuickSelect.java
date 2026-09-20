import java.util.Random;

public class QuickSelect {
    private static final Random RNG = new Random();

    public static int select(int[] a, int k, Metrics metrics) {
        if (a == null || a.length == 0 || k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Invalid input array or index k out of bounds.");
        }
        long startTime = System.nanoTime();
        int result = selectImpl(a, 0, a.length - 1, k, metrics);
        long endTime = System.nanoTime();
        metrics.setExecutionTimeNs(endTime - startTime);
        return result;
    }

    private static int selectImpl(int[] a, int low, int high, int k, Metrics metrics) {
        while (low <= high) {
            if (low == high) {
                return a[low];
            }
            metrics.enterRecursion();
            try {
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

                if (k < lt) {
                    high = lt - 1;
                } else if (k > gt) {
                    low = gt + 1;
                } else {
                    return a[k];
                }
            } finally {
                metrics.exitRecursion();
            }
        }
        return a[k];
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}