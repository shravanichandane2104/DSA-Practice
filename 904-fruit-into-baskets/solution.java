// 49 ms | 70.3 MB
class Solution {
    public int totalFruit(int[] fruits) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int max = 0;

        for (int right = 0; right < fruits.length; right++) {

            map.put(fruits[right],
                    map.getOrDefault(fruits[right], 0) + 1);

            // More than 2 types
            while (map.size() > 2) {
                int x = fruits[left];

                map.put(x, map.get(x) - 1);

                if (map.get(x) == 0) {
                    map.remove(x);
                }

                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}