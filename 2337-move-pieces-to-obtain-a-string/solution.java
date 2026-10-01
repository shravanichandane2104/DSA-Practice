// 15 ms | 47.9 MB
class Solution {
    public boolean canChange(String start, String target) {

        int i = 0;
        int j = 0;
        int n = start.length();

        while (i < n || j < n) {

            // Skip empty spaces
            while (i < n && start.charAt(i) == '_') {
                i++;
            }

            while (j < n && target.charAt(j) == '_') {
                j++;
            }

            // Both reached end
            if (i == n && j == n) {
                return true;
            }

            // One reached end before the other
            if (i == n || j == n) {
                return false;
            }

            // Character order must be same
            if (start.charAt(i) != target.charAt(j)) {
                return false;
            }

            // L can only move left
            if (start.charAt(i) == 'L' && i < j) {
                return false;
            }

            // R can only move right
            if (start.charAt(i) == 'R' && i > j) {
                return false;
            }

            i++;
            j++;
        }

        return true;
    }
}