import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();

        for (String[] clothe : clothes) {
            map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1);
        }

        int count = 1;
        for (int v : map.values()) {
            count *= v + 1;
        }

        return --count;
    }
}
