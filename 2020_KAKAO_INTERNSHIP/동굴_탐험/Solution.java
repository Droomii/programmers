// https://programmers.co.kr/learn/courses/30/lessons/67260

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
	static Set<Node> visited = new HashSet<>();
	static Map<Integer, Node> nodes = new HashMap<>();
	static Map<Node, Node> startMap = new HashMap<>();
	static Set<Node> endSet = new HashSet<>();
	
    public boolean solution(int n, int[][] path, int[][] order) {
    	for(int i = 0; i < n; i++) {
    		nodes.put(i, new Node());
    	}
    	for(int[] p : path) {
    		Node node1 = nodes.get(p[0]);
    		Node node2 = nodes.get(p[1]);
    		node1.connected.add(node2);
    		node2.connected.add(node1);
    	}
    	for(int[] ord : order) {
    		startMap.put(nodes.get(ord[0]), nodes.get(ord[1]));
    		endSet.add(nodes.get(ord[1]));
    	}
    	dfs(nodes.get(0));
        return visited.size()==n;
    }
    static void dfs(Node node) {
    	visited.add(node);
    	if(startMap.containsKey(node)) {
    		Node openedNode = startMap.get(node);
    		endSet.remove(openedNode);
			if(visited.contains(openedNode)) {
				dfs(openedNode);
			}
		}
		if(endSet.contains(node)) return;
		
		for(Node nextNode : node.connected) {
			if(!visited.contains(nextNode))
				dfs(nextNode);
		}
    }
}
class Node{
	Set<Node> connected = new HashSet<>();
}