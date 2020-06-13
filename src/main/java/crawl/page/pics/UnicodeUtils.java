package crawl.page.pics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnicodeUtils {

	public static void main(String[] args) {
		System.out.println(
				"&#27604&#36739&#32670&#28073&#30340&#22969&#23376&#91&#50&#48&#80&#93".matches("^(&#\\d+;?)+$"));
		System.out.println("&#21478;&#31867;&#23567;&#35828;".matches("^(&#\\d+;?)+$"));

		System.out.println(unicodeToChn("&#27604&#36739&#32670&#28073&#30340&#22969&#23376&#91&#50&#48&#80&#93"));
		System.out.println(unicodeToChn("&#21478;&#31867;&#23567;&#35828;"));
	}

	public static String unicodeToChn(String str) {
		Matcher matcher = Pattern.compile("&#(\\d+?(?=&|;|$))").matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			char c = (char) Integer.valueOf(matcher.group(1)).intValue();
			sb.append(c);
		}
		return sb.toString();
	}

}
