import java.util.*;

class Solution {
	public int[] solution(int N, int[] stages) {

		int[] survivors = new int[N + 1];
		for (int i = 1; i < N + 2; i++) {
			int count = 0;
			for (int stage : stages) {
				if (stage >= i) {
					count++;
				}
			}
			survivors[i - 1] = count;
		}
		List<Stage> stageList = new ArrayList<>();
		for (int i = 1; i < N + 1; i++) {
			int reached = survivors[i - 1];
			int cleared = survivors[i];
			stageList.add(new Stage(i, reached, cleared));
		}
		return stageList.stream().sorted().mapToInt(e -> e.stage).toArray();
	}
}

class Stage implements Comparable<Stage> {
	public int stage;
	public double successRate;

	public Stage(int stage, int reached, int cleared) {
		this.stage = stage;
		if (reached == 0) {
			this.successRate = 1;
		} else {
			this.successRate = (double) cleared / reached;
		}

	}

	@Override
	public int compareTo(Stage o) {
		if(this.successRate == o.successRate) {
			return Integer.compare(this.stage, o.stage);
		}
		return Double.compare(this.successRate, o.successRate);
	}
}