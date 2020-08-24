class Solution {
    public int solution(String s) {
    	int strLen = s.length();
    	int minLen = strLen;
    	for(int i = 1; i <= strLen / 2; i++) {
    		StringBuilder sb = new StringBuilder();
    		int count = 1;
    		for(int k = 0;; k += i) {
    			try {
    			if(s.substring(k, k+i).equals(s.substring(k+i, k+i+i))) {
    				count++;
    			}else {
    				sb.append(count!=1 ? count : "");
    				sb.append(s.substring(k, k+i));
    				count = 1;
    			}
    			}catch(StringIndexOutOfBoundsException e) {
    				sb.append(count!=1 ? count : "");
    				sb.append(s.substring(k));
    				break;
    			}
    		}
    		
    		minLen = sb.length() < minLen ? sb.length() : minLen;
    	}
        return minLen;
    }
}