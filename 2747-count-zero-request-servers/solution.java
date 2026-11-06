// 32 ms | 131.7 MB
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int m = queries.length;
        int[][] sortedQueries = new int[m][2];

        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        int[] count = new int[n + 1];
        int[] ans = new int[m];

        int left = 0, right = 0;
        int active = 0;

        for (int[] q : sortedQueries) {
            int time = q[0];

            while (right < logs.length && logs[right][1] <= time) {
                int server = logs[right][0];

                if (count[server] == 0) {
                    active++;
                }

                count[server]++;
                right++;
            }

            while (left < right && logs[left][1] < time - x) {
                int server = logs[left][0];

                count[server]--;

                if (count[server] == 0) {
                    active--;
                }

                left++;
            }

            ans[q[1]] = n - active;
        }

        return ans;
    }
}