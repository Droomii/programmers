import java.util.*;

class Solution {

	public String[] solution(String[] record) {
		List<Log> chatRoom = new ArrayList<>();
		Map<String, String> nickName = new HashMap<>();
		List<String> rList = new ArrayList<String>();
		for (String r : record) {
			String[] parse = r.split(" ");
			if (parse[0].equals("Enter")) {
				chatRoom.add(new Log(parse[1], true));
				nickName.put(parse[1], parse[2]);
			} else if (parse[0].equals("Change")) {
				nickName.put(parse[1], parse[2]);
			} else {
				chatRoom.add(new Log(parse[1], false));
			}
		}
		for (Log l : chatRoom) {
			StringBuilder sb = new StringBuilder();
			sb.append(nickName.get(l.id));
			sb.append("님이 ");
			if (l.enter) {
				sb.append("들어왔습니다.");
			} else {
				sb.append("나갔습니다.");
			}
			rList.add(sb.toString());
		}

		String[] answer = rList.toArray(new String[rList.size()]);
		return answer;
	}
}

class Log {
	public String id;
	public boolean enter;

	public Log(String id, boolean enter) {
		this.id = id;
		this.enter = enter;
	}
}