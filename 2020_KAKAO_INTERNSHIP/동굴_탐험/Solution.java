import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class Solution {
	
	public static Map<Integer, Node> nodes = new HashMap<>();
	public static Set<Node> visited = new HashSet<>();
	
    public boolean solution(int n, int[][] path, int[][] order) {
    	
    	for(int[] p : path) {
    		nodes.putIfAbsent(p[0], new Node(p[0]));
    		nodes.putIfAbsent(p[1], new Node(p[1]));
    		nodes.get(p[0]).addChild(nodes.get(p[1]));
    		nodes.get(p[1]).addChild(nodes.get(p[0]));
    	}
    	Arrays.stream(order).forEach(o -> Node.addCondition(nodes.get(o[0]), nodes.get(o[1])));
    	
    	visit(nodes.get(0), null);
    	return n==visited.size();
    }
    
    public static void visit(Node node, Node parent) {
    	visited.add(node);
    	node.removeChild(parent);
    	if(node.keyNode!=null) {
    		if(!visited.contains(node.keyNode)) {
    			return;
    		}
    	}else if(node.openNode!=null) {
    		if(visited.contains(node.openNode)) {
    			visit(node.openNode, null);
    		}
    	}
    	for(Node grandChild : node) {
    		visit(grandChild, node);
    	}
    }
    
    public static void main(String[] args) {
		int[][] path = {{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}};
		int[][] order = {{8,5},{6,7},{4,1}};
		int n = 9;
		System.out.println(new Solution().solution(n, path, order));
	}
}

class Node implements Iterable<Node>{
	public Set<Node> children;
	public int idx;
	public Node keyNode;
	public Node openNode;
	public Node(int idx) {
		this.children = new HashSet<>();
		this.idx = idx;
	}
	@Override
	public String toString() {
		return "Node "+this.idx+" [children=" + Arrays.toString(children.stream().mapToInt(a -> a.idx).toArray()) + 
				(keyNode==null ? "" : (", keyNode=" + keyNode.idx)) +
				(openNode==null ? "" : (", openNode=" + openNode.idx)) +
				"]";
	}
	
	public void addChild(Node child) {
		this.children.add(child);
	}
	public void removeChild(Node child) {
		this.children.remove(child);
	}

	@Override
	public Iterator<Node> iterator() {
		return this.children.iterator();
	}
	
	public static void addCondition(Node startNode, Node endNode) {
		endNode.keyNode = startNode;
		startNode.openNode = endNode; 
	}
	
	
}