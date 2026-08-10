/*
Problem: 739. Daily Temperatures
Platform: LeetCode
Difficulty: Medium
Pattern: Monotonic Stack

Time Complexity: O(n)
Space Complexity: O(n)
*/

import java.util.Stack;

class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {

        int n = temperatures.length;
        int[] ans = new int[n];

        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!st.isEmpty() &&
                   temperatures[st.peek()] <= temperatures[i]) {
                st.pop();
            }

            if (!st.isEmpty()) {
                ans[i] = st.peek() - i;
            }

            st.push(i);
        }

        return ans;
    }
}
