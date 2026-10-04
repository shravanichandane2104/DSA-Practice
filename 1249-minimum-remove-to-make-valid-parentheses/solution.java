// 19 ms | 47.4 MB
import java.util.*;

class Solution {
    public String minRemoveToMakeValid(String s) {

        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '(') {
                stack.push(i);
            } 
            else if (ch == ')') {

                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    sb.setCharAt(i, '#');
                }
            }
        }

        // Remove unmatched '('
        while (!stack.isEmpty()) {
            sb.setCharAt(stack.pop(), '#');
        }

        // Build final answer
        StringBuilder ans = new StringBuilder();

        for (char ch : sb.toString().toCharArray()) {
            if (ch != '#') {
                ans.append(ch);
            }
        }

        return ans.toString();
    }
}