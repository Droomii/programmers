import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
	public static List<Page> pageList = new ArrayList<>();
	public static Map<String, Page> pageByUrl = new HashMap<String, Page>();

	public int solution(String word, String[] pages) {
		for (String p : pages) {
			Page created = new Page(p.toLowerCase(), word.toLowerCase());
			pageList.add(created);
			pageByUrl.put(created.url, created);
		}
		addExternalLinks();
		return pageList.stream().sorted((p1, p2) -> {
			if (Double.compare(p2.score, p1.score) == 0) {
				return p1.idx - p2.idx;
			}
			return Double.compare(p2.score, p1.score);
		}).findFirst().get().idx;
	}

	public static void addExternalLinks() {
		for (Page page1 : pageByUrl.values()) {
			for (String exLink : page1.links) {
				if (exLink.equals(page1.url))
					continue;
				Page page2 = pageByUrl.get(exLink);
				if (page2 != null) {
					page2.score += page1.linkScore;
				}
			}
		}
	}

}

class Page {
	public static int indexCnt;
	public int idx;
	public int matchCount;
	public double score;
	public double linkScore;
	public int linkCount;
	public String url;
	public List<String> links;

	public Page(String page, String word) {
		this.url = getURL(page);
		this.idx = indexCnt++;
		this.matchCount = findWord(page, word);
		this.score = this.matchCount;
		this.links = getExternalLink(page);
		this.linkScore = this.score / (double) linkCount;
	}

	public List<String> getExternalLink(String page) {
		List<String> links = new ArrayList<>();
		Pattern p = Pattern.compile("<a href=\"(.+?)\"");
		Matcher m = p.matcher(page);
		while (m.find()) {
			links.add(m.group(1));
			linkCount++;
		}
		return links;
	}

	public String getURL(String page) {
		Pattern p = Pattern.compile("<meta property=\"og:url\" content=\"(https://.+?)\"/>");
		Matcher m = p.matcher(page);
		m.find();
		return m.group(1);
	}

	public int findWord(String page, String word) {
		int count = 0;
		int index = page.indexOf(word);
		while (index != -1) {
			char prefix = page.charAt(index - 1);
			char suffix = page.charAt(index + word.length());
			if (!Character.isLowerCase(prefix) && !Character.isLowerCase(suffix)) {
				count++;
			}
			index = page.indexOf(word, index + 1);
		}

		return count;
	}

	@Override
	public String toString() {
		return "Page [idx=" + idx + ", matchCount=" + matchCount + ", score=" + score + ", linkScore=" + linkScore
				+ ", url=" + url + ", " + links.size() + " links=" + links + "]";
	}

}