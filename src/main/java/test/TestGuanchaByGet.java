package test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class TestGuanchaByGet {

	public static void main(String[] args) throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet("https://www.guancha.cn/");

		get.setHeader("Host", "www.guancha.cn");
		get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0");
		get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		get.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		get.setHeader("Accept-Encoding", "gzip, deflate, br");
		get.setHeader("Cookie",
				"Hm_lvt_8ab18ec6e3ee89210917ef2c8572b30e=1570324476,1570352582,1570357931,1570768681; CNZZDATA1254137364=921531022-1507132941-null%7C1570779737; UM_distinctid=16db91bfe0417a-02718d0ee5d84a-4c594131-100200-16db91bfe05d7");
		get.setHeader("Connection", "keep-alive");
		get.setHeader("Upgrade-Insecure-Requests", "1");
		get.setHeader("If-Modified-Since", "Fri, 11 Oct 2019 09:05:56 GMT");

		HttpResponse response = client.execute(get);
		System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
	}
}
