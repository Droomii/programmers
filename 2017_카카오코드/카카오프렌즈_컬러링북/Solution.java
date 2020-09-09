// https://programmers.co.kr/learn/courses/30/lessons/1829

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public int[] solution(int m, int n, int[][] picture) {
		int[][] pp = new int[m][n];
		for(int i = 0; i < m;i++) {
			pp[i] = picture[i].clone();
		}
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;
		Queue<int[]> q = new ArrayDeque<int[]>();

		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				if (pp[r][c] != 0) {

					numberOfArea++;
					int color = pp[r][c];
					pp[r][c] = 0;
					int sizeOfArea = 1;
					for (int i = 0; i < 4; i++) {
						q.add(new int[] { r + dir[i][0], c + dir[i][1] });
					}
					while (!q.isEmpty()) {
						int[] coord = q.poll();
						try {
							if (pp[coord[0]][coord[1]] == color) {

								sizeOfArea++;
								pp[coord[0]][coord[1]] = 0;
								for (int i = 0; i < 4; i++) {
									q.add(new int[] { coord[0] + dir[i][0], coord[1] + dir[i][1] });
								}
							}
						} catch (ArrayIndexOutOfBoundsException e) {
						}
					}
					maxSizeOfOneArea = Integer.max(maxSizeOfOneArea, sizeOfArea);
				}
			}
		}
		int[] answer = new int[2];
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}
}