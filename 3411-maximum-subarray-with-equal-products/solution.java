// 4 ms | 43.7 MB

class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            long product = 1;
            int gcd = 0;
            long lcm = 1;

            for (int j = i; j < n; j++) {
                int num = nums[j];

                product *= num;
                gcd = findGCD(gcd, num);
                lcm = lcm * num / findGCD((int) lcm, num);

                if (product == lcm * gcd) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
