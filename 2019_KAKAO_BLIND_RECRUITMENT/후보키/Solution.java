import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
	public static List<List<Integer>> allComboList = new ArrayList<>();

	public int solution(String[][] relation) {
		int rowNum = relation.length;

		allCombinations(relation[0].length);
		int count = 0;
		Set<List<String>> uniqueSet = null;
		for (int i = 0; i < allComboList.size(); i++) {
			uniqueSet = new HashSet<>();
			List<Integer> chosenCol = allComboList.get(i);
			for (String[] row : relation) {
				List<String> rowAsList = new ArrayList<>();
				for (int col : chosenCol) {
					rowAsList.add(row[col]);
				}
				uniqueSet.add(rowAsList);
			}
			if (uniqueSet.size() == rowNum) {
				count++;
				for (int k = i + 1; k < allComboList.size(); k++) {
					if (allComboList.get(k).containsAll(chosenCol)) {
						allComboList.remove(k--);
					}
				}
			}
		}

		return count;
	}

	public static void allCombinations(int colNum) {
		List<Integer> initList = new ArrayList<>();
		for (int i = 0; i < colNum; i++) {
			initList.add(i);
		}
		List<Integer> initChosen = new ArrayList<>();

		for (int remainChoice = 1; remainChoice < colNum + 1; remainChoice++) {
			allCombinations(initChosen, initList, remainChoice);
		}

	}

	public static void allCombinations(List<Integer> chosen, List<Integer> remaining, int remainChoice) {
		if (remainChoice == 0) {
			allComboList.add(chosen);
			return;
		}
		for (int i = 0; i < remaining.size() - remainChoice + 1; i++) {
			List<Integer> nextChosen = new ArrayList<>(chosen);
			nextChosen.add(remaining.get(i));
			allCombinations(nextChosen, remaining.subList(i + 1, remaining.size()), remainChoice - 1);
		}

	}
}
