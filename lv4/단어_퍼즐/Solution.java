// https://programmers.co.kr/learn/courses/30/lessons/12983

class Solution {
	public int solution(String[] strs, String t) {
		int len = t.length();
		int[] route = new int[len];
		for (int i = 1; i < len+1; i++) {
			route[i-1] = len+1;
			for (String str : strs) {
				if (i - str.length() < 0) continue;
				if (t.substring(i - str.length(), i).equals(str)) {
					try {
						route[i-1] = Integer.min(route[i-1], route[i - str.length()-1] + 1);
					} catch (ArrayIndexOutOfBoundsException e) {
						route[i-1] = 1;
					}
				}
			}
		}
		return route[len - 1] == len+1 ? -1 : route[len - 1];
	}
}