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
//		sites.add("http://156na.com/tttppp/1326281.html");
//		sites.add("http://156na.com/tttppp/1326280.html");
//		sites.add("http://27ud.com/tttppp/1335101.html");
//		sites.add("http://27ud.com/tttppp/1335128.html");
//		sites.add("http://27ud.com/tttppp/1334167.html");
//		sites.add("http://27ud.com/tttppp/1333283.html");
//		sites.add("http://27ud.com/tttppp/1331034.html");
//		sites.add("http://27ud.com/tttppp/1331335.html");
//		sites.add("http://27ud.com/tttppp/1344961.html");
//		sites.add("http://27ud.com/tttppp/1344959.html");
//		sites.add("http://27ud.com/tttppp/1344744.html");
//		sites.add("http://27ud.com/tttppp/1344743.html");
//		sites.add("http://27ud.com/tttppp/1342469.html");
//		sites.add("http://27ud.com/tttppp/1342466.html");
//		sites.add("http://27uf.com/27uf-tttppp/1357674.html");
//		sites.add("http://27uf.com/27uf-tttppp/1357672.html");
//		sites.add("http://27uf.com/27uf-tttppp/1357665.html");
//		sites.add("http://27uf.com/27uf-tttppp/1357664.html");
//		sites.add("http://27uf.com/27uf-tttppp/1362236.html");
//		sites.add("http://27uf.com/27uf-tttppp/1362209.html");
//		sites.add("http://27uf.com/27uf-tttppp/1362177.html");
//		sites.add("http://27uf.com/27uf-tttppp/1361283.html");
//		sites.add("http://27uf.com/27uf-tttppp/1361273.html");
//		sites.add("http://27uf.com/27uf-tttppp/1361271.html");
		sites.add("http://27uf.com/27uf-tttppp/1361267.html");
		sites.add("http://27uf.com/27uf-tttppp/1361258.html");
		sites.add("http://27uf.com/27uf-tttppp/1361255.html");
		sites.add("http://27uf.com/27uf-tttppp/1359503.html");
		sites.add("http://27uf.com/27uf-tttppp/1359500.html");
		sites.add("http://27uf.com/27uf-tttppp/1359498.html");
		new CrawlPagePics().execute(sites);
	}
}
