// 22 ms | 48 MB
import java.util.*;

class Solution {
    public List<String> splitWordsBySeparator(
            List<String> words, char separator) {

        List<String> ans = new ArrayList<>();

        for (String word : words) {
            String[] parts = word.split(
                java.util.regex.Pattern.quote(String.valueOf(separator))
            );

            for (String part : parts) {
                if (!part.isEmpty()) {
                    ans.add(part);
                }
            }
        }

        return ans;
    }
}