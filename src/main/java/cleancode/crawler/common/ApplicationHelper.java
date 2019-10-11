package cleancode.crawler.common;

import java.net.URL;

import org.apache.log4j.Logger;

/**
 * 
 * @author Neda Peyrone
 *
 */
public class ApplicationHelper {

	private static Logger logger = Logger.getLogger(ApplicationHelper.class);

	/**
	 *  Returns site's url without the query part
	 * 
	 * @param java.net.URL|null url
	 * @return String
	 */
	public static String getBaseUrl(URL url) {
		if (url == null) {
			return null;
		}

		try {
			String path = url.getFile().substring(0, url.getFile().lastIndexOf('/'));
			String baseUrl = String.format("%s://%s%s/", url.getProtocol(), url.getHost(), path);
			return baseUrl;
		} catch (Exception ex) {
			logger.error("Exception occur:\n", ex);
			return null;
		}
	}
}
