// 33 ms | 135.2 MB
import java.util.*;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] squares = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(squares);

        int n = squares.length;
        int mid = n / 2;
        long ans = 0;

        // Subtract the smallest half
        for (int i = 0; i < mid; i++) {
            ans -= squares[i];
        }

        // Add the largest half
        for (int i = mid; i < n; i++) {
            ans += squares[i];
        }

        return ans;
    }
}