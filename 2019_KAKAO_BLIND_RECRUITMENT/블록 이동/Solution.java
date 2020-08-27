import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
	
    public int solution(int[][] board) {
    	Board b = new Board(board);
		int totalCount = 0;
		while(true) {
			int count = b.dropAndCheck();
			if(count==0) break;
			totalCount += count;
		}
        return totalCount;
    }
}

class Board{
	public int[][] board;
	public int[] top;
	public Board(int[][] board) {
		this.board = new int[board.length][board.length];
		this.top = new int[board.length];
		Arrays.fill(this.top, -1);
		
		for(int i = 0; i < board.length; i++) {
			this.board[i] = board[i].clone();
		}
		refreshTop();
	}
	public int dropAndCheck() {
		drop();
		drop();
		return check();
	}
	private void refreshTop() {
		for(int i = 0; i < board.length; i++) {
			for(int k = 0; k < board.length; k++) {
				if(board[k][i]==0) {
					top[i]=k;
				}else {
					break;
				}
			}
		}
	}
	
	public void drop() {
		for(int i = 0; i < top.length; i++) {
			if(top[i]>-1) {
				board[top[i]][i] = -1;
				top[i]--;
			}
		}
	}
	public void clear() {
		for(int i = 0; i < board.length; i++) {
			for(int k = 0; k < board.length; k++) {
				if(board[i][k]==-1) {
					board[i][k] = 0;
				}
			}
		}
		refreshTop();
		
	}
	public int check() {
		int matchCount = 0;
		for(int size = 0; size < 2; size++) {
			int sizeX = size % 2 + 2;
			int sizeY = (size+1) % 2 + 2;
			for(int x = 0; x < board.length-(sizeX-1); x++) {
				for(int y = 0; y < board.length-(sizeY-1); y++) {
					Map<Integer, Integer> elementCount = new HashMap<>();
					for(int subX = x; subX < x+sizeX; subX++) {
						for(int subY = y; subY < y+sizeY; subY++) {
							elementCount.compute(board[subX][subY], (k, v) ->(v==null) ? 1 : v+1);
						}
					}
					elementCount.remove(-1);
					if(elementCount.size()==1) {
						if(elementCount.keySet().iterator().next()==0) {
							continue;
						}
						if(elementCount.values().iterator().next()==4) {
							for(int subX = x; subX < x+sizeX; subX++) {
								for(int subY = y; subY < y+sizeY; subY++) {
									board[subX][subY] = 0;
								}
							}
							matchCount++;
						}
					}
					
					
						
				}
			}
		}
		clear();
		return matchCount;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int[] row : this.board) {
			for(int col : row) {
				sb.append(String.format("%2s ", col));
			}
			sb.append("\n");
		}
		// TODO Auto-generated method stub
		return sb.toString();
	}
}