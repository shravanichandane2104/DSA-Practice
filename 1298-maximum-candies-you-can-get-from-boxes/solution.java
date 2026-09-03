// 5 ms | 63.1 MB
class Solution {
    public int maxCandies(int[] status, int[] candies,
                          int[][] keys, int[][] containedBoxes,
                          int[] initialBoxes) {

        Queue<Integer> q = new ArrayDeque<>();

        boolean[] has = new boolean[status.length];
        boolean[] taken = new boolean[status.length];

        int ans = 0;

        // Initial boxes
        for (int box : initialBoxes) {
            has[box] = true;

            if (status[box] == 1) {
                q.offer(box);
                taken[box] = true;
            }
        }

        while (!q.isEmpty()) {

            int box = q.poll();

            // Collect candies
            ans += candies[box];

            // Get keys
            for (int key : keys[box]) {

                status[key] = 1;

                // If we already have this box, open it
                if (has[key] && !taken[key]) {
                    q.offer(key);
                    taken[key] = true;
                }
            }

            // Get contained boxes
            for (int newBox : containedBoxes[box]) {

                has[newBox] = true;

                // If box is already open, open it
                if (status[newBox] == 1 && !taken[newBox]) {
                    q.offer(newBox);
                    taken[newBox] = true;
                }
            }
        }

        return ans;
    }
}