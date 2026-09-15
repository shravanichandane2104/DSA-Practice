// 116 ms | 62.3 MB
class Solution {
    public int maxPalindromes(String s, int k) {

        int n = s.length();

        // palindrome[i][j] = true if s[i...j] is a palindrome
        boolean[][] palindrome = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {

                if (s.charAt(i) == s.charAt(j) &&
                    (j - i <= 2 || palindrome[i + 1][j - 1])) {

                    palindrome[i][j] = true;
                }
            }
        }

        // dp[i] = maximum number of valid palindromes
        // we can select from first i characters
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {

            // Skip current character
            dp[i] = dp[i - 1];

            // Try every palindrome ending at i - 1
            for (int j = 0; j < i; j++) {

                if (i - j >= k && palindrome[j][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n];
    }
}