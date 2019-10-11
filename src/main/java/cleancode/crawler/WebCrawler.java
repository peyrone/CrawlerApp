package cleancode.crawler;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StopWatch;

import cleancode.crawler.api.service.CrawlerService;
import cleancode.crawler.common.ApplicationConfiguration;

/**
 * 
 * @author Neda Peyrone
 *
 */
public class WebCrawler {
	
	private static ApplicationContext context;
	private static Logger logger = Logger.getLogger(WebCrawler.class);

	public static void main(String[] args) {	
		logger.info("I:--START--:--Download File--");
		
		StopWatch stopWatch = new StopWatch("Starting StopWatch");
		stopWatch.start("initializing");
		
		context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		CrawlerService crawlerService = (CrawlerService) context.getBean("crawlerService");
		
		try {
			crawlerService.downloadFile();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Exception occur:\n", e);
		}
		
		stopWatch.stop();
		logger.info(String.format("O:--Finish--:ElapsedSeconds/%s", stopWatch.getTotalTimeSeconds()));
	}
}
