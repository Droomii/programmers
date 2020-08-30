import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

	public int solution(int[][] board) {

		int minCost = Integer.MAX_VALUE;

		int[][][] minCostGrid = new int[board.length][board.length][4];

		for (int i = 0; i < 4; i++) {
			minCostGrid[0][0][i] = -1;
		}

		Queue<Route> q = new PriorityQueue<Route>();
		q.add(new Route(0, 1, 1, 100, 0));
		q.add(new Route(1, 0, 2, 100, 0));
		while (!q.isEmpty()) {
			Route route = q.poll();
			try {
				if (board[route.row][route.col] == 1)
					continue;
			} catch (IndexOutOfBoundsException e) {
				continue;
			}

			if (route.row == board.length - 1 && route.col == board.length - 1) {
				minCost = Integer.min(minCost, route.cost);
			}
			int costCheck = minCostGrid[route.row][route.col][route.currDir];
			if (costCheck == 0 || costCheck > route.cost) {
				minCostGrid[route.row][route.col][route.currDir] = route.cost;
				q.add(route.goForward());
				q.add(route.turnLeft());
				q.add(route.turnRight());
			}

		}
		return minCost;
	}
}

class Route implements Comparable<Route> {
	public static int[] rowDir = { -1, 0, 1, 0 };
	public static int[] colDir = { 0, 1, 0, -1 };

	public int row;
	public int col;
	public int currDir;
	public int cost;
	public int cornerCnt;

	public Route(int row, int col, int currDir, int cost, int cornerCnt) {
		this.row = row;
		this.col = col;
		this.currDir = currDir;
		this.cost = cost;
		this.cornerCnt = cornerCnt;
	}

	private Route go(int turn) {
		int nextDir = (currDir + turn) % 4;
		int cornerCnt = this.cornerCnt + (turn == 0 ? 0 : 1);
		int cost = this.cost + 100 + (turn == 0 ? 0 : 500);
		return new Route(row + rowDir[nextDir], col + colDir[nextDir], nextDir, cost, cornerCnt);
	}

	public Route goForward() {
		return go(0);
	}

	public Route turnRight() {
		return go(1);
	}

	public Route turnLeft() {
		return go(3);
	}

	@Override
	public int compareTo(Route o) {
		return this.cornerCnt - o.cornerCnt;

	}

	@Override
	public String toString() {
		return "Route [row=" + row + ", col=" + col + ", currDir=" + currDir + ", cost=" + cost + ", cornerCnt="
				+ cornerCnt + "]";
	}

}