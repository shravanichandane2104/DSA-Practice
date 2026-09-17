// 6 ms | 90.6 MB
class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int INF = 1000000000;

        // best[i] = minimum length of a valid subarray
        // completely inside indices [0 ... i-1]
        int[] best = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            best[i] = INF;
        }

        int left = 0;
        int sum = 0;
        int answer = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            // Shrink window if sum becomes greater than target
            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            // Found a subarray with sum = target
            if (sum == target) {

                int length = right - left + 1;

                // best[left] contains a previous
                // non-overlapping subarray
                if (best[left] != INF) {
                    answer = Math.min(answer, length + best[left]);
                }

                // Store current subarray as the best
                // subarray ending at or before right
                best[right + 1] = Math.min(best[right], length);

            } else {
                best[right + 1] = best[right];
            }
        }

        return answer == INF ? -1 : answer;
    }
}