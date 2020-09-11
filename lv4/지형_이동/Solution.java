// https://programmers.co.kr/learn/courses/30/lessons/62050

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
	static int[] rowDir = {-1, 0, 1, 0};
	static int[] colDir = {0, 1, 0, -1};
    public int solution(int[][] land, int height) {
    	int len = land.length;
    	int fieldCnt = len*len;
    	int totalCost = 0;
    	boolean[][] visited = new boolean[len][len];
    	int connected = 1;
    	// {col, row, height}
    	Queue<int[]> q = new ArrayDeque<>();
    	PriorityQueue<int[]> tooHighQ = new PriorityQueue<>((o1, o2) -> o1[2]-o2[2]);
    	q.add(new int[]{0, 0, land[0][0]});
    	while(connected < fieldCnt) {
    		while(!q.isEmpty()) {
        		int[] field = q.poll();
        		if(visited[field[0]][field[1]]) continue;
        		visited[field[0]][field[1]] = true;
        		connected++;
        		for(int i = 0; i < 4; i++) {
        			try {
        				int nextRow = field[0]+rowDir[i];
        				int nextCol = field[1]+colDir[i];
        				int diff = Math.abs(field[2]-land[nextRow][nextCol]); 
        				if(diff > height){
        					tooHighQ.add(new int[] {nextRow, nextCol, diff});
        				}else {
        					q.add(new int[] {nextRow, nextCol, land[nextRow][nextCol]});
        				}
        			}catch(ArrayIndexOutOfBoundsException e) {}
        		}
        	}
    		while(!tooHighQ.isEmpty()) {
    			int[] border = tooHighQ.poll();
    			if(!visited[border[0]][border[1]]) {
    				q.add(new int[] {border[0], border[1], land[border[0]][border[1]]});
    				totalCost += border[2];
    				break;
    			}
    		}
    	}
        return totalCost;
    }
}