class Solution {
    static boolean[] visited;
    static int maxCount = 0;
    
    static void dfs(int depth, int k, int[][] dungeons) {
        maxCount = Math.max(maxCount, depth);

        if (depth == dungeons.length) {
            return;
        }

        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (k >= dungeons[i][0]) {
                    dfs(depth + 1, k - dungeons[i][1], dungeons);
                }
                visited[i] = false;
            }
        }

    }

    public static int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(0, k, dungeons);
        return maxCount;
    }
}