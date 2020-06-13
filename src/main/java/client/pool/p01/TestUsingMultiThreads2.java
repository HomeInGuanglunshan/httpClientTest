package client.pool.p01;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

public class TestUsingMultiThreads2 {

	public static void main(String[] args) throws Exception {
		execute();
	}

	private static void execute() throws Exception {
		HttpClient client = HttpClientUtil.getHttpClient();
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					HttpPost post = new HttpPost("http://www.baidu.com");
					try {
						client.execute(post);
						System.out.println(client);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
		for (threadPool.shutdown(); !threadPool.awaitTermination(1000, TimeUnit.MILLISECONDS);) {
			continue;
		}
	}
}
