import java.util.*;

class Solution {
    public static int min = -30000;
    public static int max = 30000;
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a,b)->(a[0] - b[0]));
        int enterCnt = min;
        int exitCnt = max;
        int answer = 1;
        for(int[] route : routes){
            if(exitCnt < route[0]){
                enterCnt = route[0];
                exitCnt = route[1];
                answer++;
            }else{
            enterCnt = enterCnt > route[0] ? enterCnt : route[0];
            exitCnt = exitCnt < route[1] ? exitCnt : route[1];
            }
        }
        
        return answer;
    }
}