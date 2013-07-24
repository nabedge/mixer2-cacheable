package org.mixer2.cacheable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import javax.cache.Cache;
import javax.cache.CacheBuilder;
import javax.cache.CacheManager;
import javax.cache.Caching;

import org.junit.Before;
import org.junit.Test;
import org.mixer2.jaxb.xhtml.Html;

public class CachingTest {

    private String templateFileName = "sample-xhtml1-transitional.html";
    private String templateFilePath;
    private CacheableMixer2Engine m2e = new CacheableMixer2Engine();
    private int loop = 100;

    // private int loop = 1;

    @Before
    public void before() {
        CacheManager cacheManager = Caching.getCacheManager();
        CacheBuilder<String, Html> cacheBuilder = cacheManager
                .createCacheBuilder("testCache");

        // CacheConfiguration config = new CacheConfiguration("copyCache",
        // 1000).copyOnRead(false).copyOnWrite(false);
        // Cache copyCache = new Cache(config);

        Cache<String, Html> cache = cacheBuilder.build();

        m2e.setCache(cache);
        templateFilePath = getClass().getResource(templateFileName).toString();
        String osname = System.getProperty("os.name");
        if (osname.indexOf("Windows") >= 0) {
            templateFilePath = templateFilePath.replaceFirst("file:/", "");
        } else {
            templateFilePath = templateFilePath.replaceFirst("file:", "");
        }
    }

    @Test()
    public void simpleCacheTest() throws Exception {
        // create cache
        Html html = m2e.checkAndLoadHtmlTemplate(new File(templateFilePath));
        assertNotNull(html);
        // get from cache
        Html html2 = m2e.checkAndLoadHtmlTemplate(new File(templateFilePath));
        assertNotNull(html2);
        assertEquals(m2e.saveToString(html), m2e.saveToString(html2));
        // System.out.println(m2e.saveToString(html2));
    }

    @Test
    public void loopWithCache() throws IOException {
        m2e.setCacheEnabled(true);
        File file;
        for (int i = 0; i < loop; i++) {
            file = new File(templateFilePath);
            m2e.loadHtmlTemplate(file);
        }
    }

    @Test
    public void loopWithoutCache() throws IOException {
        m2e.setCacheEnabled(false);
        File file;
        for (int i = 0; i < loop; i++) {
            file = new File(templateFilePath);
            m2e.loadHtmlTemplate(file);
        }
    }

}
