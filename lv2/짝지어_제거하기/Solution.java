// https://programmers.co.kr/learn/courses/30/lessons/12973

import java.util.Stack;
class Solution {
	public int solution(String s) {
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < s.length(); i++) {
			if(!stack.isEmpty()) {
				if(stack.peek()==s.charAt(i)) {
					stack.pop();
					continue;
				}
			}
			stack.add(s.charAt(i));
		}
		return stack.isEmpty() ? 1 : 0;
	}
}