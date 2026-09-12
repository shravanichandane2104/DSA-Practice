// 165 ms | 169.9 MB
import java.util.*;

class Solution {

    static class Interval {
        int left, right, weight, index;

        Interval(int left, int right, int weight, int index) {
            this.left = left;
            this.right = right;
            this.weight = weight;
            this.index = index;
        }
    }

    static class State {
        long score;
        List<Integer> ids;

        State(long score, List<Integer> ids) {
            this.score = score;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        List<Interval> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> x = intervals.get(i);

            arr.add(new Interval(
                x.get(0),
                x.get(1),
                x.get(2),
                i
            ));
        }

        // Sort by start
        arr.sort((a, b) -> Integer.compare(a.left, b.left));

        State[][] memo = new State[n][5];

        State result = dp(arr, memo, 0, 4);

        int[] ans = new int[result.ids.size()];

        for (int i = 0; i < result.ids.size(); i++) {
            ans[i] = result.ids.get(i);
        }

        return ans;
    }

    private State dp(
        List<Interval> arr,
        State[][] memo,
        int i,
        int k
    ) {

        if (i == arr.size() || k == 0) {
            return new State(0, new ArrayList<>());
        }

        if (memo[i][k] != null) {
            return memo[i][k];
        }

        // Skip current interval
        State skip = dp(arr, memo, i + 1, k);

        // Take current interval
        Interval current = arr.get(i);

        int next = findNext(arr, i + 1, current.right);

        State nextState = dp(arr, memo, next, k - 1);

        List<Integer> ids = new ArrayList<>(nextState.ids);
        ids.add(current.index);

        Collections.sort(ids);

        State take = new State(
            current.weight + nextState.score,
            ids
        );

        memo[i][k] = better(take, skip);

        return memo[i][k];
    }

    // First interval whose start > current right
    private int findNext(
        List<Interval> arr,
        int start,
        int right
    ) {

        int low = start;
        int high = arr.size();

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr.get(mid).left > right) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private State better(State a, State b) {

        if (a.score > b.score) {
            return a;
        }

        if (b.score > a.score) {
            return b;
        }

        // Lexicographically smaller
        return compare(a.ids, b.ids) < 0 ? a : b;
    }

    private int compare(
        List<Integer> a,
        List<Integer> b
    ) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(
                    a.get(i),
                    b.get(i)
                );
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}