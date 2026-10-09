// 39 ms | 65.9 MB
import java.util.*;

class Solution {
    public List<List<String>> displayTable(List<List<String>> orders) {
        TreeSet<String> foods = new TreeSet<>();
        TreeMap<Integer, Map<String, Integer>> tables = new TreeMap<>();

        for (List<String> order : orders) {
            int table = Integer.parseInt(order.get(1));
            String food = order.get(2);

            foods.add(food);

            tables.putIfAbsent(table, new HashMap<>());
            Map<String, Integer> foodCount = tables.get(table);

            foodCount.put(food, foodCount.getOrDefault(food, 0) + 1);
        }

        List<List<String>> ans = new ArrayList<>();

        List<String> header = new ArrayList<>();
        header.add("Table");
        header.addAll(foods);
        ans.add(header);

        for (int table : tables.keySet()) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(table));

            Map<String, Integer> foodCount = tables.get(table);

            for (String food : foods) {
                row.add(String.valueOf(foodCount.getOrDefault(food, 0)));
            }

            ans.add(row);
        }

        return ans;
    }
}