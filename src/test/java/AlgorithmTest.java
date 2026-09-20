import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmTest {

    @Test
    public void testCorrectness() {
        Random rng = new Random();
        for (int i = 0; i < 100; i++) {
            int n = 1000;
            int[] a1 = rng.ints(n).toArray();
            int[] a2 = a1.clone();
            int[] a3 = a1.clone();

            Arrays.sort(a1);
            MergeSort.sort(a2, new Metrics());
            QuickSort.sort(a3, new Metrics());

            assertArrayEquals(a1, a2);
            assertArrayEquals(a1, a3);
        }
    }

    @Test
    public void testEdgeCases() {
        Metrics m = new Metrics();

        int[] empty = new int[0];
        MergeSort.sort(empty, m);
        QuickSort.sort(empty, m);
        assertEquals(0, empty.length);

        int[] single = {42};
        MergeSort.sort(single, m);
        QuickSort.sort(single, m);
        assertEquals(42, single[0]);

        int[] equal = {5, 5, 5, 5, 5};
        QuickSort.sort(equal, m);
        assertArrayEquals(new int[]{5, 5, 5, 5, 5}, equal);
    }

    @Test
    public void testQuickSortDepthOnSorted() {
        int n = 100000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i;

        Metrics m = new Metrics();
        QuickSort.sort(a, m);

        double maxAllowed = 2 * (Math.log(n) / Math.log(2));
        assertTrue(m.getMaxDepth() <= maxAllowed);
    }

    @Test
    public void testQuickSelect() {
        Random rng = new Random();
        for (int i = 0; i < 100; i++) {
            int n = 500;
            int[] a = rng.ints(n).toArray();
            int[] sorted = a.clone();
            Arrays.sort(sorted);

            int k = rng.nextInt(n);
            int val = QuickSelect.select(a, k, new Metrics());

            assertEquals(sorted[k], val);
        }
    }

    @Test
    public void testQuickSelectInvalid() {
        Metrics m = new Metrics();
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[0], 0, m));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[]{1, 2}, 5, m));
    }
}