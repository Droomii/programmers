// https://programmers.co.kr/learn/courses/30/lessons/43162

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class Solution {
    public int solution(int n, int[][] computers) throws Exception{
    	Set<Integer> left = new HashSet<>();
    	for(int i = 0; i < n; i++) {
    		left.add(i);
    	}
    	int networks = 0;
    	Queue<Integer> q = new ArrayDeque<Integer>();
    	while(!left.isEmpty()) {
    		q.add(left.iterator().next());
        	while(!q.isEmpty()) {
        		int i = q.poll();
        		left.remove(i);
        		for(int k = 0; k < n; k++) {
        			if(computers[i][k]==1 && left.contains(k)) {
        				q.add(k);
        			}
        		}
        	}
        	networks++;
    	}
        return networks;
    }
}