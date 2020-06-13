package test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class TestGuanchaByPost {

	public static void main(String[] args) throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://www.guancha.com/"); // 请填写并提交您的相关信息，审核通过后，您将获得观察体验会员计划的资格！
//		HttpPost post = new HttpPost("http://www.guancha.cn/"); // 对不起，您访问的内容违反了相关的访问限制，已经被禁止访问，如果有疑问，请与网络管理员联系。
//		HttpPost post = new HttpPost("https://www.guancha.cn/"); // 405
//		HttpPost post = new HttpPost("http://113.105.155.198/"); // The requested URL '/' was not found on this server.
		HttpResponse response = client.execute(post);
		System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
	}
}
