package org.mixer2.cacheable;

import org.junit.Test;

public class CacheableMixer2EngineTest {

    @Test
    public void test() {
        CacheableMixer2Engine m2e = new CacheableMixer2Engine();
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("</html>");
        m2e.loadHtmlTemplate(sb);
    }

}
