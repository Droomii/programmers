class Solution {

    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(toUpper(s.charAt(0)));
        for(int i = 1; i < s.length(); i++){
            if(sb.charAt(i-1)==' '){
                sb.append(toUpper(s.charAt(i)));
            }else{
                sb.append(toLower(s.charAt(i)));
            }
        }
        return sb.toString();
    }

    public boolean isUpper(char c){
        if(c >= 'A' && c <= 'Z'){
            return true;
        }
        return false;
    }

    public boolean isLower(char c){
        if(c >= 'a' && c <= 'z'){
            return true;
        }
        return false;
    }

    public char toUpper(char c){
        if(isLower(c)){
            return (char)(c + ('A' - 'a'));
        }
        return c;
    }
    public char toLower(char c){
        if(isUpper(c)){
            return (char)(c + ('a' - 'A'));
        }
        return c;
    }
}