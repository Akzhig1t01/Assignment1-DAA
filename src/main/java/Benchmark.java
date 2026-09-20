import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Benchmark {
    private static final int[] SIZES = {1000, 10000, 100000, 1000000};
    private static final String[] TYPES = {"random", "sorted", "duplicates"};
    private static final int RUNS = 5;

    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("results.csv"))) {
            writer.println("algorithm,input,n,time_ms,comparisons,max_depth");

            for (int n : SIZES) {
                for (String type : TYPES) {
                    runBenchmark("MergeSort", n, type, writer);
                    runBenchmark("QuickSort", n, type, writer);
                    runBenchmark("QuickSelect", n, type, writer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runBenchmark(String algorithm, int n, String type, PrintWriter writer) {
        long[] times = new long[RUNS];
        long[] comparisons = new long[RUNS];
        int[] depths = new int[RUNS];

        for (int r = 0; r < RUNS; r++) {
            int[] data = generateData(n, type);
            Metrics metrics = new Metrics();

            if (algorithm.equals("MergeSort")) {
                MergeSort.sort(data, metrics);
            } else if (algorithm.equals("QuickSort")) {
                QuickSort.sort(data, metrics);
            } else if (algorithm.equals("QuickSelect")) {
                QuickSelect.select(data, n / 2, metrics);
            }

            times[r] = metrics.getExecutionTimeNs();
            comparisons[r] = metrics.getComparisons();
            depths[r] = metrics.getMaxDepth();
        }

        Arrays.sort(times);
        Arrays.sort(comparisons);
        Arrays.sort(depths);

        int medianIndex = RUNS / 2;
        double medianTimeMs = times[medianIndex] / 1_000_000.0;
        long medianComparisons = comparisons[medianIndex];
        int medianDepth = depths[medianIndex];

        writer.printf("%s,%s,%d,%.4f,%d,%d\n", algorithm, type, n, medianTimeMs, medianComparisons, medianDepth);
    }

    private static int[] generateData(int n, String type) {
        int[] a = new int[n];
        Random rng = new Random();
        if (type.equals("random")) {
            for (int i = 0; i < n; i++) a[i] = rng.nextInt();
        } else if (type.equals("sorted")) {
            for (int i = 0; i < n; i++) a[i] = i;
        } else if (type.equals("duplicates")) {
            for (int i = 0; i < n; i++) a[i] = rng.nextInt(10);
        }
        return a;
    }
}