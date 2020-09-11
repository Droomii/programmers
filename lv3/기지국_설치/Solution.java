// https://programmers.co.kr/learn/courses/30/lessons/12979

class Solution {
    public int solution(int n, int[] stations, int w) {
    	if(n - stations.length <= w) return 0;
    	
    	int cnt = 0;
    	int apart = w+1;
    	int i = 0;
    	while(apart <= n+w) {
    		try {
    			if(stations[i] > apart) {
        			cnt++;
        			apart = apart + 2*w+1;
        		}else {
        			apart = stations[i++]+ 2*w+1;
        		}
    		}catch(ArrayIndexOutOfBoundsException e) {
    	    		cnt++;
    	    		apart = apart + 2*w+1;
    		}
    	}
    	return cnt;
    }
}