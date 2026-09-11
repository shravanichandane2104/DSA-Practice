// 5 ms | 46.4 MB
class Solution {
    public int totalNumbers(int[] digits) {

        Set<Integer> set = new HashSet<>();

        int n = digits.length;

        for (int i = 0; i < n; i++) {

            // Last digit must be even
            if (digits[i] % 2 != 0) {
                continue;
            }

            for (int j = 0; j < n; j++) {

                if (j == i) {
                    continue;
                }

                for (int k = 0; k < n; k++) {

                    // No leading zero
                    // Don't reuse same position
                    if (digits[k] == 0 || k == i || k == j) {
                        continue;
                    }

                    int number = digits[k] * 100
                               + digits[j] * 10
                               + digits[i];

                    set.add(number);
                }
            }
        }

        return set.size();
    }
}