// 140 ms | 221.9 MB

import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        int[] newParent = parent.clone();
        int[] prev = new int[n];
        int[] last = new int[26];
        Arrays.fill(last, -1);

        List<Integer> order = new ArrayList<>();

        int[] stack = new int[2 * n + 1];
        int top = 0;
        stack[top++] = 0;

        while (top > 0) {
            int event = stack[--top];
            int node = event / 2;
            int type = event % 2;

            int c = s.charAt(node) - 'a';

            if (type == 0) {
                prev[node] = last[c];

                if (last[c] != -1) {
                    newParent[node] = last[c];
                }

                last[c] = node;
                order.add(node);

                stack[top++] = node * 2 + 1;

                for (int child : children[node]) {
                    stack[top++] = child * 2;
                }
            } else {
                last[c] = prev[node];
            }
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        for (int i = n - 1; i > 0; i--) {
            int node = order.get(i);
            ans[newParent[node]] += ans[node];
        }

        return ans;
    }
}
