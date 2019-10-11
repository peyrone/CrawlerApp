package cleancode.crawler.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import cleancode.crawler.api.service.CrawlerService;
import cleancode.crawler.api.service.CrawlerServiceImpl;

/**
 * 
 * @author Neda Peyrone
 *
 */
@Configuration
@ComponentScan(basePackages = "cleancode.crawler")
@PropertySource(value = { "classpath:application.properties" })
public class ApplicationConfiguration {
	
	@Bean(name = "crawlerService")
	public CrawlerService webCrawler() {
		return new CrawlerServiceImpl();
	}
}
