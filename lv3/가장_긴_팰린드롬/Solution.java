// https://programmers.co.kr/learn/courses/30/lessons/12904

class Solution {
	public int solution(String s) {
        if(s.length()==1) return 1;
		int answer = 1;
		for(int i = 0; i < s.length(); i++) {
			int leftmost = i;
			int rightmost = i;
			while(true) {
				try {
					if(s.charAt(leftmost)==s.charAt(rightmost)) {
						leftmost--;
						rightmost++;
						continue;
					}
				}catch(StringIndexOutOfBoundsException e) {}
				answer = Math.max(rightmost - leftmost -1, answer);
				break;
			}
		}
		for(int i = 0; i < s.length()-1; i++) {
			int leftmost = i;
			int rightmost = i+1;
			while(true) {
				try {
					if(s.charAt(leftmost)==s.charAt(rightmost)) {
						leftmost--;
						rightmost++;
						continue;
					}
				}catch(StringIndexOutOfBoundsException e) {}
				answer = Math.max(rightmost - leftmost - 1, answer);
				break;
			}
		}
		return answer;
	}
}