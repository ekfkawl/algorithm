import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    static class Priority {
        int index;
        int priority;

        public Priority(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
    
    public static int solution(int[] priorities, int location) {
        Deque<Priority> deque = new ArrayDeque<>();
        Queue<Priority> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.priority, o1.priority));

        for (int i = 0; i < priorities.length; i++) {
            Priority priority = new Priority(i, priorities[i]);

            deque.addLast(priority);
            queue.add(priority);
        }

        int res = 0;
        while (true) {
            if (deque.peekFirst().priority < queue.peek().priority) {
                deque.addLast(deque.removeFirst());
            }else {
                res++;
                if (!queue.isEmpty()) {
                    queue.poll();
                }
                if (deque.removeFirst().index == location) {
                    break;
                }
            }
        }

        return res;
    }

}
