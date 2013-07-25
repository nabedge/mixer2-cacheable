package org.mixer2.cacheable;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.time.StopWatch;
import org.junit.Before;
import org.junit.Test;
import org.mixer2.jaxb.xhtml.Html;

public class CacheableMixer2EngineTest {

    private String templateFileName = "sample-xhtml1-transitional.html";
    private String templateFilePath;
    private CacheableMixer2Engine m2e = new CacheableMixer2Engine();
    private int loop = 1000;

    @Before
    public void before() {
        templateFilePath = getClass().getResource(templateFileName).toString();
        if (SystemUtils.IS_OS_WINDOWS) {
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
        // get from cache (first)
        Html html2 = m2e.checkAndLoadHtmlTemplate(new File(templateFilePath));
        assertNotNull(html2);
        // get from cache (second)
        Html html3 = m2e.checkAndLoadHtmlTemplate(new File(templateFilePath));
        assertNotNull(html3);
        // same string
        assertEquals(m2e.saveToString(html), m2e.saveToString(html2));
        assertEquals(m2e.saveToString(html2), m2e.saveToString(html3));
        assertEquals(m2e.saveToString(html), m2e.saveToString(html3));
        // but not refarence.
        html.getBody().setId("111");
        html2.getBody().setId("222");
        html3.getBody().setId("333");
        assertThat(html.getBody().getId(), is(not("222")));
        assertThat(html.getBody().getId(), is(not("333")));
        assertThat(html2.getBody().getId(), is(not("111")));
        assertThat(html2.getBody().getId(), is(not("333")));
        assertThat(html3.getBody().getId(), is(not("111")));
        assertThat(html3.getBody().getId(), is(not("222")));
    }

    @Test
    public void loopWithCache() throws IOException {
        m2e.setCacheEnabled(true);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < loop; i++) {
            File file = new File(templateFilePath);
            m2e.loadHtmlTemplate(file);
        }
        stopWatch.stop();
        System.out.println("   loopWithCache: loop=" + loop + ", time(msec)="
                + stopWatch.getTime());
    }

    @Test
    public void loopWithoutCache() throws IOException {
        m2e.setCacheEnabled(false);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < loop; i++) {
            File file = new File(templateFilePath);
            m2e.loadHtmlTemplate(file);
        }
        stopWatch.stop();
        System.out.println("loopWithOutCache: loop=" + loop + ", time(msec)="
                + stopWatch.getTime());
    }

}
