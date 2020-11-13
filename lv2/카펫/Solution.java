// https://programmers.co.kr/learn/courses/30/lessons/42842

class Solution {
    public int[] solution(int brown, int yellow) {
    	brown -= 4;
    	brown /= 2;
    	int w = brown - 1;
    	int h = 1;
    	
    	while(true) {
    		if(w * h == yellow) break;
    		w--;
    		h++;
    	}
        return new int[] {w+2, h+2};
    }
}