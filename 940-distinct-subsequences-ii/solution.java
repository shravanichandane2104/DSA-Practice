// 6 ms | 43.2 MB
class Solution {
    public int distinctSubseqII(String s) {

        final long MOD = 1_000_000_007;

        long[] end = new long[26];

        for (char c : s.toCharArray()) {

            int idx = c - 'a';

            long total = 0;

            for (long x : end) {
                total = (total + x) % MOD;
            }

            end[idx] = (total + 1) % MOD;
        }

        long ans = 0;

        for (long x : end) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }
}