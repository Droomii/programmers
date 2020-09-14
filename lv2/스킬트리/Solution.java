// https://programmers.co.kr/learn/courses/30/lessons/49993

class Solution {
    public int solution(String skill, String[] skill_trees) {
    	int cnt = 0;
    	for(String skill_tree : skill_trees) {
    		skill_tree = skill_tree.replaceAll("[^" + skill + "]", "");
    		cnt += skill.startsWith(skill_tree) ? 1 : 0;
    	}
    	return cnt;
    }
}