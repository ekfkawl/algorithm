class Solution {
    public static int[] solution(int brown, int yellow) {
        
        int totalBlock = brown + yellow;
        
        for (int h = 3; h <= totalBlock / 3; h++) {
            if (totalBlock % h == 0) {
                int w = totalBlock / h;
                
                int maybeYellow = (h - 2) * (w - 2);
                if (yellow == maybeYellow) {
                    return new int[]{w, h};
                }
                
            }
        }
        
        return new int[]{0, 0};
    }
}