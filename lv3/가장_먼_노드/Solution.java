// https://programmers.co.kr/learn/courses/30/lessons/49189

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
    public int solution(int n, int[][] edge) {
    	Map<Integer, Node> nodeMap = new HashMap<>();
    	
    	for(int i = 1; i < n+1; i++) {
    		nodeMap.put(i, new Node(i));
    	}
    	for(int[] e : edge) {
    		nodeMap.get(e[0]).connect(nodeMap.get(e[1]));
    	}
    	
    	SortedMap<Integer, Integer> distances = new TreeMap<>();
    	
    	Set<Integer> visited = new HashSet<>();
    	
    	Queue<int[]> nodeQ = new PriorityQueue<>((a, b)->a[1] - b[1]);
    	nodeQ.add(new int[] {1, 0});
    	
    	while(!nodeQ.isEmpty()) {
    		int[] currNode = nodeQ.poll();
    		if(visited.add(currNode[0])) {
    			distances.compute(currNode[1], (k, v)->(v==null) ? 1 : v+1);
    			Node node = nodeMap.get(currNode[0]);
    			for(Node con : node.connected) {
    				nodeQ.add(new int[] {con.idx, currNode[1]+1});
    			}
    		}
    	}
        return distances.get(distances.lastKey());
    }
}

class Node{
	Set<Node> connected;
	int idx;
	
	public Node(int idx) {
		this.connected = new HashSet<>();
		this.idx = idx;
	}
	public void connect(Node o) {
		connected.add(o);
		o.connected.add(this);
	}
}