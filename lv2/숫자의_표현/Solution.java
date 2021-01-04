// https://programmers.co.kr/learn/courses/30/lessons/12924

class Solution {
    public int solution(int n) {

        // 표현 가능 갯수
        int answer = 0;

        // 맨 왼쪽의 숫자(가장 작은 숫자)
        int min = 1;

        // 맨 오른쪽의 숫자(가장 큰 숫자)
        int max = 1;

        // 총합
        int sum = 0;

        while(min <= n){
            // 총합과 n이 같은 경우
            if(sum == n){
                answer++;
            }

            // 총합이 n보다 큰 경우
            if(sum > n){
                sum -= min++;
            // 총합이 n보다 같거나 작은 경우
            }else{
                sum += max++;
            }
        }

        return answer;
    }

}