// https://programmers.co.kr/learn/courses/30/lessons/12952

class Solution {
    static int[][] canAttack;
    static int answer = 0;
    static int N;
    static int[][] dir = {{1,1},{1,0},{1,-1}};
    public int solution(int n) {
        N = n;
        canAttack = new int[n][n];
        dfs(0);
        return answer;
    }
    
    public static void dfs(int row){
    	if(row==N) {
    		answer++;
    		return;
    	}
    	for(int i = 0; i < N; i++) {
    		if(canAttack[row][i]==0) {
    			mark(row, i, true);
    			dfs(row+1);
    			mark(row, i, false);
    		}
    	}
    }
    public static void mark(int row, int col, boolean increment){
    	
        int inc = increment ? 1 : -1;
        canAttack[row][col] += inc;
        
        for(int[] d : dir) {
        	int rowStart = row + d[0];
        	int colStart = col + d[1];
        	while(true) {
        		try {
        			canAttack[rowStart][colStart] += inc;
        			rowStart += d[0];
        			colStart += d[1];
        		}catch(ArrayIndexOutOfBoundsException e) {
        			break;
        		}
        	}
        }
    }
    public static void main(String[] args) {
    	System.out.println(new Solution().solution(5));
	}
    
}