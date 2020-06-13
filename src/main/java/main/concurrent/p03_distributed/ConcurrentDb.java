package main.concurrent.p03_distributed;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class ConcurrentDb {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		List<Future<String>> futureList = new ArrayList<>();
		for (int i = 0; i < 200; i++) {
			futureList.add(threadPool.submit(new HttpCallable("lida", i)));
		}
		threadPool.shutdown();
		for (Future<String> future : futureList) {
			System.out.println(future.get());
		}
	}

}

class HttpCallable implements Callable<String> {

	String username;

	Integer password;

	public HttpCallable(String username, Integer password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public String call() throws Exception {

		CloseableHttpClient client = HttpClients.createDefault();

		String url1 = "http://localhost:8080/concurrentDb1/addUser";
		String url2 = "http://localhost:8080/concurrentDb2/addUser";

		String[] urlArr = new String[] { url1, url2 };

		HttpPost httpPost = new HttpPost(urlArr[password % 2]);

		// 参数形式为key=value&key=value
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("username", username));
		formparams.add(new BasicNameValuePair("password", String.valueOf(password)));

		// 加utf-8进行编码
		UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
		httpPost.setEntity(uefEntity);

		CloseableHttpResponse response = client.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		return result;
	}

}