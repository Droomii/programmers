// https://programmers.co.kr/learn/courses/30/lessons/49994

class Solution {
	static int[][] moveDir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int solution(String dirs) {
    	boolean[][][] visited = new boolean[11][11][4];
    	int row = 5;
    	int col = 5;
    	int cnt = 0;
    	for(char d : dirs.toCharArray()) {
    		int dir = 0;
    		switch(d) {
    		case 'U': dir=0;break;
    		case 'R': dir=1;break;
    		case 'D': dir=2;break;
    		case 'L': dir=3;break;
    		}
    		
    		int newRow = row + moveDir[dir][0];
    		int newCol = col + moveDir[dir][1];
    		if(!visited[row][col][dir]) {
    			try {
    				visited[newRow][newCol][(dir+2)%4] = true;
    			}catch(ArrayIndexOutOfBoundsException e) {
    				continue;
    			}
    			cnt++;
    			visited[row][col][dir] = true;
    		}
    		row = newRow;
    		col = newCol;
    	}
        return cnt;
    }
}