// 2 ms | 110.2 MB

class Solution {
    public int sortPermutation(int[] nums) {
        int k = (1 << 30) - 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                k &= nums[i];
                k &= i;
            }
        }

        return k == (1 << 30) - 1 ? 0 : k;
    }
}
