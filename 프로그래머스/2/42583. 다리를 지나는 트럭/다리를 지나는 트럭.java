import java.util.LinkedList;
import java.util.Queue;

class Solution {
static class Info {
        int inTime;
        int weight;

        public Info(int inTime, int weight) {
            this.inTime = inTime;
            this.weight = weight;
        }
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> queue = new LinkedList<>();
        Queue<Info> processing = new LinkedList<>();

        for (int truckWeight : truck_weights) {
            queue.add(truckWeight);
        }

        int time = 0;
        int currentWeight = 0;
        while (!queue.isEmpty() || !processing.isEmpty()) {
            time++;

            if (!processing.isEmpty() && time - processing.peek().inTime >= bridge_length) {
                currentWeight -= processing.poll().weight;
            }

            if (!queue.isEmpty() && currentWeight + queue.peek() <= weight && processing.size() < bridge_length) {
                currentWeight += queue.peek();
                processing.add(new Info(time, queue.poll()));
            }
        }

        return time;
    }
}