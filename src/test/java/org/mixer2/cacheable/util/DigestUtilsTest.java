package org.mixer2.cacheable.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.StopWatch;
import org.junit.Before;
import org.junit.Test;
import org.mixer2.cacheable.util.DigestUtils;

public class DigestUtilsTest {

    private String templateFileName = "sample-xhtml1-transitional.html";
    private String templateFilePath;

    @Before
    public void init() throws IOException {
        templateFilePath = getClass().getResource(templateFileName).toString();
        String osname = System.getProperty("os.name");
        if(osname.indexOf("Windows")>=0){
            templateFilePath = templateFilePath.replaceFirst("file:/", "");
        } else {
            templateFilePath = templateFilePath.replaceFirst("file:", "");
        }
    }
    
    @Test
    public void testSha1Hex() {
        String str = "abcdefg";
        String str1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(str);
        String str2 = org.mixer2.cacheable.util.DigestUtils.sha1Hex(str);
        assertEquals(str1, str2);
    }

    @Test
    public void testSha1Hex_multibyte() {
        String str = "あいうえお";
        String str1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(str);
        String str2 = org.mixer2.cacheable.util.DigestUtils.sha1Hex(str);
        assertEquals(str1, str2);
    }

    @Test
    public void performance() throws Exception {
        int loop = 10000;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i=0; i<loop; i++) {
            File file = new File(templateFilePath);
            DigestUtils.sha1Hex(FileUtils.readFileToString(file));
        }
        stopWatch.stop();
        System.out.println("SHA-1 digest performance test. loop= " + loop + ", time(msec)= " + stopWatch.getTime());
        
    }

}
