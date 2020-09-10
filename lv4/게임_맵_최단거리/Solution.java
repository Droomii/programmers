// https://programmers.co.kr/learn/courses/30/lessons/1844

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int solution(int[][] maps) {
    	int m = maps.length;
    	int n = maps[0].length;
    	int[][] shortest = new int[m][n];
    	Queue<int[]> topQueue = new ArrayDeque<int[]>();
    	topQueue.add(new int[] {0, 0, 1});
    	while(!topQueue.isEmpty()) {
    		int[] top = topQueue.poll();
    		if(top[0]==m-1 && top[1]==n-1)
    			return top[2];
    		try {
    			if(maps[top[0]][top[1]]==1) {
        			if(shortest[top[0]][top[1]]==0) {
        				shortest[top[0]][top[1]] = top[2];
        				for(int i = 0; i < 4; i++) {
        					topQueue.add(new int[] {top[0]+dir[i][0], top[1]+dir[i][1], top[2]+1});
        				}
        			}
        		}
    		}catch(ArrayIndexOutOfBoundsException e) {}
    	}
        return -1;
    }
}