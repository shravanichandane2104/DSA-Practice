// 8 ms | 47.7 MB
class Solution {
    public int minInsertions(String s) {
        int need = 0;
        int insertions = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                if (need % 2 == 1) {
                    insertions++;
                    need--;
                }
                need += 2;
            } else {
                need--;

                if (need < 0) {
                    insertions++;
                    need = 1;
                }
            }
        }

        return insertions + need;
    }
}