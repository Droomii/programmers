// https://programmers.co.kr/learn/courses/30/lessons/68645

class Solution {
    public int[] solution(int n) {
    	int[][] triangle = new int[n][n];
    	int answerLen = 0;
    	for(int i = 1; i < n+1; i++) {
    		answerLen += i;
    	}
    	int[] answer = new int[answerLen];
    	int top = 0;
    	int left = 0;
    	int bottom = n-1;
    	int right = n-1;
    	int currentNum = 1;
    	for(int dir = 0;; dir++) {
    		int currentDir = dir % 3;
    		if(currentDir == 0) {
    			for(int row = top; row <= bottom; row++) {
    				triangle[row][left] = currentNum++;
    			}
    			top++;
    			left++;
    		}else if(currentDir == 1) {
    			for(int col = left; col <= right; col++) {
    				triangle[bottom][col] = currentNum++;
    			}
    			bottom--;
    			right--;
    		}else {
    			for(int i = 0; i < right-left+1 ; i++) {
    				triangle[bottom-i][right-i] = currentNum++; 
    			}
    			top++;
    			right--;
    		}
    		if(left>right || top>bottom) break;
    	}
    	int answerIdx = 0;
    	for(int i = 0; i < n; i++) {
    		for(int k = 0; k < i+1; k++) {
    			answer[answerIdx++] = triangle[i][k];
    		}
    	}
        return answer;
    }
    public static void main(String[] args) {
    	new Solution().solution(6);
    	
	}
}