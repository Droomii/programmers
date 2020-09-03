// https://programmers.co.kr/learn/courses/30/lessons/42861
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
	public int solution(int n, int[][] costs) {
		List<Conn> connections = Arrays.stream(costs).map(Conn::new).sorted().collect(Collectors.toList());
		Set<Integer> connected = new HashSet<>();
		int cost = 0;
		connected.addAll(connections.get(0).nodes);
		cost += connections.get(0).cost;
		connections.remove(0);
		
		while(connected.size() < n) {
			Iterator<Conn> it = connections.iterator();
			while(it.hasNext()) {
				Conn conn = it.next();
				if(connected.containsAll(conn.nodes)) continue;
				
				if(connected.removeAll(conn.nodes)) {
					cost += conn.cost;
					it.remove();
					connected.addAll(conn.nodes);
					break;
				}
				
			}
		}
		return cost;
	}
}

class Conn implements Comparable<Conn>{
	public Set<Integer> nodes;
	public int cost;
	
	public Conn(int[] cost) {
		this.nodes = new HashSet<Integer>();
		nodes.add(cost[0]);
		nodes.add(cost[1]);
		this.cost = cost[2];
	}
	@Override
	public int compareTo(Conn o) {
		return this.cost - o.cost;
	}
	@Override
	public String toString() {
		return "Conn [nodes=" + nodes + ", cost=" + cost + "]";
	}
	
	
}