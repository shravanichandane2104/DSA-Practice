// 65 ms | 231.4 MB
import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        char[] ch = labels.toCharArray();

        // State: node, last character, consecutive count
        int[][] dist = new int[n][k + 1];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2])
        );

        int startCount = 1;
        if (startCount > k) return -1;

        dist[0][1] = 0;
        pq.offer(new int[]{0, 1, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int u = cur[0];
            int count = cur[1];
            int cost = cur[2];

            if (cost != dist[u][count]) continue;

            if (u == n - 1) return cost;

            for (int[] e : adj[u]) {
                int v = e[0];
                int weight = e[1];

                int newCount = (ch[u] == ch[v]) ? count + 1 : 1;

                if (newCount > k) continue;

                int newCost = cost + weight;

                if (newCost < dist[v][newCount]) {
                    dist[v][newCount] = newCost;
                    pq.offer(new int[]{v, newCount, newCost});
                }
            }
        }

        return -1;
    }
}