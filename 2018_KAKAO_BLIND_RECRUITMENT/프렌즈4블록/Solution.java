// https://programmers.co.kr/learn/courses/30/lessons/17679

import java.util.Arrays;

class Solution {
    public int solution(int m, int n, String[] board) {
    	
    	// 2차원 char 배열로 보드 재구성
    	// boardInChar[0][0]은 일반 보드의 좌측 상단
    	String[][] newBoard = new String[n][m];
    	for(int i = 0; i < m; i++) {
    		for(int k = 0; k < n; k++) {
    			newBoard[k][i] = board[i].substring(k, k+1);
    		}
    	}
    	int totalDelete = 0;
    	while(true) {
    		boolean[][] toDelete = new boolean[n][m];
        	for(int i = 0; i < n-1; i++) {
        		for(int k = 0; k < m-1; k++) {
        			if(newBoard[i][k].equals(newBoard[i][k+1])
        			&& newBoard[i+1][k].equals(newBoard[i+1][k+1])
        			&& newBoard[i][k+1].equals(newBoard[i+1][k+1])) {
        				if(!newBoard[i][k].equals("0")) {
        					toDelete[i][k] = toDelete[i][k+1] = toDelete[i+1][k] = toDelete[i+1][k+1] = true;
        				}
        			}
        		}
        	}
        	int deleteCnt = 0;
        	for(int i = 0; i < n; i++) {
        		for(int k = 0; k < m; k++) {
        			if(toDelete[i][k]) {
        				newBoard[i][k] = "0";
        				deleteCnt++;
        			}
        				
        		}
        	}
        	if(deleteCnt==0) break;
        	totalDelete += deleteCnt;
        	for(int i = 0; i < n; i++) {
        		Arrays.sort(newBoard[i], (a, b) -> a.equals("0") ? -1 : b.equals("0") ? 1 : 0);
        	}
    	}
    	
    	return totalDelete;
    }

}