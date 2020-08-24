class Solution {
	public boolean solution(int[][] key, int[][] lock) {

		int gridSize = key.length * 2 + lock.length - 2;
		int iterations = key.length + lock.length - 1;
		
		int[][] grid = new int[gridSize][gridSize];
		
		// 중앙에 자물쇠 넣기
		int rowIter = key.length-1;
		for(int[] lockRow : lock) {
			int colIter = key.length-1;
			for(int lockCol : lockRow) {
				grid[rowIter][colIter] = lockCol;
				colIter++;
			}
			rowIter++;
		}
		
		// 회전 4회
		for(int i = 0 ; i < 4; i++) {
			
			// 행 반복문
			for(int row = 0; row < iterations; row++) {
				// 열 반복문
				for(int col = 0; col < iterations; col++) {
					
					// 임시 그리드 만들기
					int[][] testGrid = new int[grid.length][];
					for(int gridRow = 0; gridRow < grid.length; gridRow++) {
						testGrid[gridRow] = grid[gridRow].clone();
					}
					
					// 열쇠 넣기
					rowIter = row;
					for(int[] keyRow : key) {
						int colIter = col;
						for(int keyCol : keyRow) {
							testGrid[rowIter][colIter] += keyCol;
							colIter++;
						}
						rowIter++;
					}
					
					// 맞는지 보기
					int match = 1;
					rowIter = key.length-1;
					for(int j = rowIter; j < rowIter+lock.length; j++) {
						int colIter = key.length-1;
						for(int k = colIter; k < colIter+lock.length; k++) {
							match *= testGrid[j][k];
						}
					}
					if(match==1) {
						return true;
					}
				}
			}
			
			key = rotate90(key);
		}
		return false;
	}
	
	public static int[][] rotate90(int[][] key){
		int size = key.length;
		int[][] res = new int[size][size];
		for(int i = 0 ; i < size; i++) {
			for(int k = 0; k < size; k++) {
				res[k][size-1-i] = key[i][k]; 
			}
		}
		
		return res;
		
	}
}