// https://programmers.co.kr/learn/courses/30/lessons/42883
class Solution {
    public String solution(String number, int k) {
    	StringBuilder sb = new StringBuilder(number);
    	int i = 0;
    		while(k>0) {
        		char first = sb.charAt(i);
        		char second = 58;
        		try {
        			second = sb.charAt(i+1);
        		}catch(StringIndexOutOfBoundsException e) {}
        		
        		if(first>=second) {
        			i++;
        			continue;
        		}
        		sb.deleteCharAt(i);
        		i = 0;
        		k--;
        	}
    	return sb.toString();
    }
}