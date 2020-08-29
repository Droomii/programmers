import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
	public int[] solution(String[] gems) {
		if(gems.length==1) {
			return new int[] {1,1};
		}
		Set<String> gemSet = new HashSet<String>();
		Queue<String> gemQueue = new ArrayDeque<>();
		for (String gem : gems) {
			gemSet.add(gem);
			gemQueue.add(gem);
		}
		if(gemSet.size()==1) {
			return new int[] {1,1};
		}
		Map<String, Integer> selectCount = new HashMap<>();
		Deque<String> buyQueue = new ArrayDeque<String>();
		int right = 0;
		int left = 0;
		int shortest = gems.length;
		int[] best = { 1, gems.length };
		while (true) {
			if (selectCount.size() == gemSet.size()){
				selectCount.compute(buyQueue.poll(), (k, v) -> (v - 1 == 0) ? null : v - 1);
				left++;
			}else if(right == gems.length) {
				break;
			}else  {
				buyQueue.add(gemQueue.poll());
				selectCount.compute(buyQueue.peekLast(), (k, v) -> (v == null) ? 1 : v + 1);
				right++;
			}
			if (selectCount.size() == gemSet.size() && shortest > buyQueue.size()) {
				shortest = buyQueue.size();
				best[0] = left + 1;
				best[1] = right;
			}
		}
		return best;
	}
	public static void main(String[] args) {
		String[] gems = {"XYZ", "XYZ", "XYZ"};
		System.out.println(Arrays.toString(new Solution().solution(gems)));
	}
}
