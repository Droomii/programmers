// https://programmers.co.kr/learn/courses/30/lessons/42861
import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
    	Arrays.sort(costs, (a, b)->a[2]-b[2]);
    	List<int[]> costList = new ArrayList<>();
    	for(int[] cost : costs) {
    		costList.add(cost);
    	}
    	
    	
    	Set<Integer> connected = new HashSet<>();
    	int[] start = costList.remove(0);
    	int answer = start[2];
    	
    	connected.add(start[0]);
    	connected.add(start[1]);
    	
    	while(connected.size()<n) {
    		Iterator<int[]> it = costList.iterator();
    		while(it.hasNext()) {
    			int[] next = it.next();
    			if(connected.contains(next[0]) && connected.contains(next[1])) {
    				it.remove();
    				continue;
    			}
    			if(connected.contains(next[0]) || connected.contains(next[1])) {
    		    	connected.add(next[0]);
    		    	connected.add(next[1]);
    				answer += next[2];
    				it.remove();
    				break;
    			}
    		}
    	}
        return answer;
    }
}


}