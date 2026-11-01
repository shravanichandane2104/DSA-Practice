// 70 ms | 279.4 MB
class Solution {
    static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long[][] fromLeft = new long[m][n];
        long[][] fromTop = new long[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                long left = fromLeft[i][j];
                long top = fromTop[i][j];

                // Starting cell
                if (i == 0 && j == 0) {
                    left = 1;
                }

                // Empty cell
                if (grid[i][j] == 0) {

                    long ways = (left + top) % MOD;

                    // Destination reached
                    if (i == m - 1 && j == n - 1) {
                        return (int) ways;
                    }

                    // Move right
                    if (j + 1 < n) {
                        fromLeft[i][j + 1] =
                            (fromLeft[i][j + 1] + ways) % MOD;
                    }

                    // Move down
                    if (i + 1 < m) {
                        fromTop[i + 1][j] =
                            (fromTop[i + 1][j] + ways) % MOD;
                    }

                } else {

                    // Mirror: entered from left -> reflect down
                    if (i + 1 < m) {
                        fromTop[i + 1][j] =
                            (fromTop[i + 1][j] + left) % MOD;
                    }

                    // Mirror: entered from top -> reflect right
                    if (j + 1 < n) {
                        fromLeft[i][j + 1] =
                            (fromLeft[i][j + 1] + top) % MOD;
                    }
                }
            }
        }

        return 0;
    }
}