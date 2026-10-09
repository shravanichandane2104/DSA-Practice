// 38 ms | 77 MB
import java.util.*;

class Solution {
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        long[] pairs = new long[n - 1];

        for (int i = 0; i < n - 1; i++) {
            pairs[i] = (long) weights[i] + weights[i + 1];
        }

        Arrays.sort(pairs);

        long minScore = 0;
        long maxScore = 0;

        for (int i = 0; i < k - 1; i++) {
            minScore += pairs[i];
            maxScore += pairs[n - 2 - i];
        }

        return maxScore - minScore;
    }
}