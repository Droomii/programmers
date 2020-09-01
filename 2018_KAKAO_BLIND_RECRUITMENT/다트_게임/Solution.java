// https://programmers.co.kr/learn/courses/30/lessons/17682

class Solution {
    public int solution(String dartResult) {
    	StringBuilder sb = new StringBuilder();
    	int totalScore = 0;
    	int lastLastscore = 0;
    	int lastScore = 0;
    	for(char c : dartResult.toCharArray()) {
    		if(Character.isDigit(c))
    			sb.append(c);
    		else {
    			if(c=='#') {
    				lastScore = -lastScore;
    				totalScore += lastScore * 2;
    			}else if(c=='*') {
    				totalScore += lastScore;
    				totalScore += lastLastscore;
    				lastScore += lastScore;
    				lastLastscore += lastLastscore;
    			}else {
    				lastLastscore = lastScore;
    				lastScore = Integer.parseInt(sb.toString());
        			lastScore *= c=='T' ? lastScore*lastScore : c=='D' ? lastScore : 1;
        			totalScore += lastScore;
    			}
    			sb = new StringBuilder();
    		}
    	}
    	return totalScore;
    }
}