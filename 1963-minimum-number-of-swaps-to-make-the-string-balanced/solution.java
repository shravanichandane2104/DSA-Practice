// 14 ms | 59.4 MB
class Solution {
    public int minSwaps(String s) {

        int balance = 0;
        int swaps = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '[') {
                balance++;
            } else {
                balance--;
            }

            // Invalid prefix
            if (balance < 0) {
                swaps++;
                balance += 2;
            }
        }

        return swaps;
    }
}