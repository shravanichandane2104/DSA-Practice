// 31 ms | 53.2 MB
import java.util.*;

class Solution {

    TreeSet<String> set = new TreeSet<>();

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        return new ArrayList<>(set);
    }

    private void dfs(String exp) {

        int close = exp.indexOf('}');

        // No braces left
        if (close == -1) {
            set.add(exp);
            return;
        }

        // Find matching opening brace
        int open = exp.lastIndexOf('{', close);

        String left = exp.substring(0, open);
        String right = exp.substring(close + 1);

        // Options inside braces
        String[] choices =
            exp.substring(open + 1, close).split(",");

        for (String choice : choices) {
            dfs(left + choice + right);
        }
    }
}