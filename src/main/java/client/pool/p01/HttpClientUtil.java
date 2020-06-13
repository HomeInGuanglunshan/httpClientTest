package client.pool.p01;

import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://blog.csdn.net/ZhangjcGG/article/details/79861904
 * 
 * https://blog.csdn.net/qq_29857681/article/details/90719645
 */
public class HttpClientUtil {

	private static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);
	private static PoolingHttpClientConnectionManager cm = null;

	static {
		LayeredConnectionSocketFactory sslsf = null;
		try {
			sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("创建SSL连接失败");
		}

		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();

		cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		cm.setMaxTotal(200); // 多线程调用注意配置，根据线程数设定 
		cm.setDefaultMaxPerRoute(300); // 多线程调用注意配置，根据线程数设定 
	}

	public static CloseableHttpClient getHttpClient() {
//		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm)
//				.setRetryHandler(new DefaultHttpRequestRetryHandler(10, true)).build();
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
		return httpClient;
	}
}
