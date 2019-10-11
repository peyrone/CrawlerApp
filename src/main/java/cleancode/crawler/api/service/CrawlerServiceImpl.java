package cleancode.crawler.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import cleancode.crawler.api.entity.LinkItem;
import cleancode.crawler.common.ApplicationHelper;
import cleancode.crawler.common.Constants;
import cleancode.crawler.common.HttpDownloadUtility;
import cleancode.crawler.common.Parallel;

/**
 * 
 * @author Neda Peyrone
 *
 */
public class CrawlerServiceImpl implements CrawlerService {
	
	private static Logger logger = Logger.getLogger(CrawlerServiceImpl.class);
	
	@Autowired
	private Environment environment;

	public void downloadFile() throws IOException {
		final String crawlerUrl = environment.getProperty(Constants.CRAWLER_URL);
		final String folderPath = environment.getProperty(Constants.FOLDER_PATH);
		
		Collection<LinkItem> elems = this.getAllLinks(crawlerUrl);

		if (elems != null && elems.size() > 0) {
			Parallel.For(elems, new Parallel.Operation<LinkItem>() {
				public void perform(LinkItem param) {
					System.out.println(param.toString());

					try {
						HttpDownloadUtility.downloadFile(param.getHref(), folderPath);
					} catch (IOException e) {
						e.printStackTrace();
					}
				};
			});
		}
	}

	private Collection<LinkItem> getAllLinks(String crawlerUrl) throws IOException {
		URL url = new URL(crawlerUrl);
		HttpURLConnection uc = (HttpURLConnection) url.openConnection();

		uc.connect();
		
		String line = null;
		StringBuffer tmp = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		while ((line = in.readLine()) != null) {
			tmp.append(line);
		}

		Document doc = Jsoup.parse(String.valueOf(tmp));

		// Get page title
		String title = doc.title();

		// Get all links
		Elements links = doc.select("div.art-article a[href^=images/pdf]");
		Collection<LinkItem> elems = new LinkedList<LinkItem>();

		for (Element link : links) {
			link.setBaseUri(ApplicationHelper.getBaseUrl(url));
			String text = link.text();
			String absUrl = link.absUrl("href");
			elems.add(new LinkItem(absUrl, text));
		}		

		logger.info(String.format("Page Title/%s:Total/%s", title, elems.size()));		
		return elems;
	}
}
