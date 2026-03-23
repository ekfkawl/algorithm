import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    static class AlignString implements Comparable<AlignString> {
    String str;

    public AlignString(String str) {
        this.str = str;
    }

    @Override
    public int compareTo(AlignString o) {
        return (o.str + this.str).compareTo(this.str + o.str);
    }
}
    
  public static String solution(int[] numbers) {

        Queue<AlignString> queue = new PriorityQueue<>();


        for (int n : numbers) {
            queue.add(new AlignString(String.valueOf(n)));
        }

        if ("0".equals(queue.peek().str)) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append(queue.poll().str);
        }

        return sb.toString();
    }
}