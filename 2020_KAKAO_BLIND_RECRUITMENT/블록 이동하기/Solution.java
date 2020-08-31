import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

class Solution {
	public int solution(int[][] board) throws Exception{
//		Map<Robot, Integer> visited = new HashMap<>();
		int[][][] visited = new int[board.length][board.length][2];
		Queue<Robot> q = new ArrayDeque<Robot>();
		int minCnt = Integer.MAX_VALUE;
		
		q.add(new Robot(0, 0, 0, 1, 0, 0));
		int test = 0;
		while(!q.isEmpty()) {
			Robot r = q.poll();
			// 벽 확인
			try {
				if(board[r.row1][r.col1] + board[r.row2][r.col2]>0) {
					continue;
				}
			}catch(ArrayIndexOutOfBoundsException e) {
				continue;
			}
			
			// 회전 장애물 확인
			if(r.currDir%2 == 1) {
				try {
					int[] passing = r.getPassingCoord();
					if(board[passing[0]][passing[1]]==1) {
						continue;
					}
				}catch(ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}
			
			if((r.row1==board.length-1 && r.col1==board.length-1)||(r.row2==board.length-1 && r.col2==board.length-1)) {
				minCnt = Integer.min(minCnt, r.moveCount);
				continue;
			}
			
			if(visited[r.row1][r.col1][r.isVertical ? 0 : 1]==0) {
				visited[r.row1][r.col1][r.isVertical ? 0 : 1]=r.moveCount;
				
			}else if(visited[r.row1][r.col1][r.isVertical ? 0 : 1] > r.moveCount) {
				visited[r.row1][r.col1][r.isVertical ? 0 : 1]=r.moveCount;
			}else {
				continue;
			}
			
			for(int i = 0; i < 8; i++) {
				q.add(r.move(i));
			}
			
		}
		return minCnt;
	}
}

class Robot {
	public int[][] dirs = { { -1, 0, -1, 0 }, { 0, 0, -1, +1 }, { 0, 1, 0, 1 }, { 1, 1, 0, 0 }, { 1, 0, 1, 0 },
			{ 1, -1, 0, 0 }, { 0, -1, 0, -1 }, { 0, 0, -1, -1 } };

	
	public int[][] passVertical = {{0, -1}, {1, -1}, {1, 1}, {0, 1}}; 
	public int[][] passHorizontal = {{1, 1}, {-1, 1}, {-1, 0}, {1, 0}};
	public int row1, col1, row2, col2;
	public int currDir;
	public int moveCount;
	public boolean isVertical;

	public Robot(int row1, int col1, int row2, int col2, int currDir, int moveCount) {
		this.currDir = currDir;
		this.moveCount = moveCount;
		int Coord1Sum = row1 + col1;
		int Coord2Sum = row2 + col2;
		if (Coord1Sum < Coord2Sum) {
			this.row1 = row1;
			this.col1 = col1;
			this.row2 = row2;
			this.col2 = col2;
		} else {
			this.row1 = row2;
			this.col1 = col2;
			this.row2 = row1;
			this.col2 = col1;
		}

		if (col1 == col2) {
			this.isVertical = true;
		} else {
			this.isVertical = false;
		}
	}
	public Robot move(int dir) {
		int i = -1;
		if(dir%4==1 && !isVertical) i = 1;
		
		return new Robot(
				row1 + dirs[dir][(++i)%4],
				col1 + dirs[dir][(++i)%4], 
				row2+dirs[dir][(++i)%4], 
				col2+dirs[dir][(++i)%4], dir, moveCount+1);
		
	}
	
	public int[] getPassingCoord() {
		int pass = (this.currDir-1) / 2;
		int[] head = {this.row1, this.col1};
		int[] passing;
		if(isVertical) {
			passing = passVertical[pass];
		}else {
			passing =  passHorizontal[pass];
		}
		head[0] += passing[0];
		head[1] += passing[1];
		return head;
	}
	
	@Override
	public boolean equals(Object obj) {
		Robot o = (Robot)obj;
		boolean res = true;
		res &= this.row1==o.row1;
		res &= this.row2==o.row2;
		res &= this.col1==o.col1;
		res &= this.col2==o.col2;
		return res;
	}
	@Override
	public String toString() {
		return "Robot [(" +row1 + ", " + col1 + "), (" + row2 + ", " + col2 + "), currDir=" + currDir
				+ ", moveCount=" + moveCount + "]";
	}
	
}