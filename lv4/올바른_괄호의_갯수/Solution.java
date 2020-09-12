// https://programmers.co.kr/learn/courses/30/lessons/12929

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int solution(int n) {
    	Queue<int[]> q = new ArrayDeque<int[]>();
    	int cnt = 0;
    	q.add(new int[] {1, 0});
    	while(!q.isEmpty()) {
    		int[] brackets = q.poll();
    		if(brackets[1]>brackets[0]) continue;
    		if(brackets[0] > n) continue;
    		if(brackets[1]==n) {
    			cnt++;
    			continue;
    		}
    		q.add(new int[] {brackets[0]+1, brackets[1]});
    		q.add(new int[] {brackets[0], brackets[1]+1});
    	}
        return cnt;
    }
}