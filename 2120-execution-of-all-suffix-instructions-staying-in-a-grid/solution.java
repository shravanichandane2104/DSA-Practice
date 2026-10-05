// 24 ms | 46.4 MB
class Solution {
    public int[] executeInstructions(int n, int[] startPos, String s) {
        int m = s.length();
        int[] ans = new int[m];

        for (int i = 0; i < m; i++) {
            int r = startPos[0];
            int c = startPos[1];
            int count = 0;

            for (int j = i; j < m; j++) {
                char ch = s.charAt(j);

                if (ch == 'L') c--;
                else if (ch == 'R') c++;
                else if (ch == 'U') r--;
                else if (ch == 'D') r++;

                if (r < 0 || r >= n || c < 0 || c >= n) {
                    break;
                }

                count++;
            }

            ans[i] = count;
        }

        return ans;
    }
}