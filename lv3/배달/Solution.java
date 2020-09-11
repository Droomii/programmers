// https://programmers.co.kr/learn/courses/30/lessons/12978

import java.util.PriorityQueue;

class Solution {
    public int solution(int N, int[][] road, int K) {
    	boolean[] visited = new boolean[N];
    	int[][] graph = new int[N][N];
    	for(int[] r : road) {
    		r[0] -= 1;
    		r[1] -= 1;
    		graph[r[0]][r[1]] = graph[r[0]][r[1]] != 0 ? Math.min(graph[r[0]][r[1]], r[2]) : r[2];
    		graph[r[1]][r[0]] = graph[r[0]][r[1]];
    	}
    	PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2)-> o1[1] - o2[1]);
    	q.add(new int[] {0, 0});
    	int visitCnt = 0;
    	while(!q.isEmpty()) {
    		int[] route = q.poll();
    		if(route[1]>K || visited[route[0]]) continue;
    		visited[route[0]] = true;
    		visitCnt++;
    		for(int i = 0; i < N; i++) {
    			if(graph[route[0]][i]>0) {
    				q.add(new int[] {i, route[1]+graph[route[0]][i]});
    			}
    		}
    	}
    	
        return visitCnt;
    }
}