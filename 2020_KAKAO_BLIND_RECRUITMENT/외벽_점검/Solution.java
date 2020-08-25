import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

class Solution {
	
	static Set<List<Integer>> perm = new HashSet<>();
	public int solution(int n, int[] weak, int[] dist) {
		if (n == 1) {
			return 1;
		}
		int[] weakDist = new int[weak.length];
		for(int i = 0; i < weak.length-1; i++) {
			weakDist[i] = weak[i+1] - weak[i];
		}
		weakDist[weak.length-1] = weak[0] - weak[weak.length-1] + n;

		// 친구들 거리 내림차순 정렬
		List<Integer> distList = Arrays.stream(dist).collect(ArrayList::new, ArrayList::add,
				ArrayList::addAll);
		permutation(distList);
		
		int minCount = Integer.MAX_VALUE;
		for(List<Integer> dList : perm) {
			for (int i = 0; i < weak.length; i++) {
				Queue<Integer> distances = new LinkedList<Integer>();
				for (int k = i; k < i+ weak.length - 1; k++) {
					distances.add(weakDist[k%weak.length]);
				}
				Iterator<Integer> distIt = dList.iterator();
				int count = 1;
				int currentDist = distIt.next();
				while (true) {
					currentDist -= distances.poll();

					if (currentDist == 0) {
						if (distances.poll() == null) {
							break;
						}
					} else if (currentDist > 0) {
						if (distances.peek() == null) {
							break;
						}
					}
					if (currentDist <= 0) {
						try {
							currentDist = distIt.next();
							count++;
						} catch (NoSuchElementException e) {
							count = Integer.MAX_VALUE;
							break;
						}
					}
					if (distances.peek() == null) {
						break;
					}
				}
				minCount = Math.min(count, minCount);
			}
		}
		
		return minCount == Integer.MAX_VALUE ? -1 : minCount;
	}
	public static void permutation(List<Integer> distList) { 
	    permutation(new LinkedList<>(), distList); 
	}
	private static void permutation(List<Integer> preList, List<Integer> distList) {
	    if (distList.isEmpty()) {
	    	perm.add(preList);
	    }
	    else {
	        for (int i = 0; i < distList.size(); i++) {
	        	List<Integer> nextPreList = new LinkedList<>(preList);
	        	nextPreList.add(distList.get(i));
	        	List<Integer> nextList = new LinkedList<>(distList);
	        	nextList.remove(i);
	        	permutation(nextPreList, nextList);
	        }
	            
	    }
	}
	
}
