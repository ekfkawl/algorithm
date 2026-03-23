import java.util.*;

class Solution {
    
    static Map<String, Set<String>> map = new HashMap<>();
    static Map<String, Integer> cnt = new HashMap<>();
    
    // 유저가 한번이라도 신고한 유저가 정지먹었을 때 메일 발송됨
    public int[] solution(String[] id_list, String[] report, int k) {
        
        for (String r: report) {
            String[] s = r.split(" ");
            String a = s[0];
            String b = s[1];
            
            if (map.computeIfAbsent(a, val -> new HashSet<>()).add(b)) {
                cnt.put(b, cnt.getOrDefault(b, 0) + 1);
            }
        }
        
        int i = 0;
        int[] answer = new int[id_list.length];
        for (String id: id_list) {
            for (String target: map.getOrDefault(id, new HashSet<>())) {
                if (cnt.getOrDefault(target, 0) >= k) {
                    answer[i]++;
                }
            }
            i++;
        }
    
       
        return answer;
    }
}