import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
	public int solution(int[] food_times, long k) {
		if (food_times.length > k) {
			return (int) k + 1;
		}

		PriorityQueue<Food> foodQueue = Arrays.stream(food_times).mapToObj(Food::new).collect(PriorityQueue::new,
				PriorityQueue::add, PriorityQueue::addAll);

		int currentTime = 0;
		while (!foodQueue.isEmpty()) {
			Food f = foodQueue.peek();
			long timeDiff = f.time - currentTime;
			if (timeDiff > 0) {
				long usedTime = foodQueue.size() * timeDiff;
				if (usedTime <= k) {
					k -= usedTime;
					currentTime = f.time;
				} else {
					int[] remainingArray = foodQueue.parallelStream().sorted((a, b) -> {
						return a.foodIdx - b.foodIdx;
					}).mapToInt(a -> a.foodIdx).toArray();
					return remainingArray[(int) (k % remainingArray.length)];
				}
			}
			foodQueue.poll();
		}
		return -1;

	}

	public static void main(String[] args) {
		int[] food_times = 	{3, 1, 2};
		long k = 5;
		System.out.println(new Solution().solution(food_times, k));
	}
}

class Food implements Comparable<Food> {
	public static int idx = 1;
	public int foodIdx;
	public int time;

	public Food(int time) {
		this.foodIdx = idx++;
		this.time = time;
	}

	@Override
	public int compareTo(Food o) {
		return this.time - o.time;
	}

	@Override
	public String toString() {
		return "Food [foodIdx=" + foodIdx + ", time=" + time + "]";
	}

}