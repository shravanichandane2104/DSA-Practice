// 2 ms | 46.1 MB

class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];

        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(grid[i], '#');
        }

        // First row is free
        java.util.Arrays.fill(grid[0], '.');

        // Last column is free
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }

        String[] result = new String[m];

        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }

        return result;
    }
}
