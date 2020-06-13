package crawl.page.pics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import client.pool.p01.HttpClientUtil;

public class CrawlPagePics {

	String site = "http://661ku.com/tttppp/1152721.html";

	String storageLocation = "F:/69978a714208088b5aeDFAFDA/sexy pictures/";

	HttpClient client = HttpClientUtil.getHttpClient();

	List<String> picSrcs;

	String defaultCharset = "ISO-8859-1";

	public static void main(String[] args) throws Exception {
		new CrawlPagePics().execute();
	}

	public void execute(List<String> sites) throws Exception {
		for (String site : sites) {
			this.site = site;
			System.out.println("-----------------------------");
			System.out.println(site);
			System.out.println("-----------------------------");
			execute();
		}
	}

	private void execute() throws Exception {

		HttpGet get = new HttpGet(site);
		get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0");

		HttpResponse response = getResponseRecursively(get);

		String result = EntityUtils.toString(response.getEntity());

		String charset = getCharset(result);
		if (!defaultCharset.equalsIgnoreCase(charset)) {
			result = new String(result.getBytes(defaultCharset), charset);
		}

		String title = getTitleFromPage(result);

		getPicSrcsFromPage(result);

		downloadPics(title);
	}

	private HttpResponse getResponseRecursively(HttpGet get) throws Exception {
		try {
			return client.execute(get);
		} catch (SocketException e) {
			System.out.println(get.getURI() + " : " + e.getMessage());
			Thread.sleep(2000);
			return getResponseRecursively(get);
		}
	}

	private String getCharset(String result) {
		Matcher matcher = Pattern.compile("<meta charset=(.*?)>").matcher(result);
		while (matcher.find()) {
			return matcher.group(1).replace("\"", "");
		}
		return defaultCharset;
	}

	private String getTitleFromPage(String result) {
		Matcher matcher = Pattern.compile("<h1 class=\"h1-title\">(.*?)</h1>").matcher(result);
		String title = null;
		while (matcher.find()) {
			title = matcher.group(1);
		}
		if (title == null) {
			return "NoTitle";
		} else if (title.matches("^(&#\\d+;?)+$")) {
			return UnicodeUtils.unicodeToChn(title);
		} else {
			return title;
		}
	}

	private void getPicSrcsFromPage(String result) {
		Matcher matcher = Pattern.compile("<img.*?src=\"(.*?)\"").matcher(result);
		picSrcs = new ArrayList<String>();
		while (matcher.find()) {
			picSrcs.add(matcher.group(1));
		}
	}

	private void downloadPics(String title) throws Exception {

		ExecutorService threadPool = Executors.newCachedThreadPool();

		int counter = 1;
		for (String src : picSrcs) {

			String seq = ("00" + counter).replaceAll(".*(\\w{3})", "$1");

			threadPool.execute(new Runnable() {
				@Override
				public void run() {

					HttpGet get = new HttpGet(src);
					HttpResponse response;

					InputStream inputStream = null;
					FileOutputStream outputStream = null;

					try {
						response = getResponseRecursively(get);

						String folder = storageLocation + title;
						File folderFile = new File(folder);
						if (!folderFile.exists()) {
							folderFile.mkdirs();
						}
						String picName = seq + "-" + src.substring(src.lastIndexOf("/") + 1);
						outputStream = new FileOutputStream(new File(folder + "/" + picName));
						inputStream = response.getEntity().getContent();

						byte[] buff = new byte[1024];
						int len = 0;
						while ((len = inputStream.read(buff)) != -1) {
							outputStream.write(buff, 0, len);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							inputStream.close();
						} catch (IOException e) {
						}
						try {
							outputStream.close();
						} catch (IOException e) {
						}
					}
				}
			});

			counter++;
		}

		for (threadPool.shutdown(); !threadPool.awaitTermination(1000, TimeUnit.MILLISECONDS);) {
			continue;
		}
	}
}
