/*
Problem: 187. Repeated DNA Sequences
Platform: LeetCode
Difficulty: Medium
Pattern: Fixed-Size Sliding Window + HashMap

Time Complexity: O(n)
Space Complexity: O(n)
*/

import java.util.*;

class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {

        HashMap<String, Integer> map = new HashMap<>();
        List<String> ans = new ArrayList<>();

        for (int i = 0; i <= s.length() - 10; i++) {

            String str = s.substring(i, i + 10);

            map.put(str, map.getOrDefault(str, 0) + 1);

            if (map.get(str) == 2) {
                ans.add(str);
            }
        }

        return ans;
    }
}
