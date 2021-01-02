// https://programmers.co.kr/learn/courses/30/lessons/12909

class Solution {
    boolean solution(String s) {
        
        // 괄호 열리면 1 더하기, 닫히면 1 빼기
        // 음수가 되면 올바름 괄호가 아님
        int balance = 0;
        
        // 문자열 for문 돌리기
        // balance가 음수가 되면 반복문 빠져나오기
        for(int i = 0; i < s.length() && balance > -1; i++){
            // 열리는 괄호인 경우
            if(s.charAt(i)=='('){
                balance++;
            
            // 닫히는 괄호인 경우
            }else{
                balance--;
            }
        }
        // balance가 0일 경우 올바른 괄호
        if(balance==0) return true;
        
        // balance가 0이 아닐 경우 괄호가 다 닫히지 않았으므로 올바르지 않은 괄호
        return false;
    }
}