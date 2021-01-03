// https://programmers.co.kr/learn/courses/30/lessons/12899

class Solution {
  public String solution(int n) {
      char[] num = {'4','1','2'};
      StringBuilder sb = new StringBuilder();
      
      while(n > 0){
          sb.append(num[n % 3]);
          n = (n - 1) / 3;
      }
      return sb.reverse().toString();
  }
}