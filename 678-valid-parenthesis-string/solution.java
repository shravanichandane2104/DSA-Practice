// 0 ms | 42.7 MB
class Solution {
    public boolean checkValidString(String s) {

        int low = 0;
        int high = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                low++;
                high++;
            } 
            else if (ch == ')') {
                low--;
                high--;
            } 
            else {
                // '*' can be ')' or '('
                low--;
                high++;
            }

            // Even the maximum possible balance is negative
            if (high < 0) {
                return false;
            }

            // Minimum balance cannot be negative
            low = Math.max(low, 0);
        }

        return low == 0;
    }
}