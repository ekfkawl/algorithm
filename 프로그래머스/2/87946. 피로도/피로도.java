class Solution {
    static boolean[] visited;
    
    static int dfs(int depth, int k, int[][] dungeons) {
        int res = depth;

        if (depth == dungeons.length) {
            return res;
        }

        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (k >= dungeons[i][0]) {
                    res = Math.max(res, dfs(depth + 1, k - dungeons[i][1], dungeons));
                }
                visited[i] = false;
            }
        }
        
        return res;
    }

    public static int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        return dfs(0, k, dungeons);
    }
}