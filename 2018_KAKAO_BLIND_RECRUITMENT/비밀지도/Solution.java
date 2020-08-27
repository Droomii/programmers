class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
    	String[] answer = new String[n];
    	for(int i = 0 ; i < n; i++) {
    		int or = arr1[i] | arr2[i];
    		String binary = String.format("%" + n + "s", Integer.toBinaryString(or));
    		answer[i] = binary
    				.substring(0, n)
    				.replaceAll("1", "#")
    				.replaceAll("0", " "); 
    	}
    	
        return answer;
    }
}