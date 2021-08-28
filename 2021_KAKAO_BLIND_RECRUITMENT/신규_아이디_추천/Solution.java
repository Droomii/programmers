class Solution {
    public String solution(String new_id) {
        String answer = new_id;
        answer = answer.toLowerCase();
        System.out.println(answer);
        answer = answer.replaceAll("[^-_.A-Za-z]", "");
        System.out.println(answer);
        answer = answer.replaceAll("\\.{2,}", ".");
        System.out.println(answer);
        answer = answer.startsWith(".") ? answer.substring(1) : answer;
        System.out.println(answer);
        answer = answer.isEmpty() ? "a" : answer;
        System.out.println(answer);
        answer = answer.length() > 15 ? answer.substring(0, 15) : answer;
        System.out.println(answer);
        answer = answer.startsWith(".") ? answer.substring(1) : answer;
        System.out.println(answer);
        answer = answer.endsWith(".") ? answer.substring(0, answer.length()-1) : answer;
        System.out.println(answer);
        while (answer.length() < 3) {
            answer += answer.substring(answer.length()-1);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution("z-+.^."));
    }
}