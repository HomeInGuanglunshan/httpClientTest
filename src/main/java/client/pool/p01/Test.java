package client.pool.p01;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

public class Test {

	public static void main(String[] args) {
		System.out.println(execute("http://www.baidu.com"));
	}

	@SuppressWarnings("deprecation")
	private static String execute(String url) {
		CloseableHttpClient client = HttpClientUtil.getHttpClient();
		// 发送get请求
		HttpGet request = new HttpGet(url);
		CloseableHttpResponse response = null;
		// 设置请求和传输超时时间

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000)//数据传输过程中数据包之间间隔的最大时间
				.setConnectTimeout(20000)//连接建立时间，三次握手完成时间
				.setExpectContinueEnabled(true)//重点参数 
				.setConnectionRequestTimeout(10000).setStaleConnectionCheckEnabled(true)//重点参数，在请求之前校验链接是否有效
				.build();
		request.setConfig(requestConfig);

		try {
			response = client.execute(request);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				System.out.println("请求失败");
				return null;
			}
			HttpEntity resEntity = response.getEntity();
			if (resEntity == null) {
				return null;
			}
			String result = EntityUtils.toString(resEntity, "UTF-8");
			return result;
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			return null;
		} catch (ClientProtocolException e) {
			System.out.println(e.getMessage());
			return null;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			if (response != null) {
				try {

					//此处调优重点，多线程模式下可提高性能。
					EntityUtils.consume(response.getEntity());//此处高能，通过源码分析，由EntityUtils是否回收HttpEntity

					response.close();
				} catch (IOException e) {
					System.out.println("关闭response失败:" + e);
				}
			}
		}
	}
}
