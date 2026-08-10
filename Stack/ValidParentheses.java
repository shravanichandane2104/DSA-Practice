/*
Problem: 20. Valid Parentheses
Platform: LeetCode
Difficulty: Easy
Pattern: Stack

Time Complexity: O(n)
Space Complexity: O(n)
*/

import java.util.Stack;

class ValidParentheses {
    public boolean isValid(String s) {

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char current = s.charAt(i);

            if (current == '[' || current == '(' || current == '{') {
                st.push(current);
            } else {

                if (st.isEmpty()) {
                    return false;
                }

                char open = st.pop();

                if ((current == '}' && open == '{') ||
                    (current == ')' && open == '(') ||
                    (current == ']' && open == '[')) {
                    continue;
                } else {
                    return false;
                }
            }
        }

        return st.isEmpty();
    }
}
