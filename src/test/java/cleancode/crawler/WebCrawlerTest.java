package cleancode.crawler;

import java.io.IOException;

import junit.framework.TestCase;

/**
 * 
 * @author Neda Peyrone
 *
 */
public class WebCrawlerTest extends TestCase
{   
    public void testMain() throws IOException {
        System.out.println("main");
        String[] args = null;
    		WebCrawler.main(args);
    }
}
