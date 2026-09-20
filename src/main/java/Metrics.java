public class Metrics {
    private long comparisons;
    private int maxDepth;
    private int currentDepth;
    private long executionTimeNs;

    public void reset() {
        this.comparisons = 0;
        this.maxDepth = 0;
        this.currentDepth = 0;
        this.executionTimeNs = 0;
    }

    public void incrementComparisons() {
        this.comparisons++;
    }

    public void enterRecursion() {
        this.currentDepth++;
        if (this.currentDepth > this.maxDepth) {
            this.maxDepth = this.currentDepth;
        }
    }

    public void exitRecursion() {
        this.currentDepth--;
    }

    public void setExecutionTimeNs(long ns) {
        this.executionTimeNs = ns;
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public long getExecutionTimeNs() {
        return executionTimeNs;
    }

    public double getExecutionTimeMs() {
        return executionTimeNs / 1_000_000.0;
    }
}