// https://programmers.co.kr/learn/courses/30/lessons/1832

class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
    	// 0은 왼쪽에서 오는거, 1은 위에서 오는거
    	int[][][] grid = new int[m][n][2];
    	grid[0][0][0] = 1;
    	for(int i = 0; i < m; i++) {
    		for(int k = 0; k < n; k++) {
    			if(cityMap[i][k]==1) {
    				grid[i][k][0] = 0;
    				grid[i][k][1] = 0;
    			}else {
    				try {
    					grid[i][k+1][0] = grid[i][k][0] + (cityMap[i][k]==2 ? 0 : grid[i][k][1]);
    					grid[i][k+1][0] %= MOD;
    				}catch(ArrayIndexOutOfBoundsException e) {}
    				 try {
    					 grid[i+1][k][1] = grid[i][k][1] + (cityMap[i][k]==2 ? 0 : grid[i][k][0]);
    					 grid[i+1][k][1] %= MOD;
    				 }catch(ArrayIndexOutOfBoundsException e) {}
    			}
    		}
    	}
        return (grid[m-1][n-1][0] + grid[m-1][n-1][1]) % MOD;
    }
}