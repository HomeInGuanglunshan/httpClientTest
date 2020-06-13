package crawl.page.pics;

import java.util.ArrayList;
import java.util.List;

public class TestCrawling {

	public static void main(String[] args) throws Exception {
		List<String> sites = new ArrayList<String>();
//		sites.add("http://www.662ku.com/tttppp/1136701.html");
//		sites.add("http://www.662ku.com/tttppp/1145309.html");
//		sites.add("http://661ku.com/tttppp/1149237.html");
//		sites.add("http://661ku.com/tttppp/1149098.html");
//		sites.add("http://www.661ku.com/tttppp/1149229.html");
//		sites.add("http://www.661ku.com/tttppp/1149084.html");
//		sites.add("http://www.661ku.com/tttppp/1149083.html");
//		sites.add("http://661ku.com/tttppp/1148265.html");
//		sites.add("http://661ku.com/tttppp/1148215.html");
//		sites.add("http://661ku.com/tttppp/1147193.html");
		sites.add("http://156na.com/tttppp/1326281.html");
		sites.add("http://156na.com/tttppp/1326280.html");
		new CrawlPagePics().execute(sites);
	}
}
