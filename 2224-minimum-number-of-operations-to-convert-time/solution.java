// 1 ms | 42.5 MB

class Solution {
    public int convertTime(String current, String correct) {
        int curr = Integer.parseInt(current.substring(0, 2)) * 60
                 + Integer.parseInt(current.substring(3, 5));

        int corr = Integer.parseInt(correct.substring(0, 2)) * 60
                 + Integer.parseInt(correct.substring(3, 5));

        int diff = corr - curr;
        int operations = 0;

        int[] changes = {60, 15, 5, 1};

        for (int change : changes) {
            operations += diff / change;
            diff %= change;
        }

        return operations;
    }
}
