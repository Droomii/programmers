// https://programmers.co.kr/learn/courses/30/lessons/42839

import java.util.HashSet;
import java.util.Set;

class Solution {
	
	static Set<Integer> numberSet = new HashSet<>(); 
	
    public int solution(String numbers) {
    	for(int i = 1; i < numbers.length()+1; i++) {
    		permutation("", numbers, i);
    	}
        return (int)numberSet.stream().filter(Solution::checkPrime).count();
    }
    
    public static void permutation(String selected, String toSelect, int selectLimit) {
    	if(selected.length()==selectLimit) {
    		int num = Integer.parseInt(selected);
    		if(num>1) {
    			numberSet.add(num);
    		}
    		return;
    	}
    	for(int i = 0; i < toSelect.length(); i++) {
    		permutation(selected+toSelect.charAt(i), toSelect.substring(0, i)+toSelect.substring(i+1), selectLimit);
    	}
    }
    
    public static boolean checkPrime(int num) {
    	boolean isNotPrime = false;
    	for(int i = 2; i <=num/2;i++) {
    		isNotPrime |= num % i == 0;
    		if(isNotPrime) break;
    	}
    	return !isNotPrime;
    }
}