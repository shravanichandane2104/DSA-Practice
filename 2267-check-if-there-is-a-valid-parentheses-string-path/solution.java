// 71 ms | 49 MB
class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int len = m + n - 1;

        // Valid parentheses string must have even length
        if (len % 2 == 1) {
            return false;
        }

        boolean[][][] dp = new boolean[m][n][len + 1];

        // Starting cell must be '('
        if (grid[0][0] == '(') {
            dp[0][0][1] = true;
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                if (r == 0 && c == 0) {
                    continue;
                }

                for (int balance = 0; balance <= len; balance++) {

                    if (grid[r][c] == '(') {

                        // Previous balance = balance - 1
                        if (balance > 0) {

                            if (r > 0 && dp[r - 1][c][balance - 1]) {
                                dp[r][c][balance] = true;
                            }

                            if (c > 0 && dp[r][c - 1][balance - 1]) {
                                dp[r][c][balance] = true;
                            }
                        }

                    } else {

                        // Current ')' decreases balance
                        if (balance + 1 <= len) {

                            if (r > 0 && dp[r - 1][c][balance + 1]) {
                                dp[r][c][balance] = true;
                            }

                            if (c > 0 && dp[r][c - 1][balance + 1]) {
                                dp[r][c][balance] = true;
                            }
                        }
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}