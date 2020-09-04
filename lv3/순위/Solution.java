// https://programmers.co.kr/learn/courses/30/lessons/49191

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
	static Set<Integer> remaining;

	public int solution(int n, int[][] results) {
		remaining = IntStream.range(1, n + 1).collect(HashSet::new, HashSet::add, HashSet::addAll);

		boolean[][] resultMatrix = new boolean[n][n];
		Arrays.stream(results).forEach(result -> {
			resultMatrix[result[0] - 1][result[1] - 1] = true;
		});
		
		while(true) {
			boolean[][] original = new boolean[n][n];
			for(int i = 0; i < n; i++) {
				original[i] = resultMatrix[i].clone();
			}

			for (int col = 0; col < n; col++) {
				for(int row = 0; row < n; row++) {
					if(resultMatrix[row][col]) {
						for(int row2 = 0; row2 < n; row2++) {
							resultMatrix[row2][col] = resultMatrix[row2][row] ? true : resultMatrix[row2][col]; 
						}
					}
				}
			}
			if(Arrays.deepEquals(original, resultMatrix)) break;
		}
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = 0; j < n; j++) {
				sum += resultMatrix[i][j] ? 1 : 0;
				sum += resultMatrix[j][i] ? 1 : 0;
			}
			if (sum == n - 1)
				cnt++;
		}
		return cnt;
	}
}