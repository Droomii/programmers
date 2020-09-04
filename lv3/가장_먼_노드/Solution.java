// https://programmers.co.kr/learn/courses/30/lessons/49189

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
    public int solution(int n, int[][] edge) {
    	boolean[][] matrix = new boolean[n][n];
    	Arrays.stream(edge).forEach(e ->{
    		matrix[e[0]-1][e[1]-1] = true;
    		matrix[e[1]-1][e[0]-1] = true;
    	});
    	Set<Integer> visited = new HashSet<>();
    	Queue<Integer> nodeQ = new ArrayDeque<Integer>();
    	Queue<Integer> distQ = new ArrayDeque<Integer>();
    	SortedMap<Integer, Integer> distCnt = new TreeMap<>();
    	nodeQ.add(0);
    	distQ.add(0);
    	visited.add(0);
    	while(!nodeQ.isEmpty()) {
    		int from = nodeQ.poll();
    		int dist = distQ.poll();
    		distCnt.compute(dist, (k,v) -> (v==null) ? 1 : v+1);
    		for(int to = 0; to < n; to++) {
    			if(matrix[from][to] && !visited.contains(to)) {
    				visited.add(to);
    				nodeQ.add(to);
    				distQ.add(dist+1);
    			}
    		}
    	}
        return distCnt.get(distCnt.lastKey());
    }
}
