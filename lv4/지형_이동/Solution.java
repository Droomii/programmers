// https://programmers.co.kr/learn/courses/30/lessons/62050
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

class Solution {
	static int[][] urdl = {{-1,0},{0,1},{1,0},{0,-1}};
	static int N;
    public int solution(int[][] land, int height) {
    	int fieldCnt = land.length * land.length;
    	N = land.length;
    	Set<Integer> visited = new HashSet<>();
    	int cost = 0;
    	
    	Queue<int[]> q = new PriorityQueue<>((a, b)->a[1]-b[1]);
    	q.add(new int[] {coordToNum(0, 1), Math.abs(land[0][0]-land[0][1])});
    	q.add(new int[] {coordToNum(1, 0), Math.abs(land[0][0]-land[1][0])});
    	visited.add(0);
    	while(visited.size() < fieldCnt) {
    		int[] cross = q.poll();
    		if(visited.contains(cross[0])) continue;
    		
    		visited.add(cross[0]);
    		int[] coord = numToCoord(cross[0]);
    		int diff = cross[1];
    		
    		if(diff > height)
    			cost += diff;
    		
    		for(int i = 0; i < 4; i++) {
    			try {
    				int[] to = {coord[0]+urdl[i][0], coord[1]+urdl[i][1]};
    				int nextDiff =Math.abs(land[coord[0]][coord[1]]-land[to[0]][to[1]]);
    				int[] next = {coordToNum(to[0], to[1]), nextDiff};
    				q.add(next);
    			}catch(ArrayIndexOutOfBoundsException e) {
    				continue;
    			}
    		}
    	}
        return cost;
    }
    
    public static int coordToNum(int row, int col) {
    	return row*N + col;
    }
    public static int[] numToCoord(int num) {
    	return new int[] {num / N, num % N};
    }
}
