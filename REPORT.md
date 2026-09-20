# Empirical Evaluation and Theoretical Analysis Report

## 1. Introduction
This report analyzes the performance of three fundamental algorithms: **MergeSort**, **QuickSort**, and **QuickSelect**. We measure execution time ($ms$), maximum recursion depth, and total comparisons across varying input sizes ($n \in [10^3, 10^6]$).

## 2. Asymptotic Analysis Comparison

| Algorithm | Best Time Complexity | Average Time Complexity | Worst Time Complexity | Space Complexity (Auxiliary) |
| :--- | :--- | :--- | :--- | :--- |
| **MergeSort** | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n)$ |
| **QuickSort** | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n^2)$ | $\mathcal{O}(\log n)$ |
| **QuickSelect**| $\mathcal{O}(n)$ | $\mathcal{O}(n)$ | $\mathcal{O}(n^2)$ | $\mathcal{O}(1)$ or $\mathcal{O}(\log n)$ |

## 3. Empirical Results Analysis

### Execution Time (`time_vs_n.png`)
- **QuickSelect** exhibits linear performance $\mathcal{O}(n)$, significantly outperforming full-sorting algorithms as $n$ grows to $1,000,000$.
- **MergeSort** maintains predictable $\mathcal{O}(n \log n)$ time complexity across all input distributions.
- **QuickSort** shows strong average performance on random inputs due to cache locality, but performance degrades if pivot selection encounters worst-case distributions.

### Recursion Depth (`depth_vs_n.png`)
- **MergeSort** recursion depth strictly adheres to $\lceil \log_2 n \rceil$, maintaining low stack overhead.
- **QuickSort** depth stays within logarithmic bounds $\mathcal{O}(\log n)$ on average.
- **QuickSelect** displays recursion depth proportional to tree height during partitioning phases.

### Comparison Ratio (`ratio_vs_n.png`)
- The comparison ratio validates theoretical bounds. For QuickSelect, $C / n$ approaches a constant factor, while for MergeSort and QuickSort, $C / (n \log_2 n)$ remains stable across scaling sizes.

## 4. Conclusion
Empirical benchmarks strictly align with theoretical expectations. QuickSelect is the optimal choice for $k$-th element selection tasks, whereas MergeSort offers guaranteed stable performance without worst-case degradation risks.