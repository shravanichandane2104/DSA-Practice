// 127 ms | 223.7 MB
import java.util.*;

class Solution {
    int n, LOG;
    List<Integer>[] adj;
    int[] tin, tout, depth, parent, baseMask;
    int[][] up;
    int[] bit;
    char[] chars;

    public List<Boolean> palindromePath(int n, int[][] edges,
                                        String s, String[] queries) {
        this.n = n;
        chars = s.toCharArray();

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        baseMask = new int[n];

        int[] order = new int[n];
        int[] stack = new int[n];
        int top = 0, count = 0;

        Arrays.fill(parent, -1);
        parent[0] = 0;
        stack[top++] = 0;

        // Iterative DFS
        while (top > 0) {
            int u = stack[--top];
            tin[u] = count;
            order[count++] = u;

            for (int v : adj[u]) {
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        // Prefix XOR masks from root
        for (int i = 0; i < n; i++) {
            int u = order[i];
            int ownMask = 1 << (chars[u] - 'a');

            if (u == 0) {
                baseMask[u] = ownMask;
            } else {
                baseMask[u] = baseMask[parent[u]] ^ ownMask;
            }
        }

        // Subtree sizes; DFS preorder keeps subtrees contiguous
        int[] subtreeSize = new int[n];
        Arrays.fill(subtreeSize, 1);

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            subtreeSize[parent[u]] += subtreeSize[u];
        }

        for (int u = 0; u < n; u++) {
            tout[u] = tin[u] + subtreeSize[u];
        }

        // Binary lifting table for LCA
        up = new int[LOG][n];

        for (int u = 0; u < n; u++) {
            up[0][u] = parent[u];
        }

        for (int j = 1; j < LOG; j++) {
            for (int u = 0; u < n; u++) {
                up[j][u] = up[j - 1][up[j - 1][u]];
            }
        }

        // Fenwick XOR tree: n+2 avoids endpoint index overflow
        bit = new int[n + 2];

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int delta = (1 << (chars[u] - 'a'))
                          ^ (1 << (c - 'a'));

                if (delta != 0) {
                    // Apply delta to every node in u's subtree
                    update(tin[u] + 1, delta);
                    update(tout[u] + 1, delta);
                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int w = lca(u, v);

                int maskU = baseMask[u] ^ prefix(tin[u] + 1);
                int maskV = baseMask[v] ^ prefix(tin[v] + 1);

                int pathMask = maskU ^ maskV
                             ^ (1 << (chars[w] - 'a'));

                // At most one character may have odd frequency
                ans.add(pathMask == 0 ||
                        (pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }

    void update(int i, int value) {
        while (i < bit.length) {
            bit[i] ^= value;
            i += i & -i;
        }
    }

    int prefix(int i) {
        int result = 0;

        while (i > 0) {
            result ^= bit[i];
            i -= i & -i;
        }

        return result;
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return parent[u];
    }
}