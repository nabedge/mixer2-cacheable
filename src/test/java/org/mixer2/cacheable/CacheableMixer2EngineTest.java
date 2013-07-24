package org.mixer2.cacheable;

import org.junit.Test;

public class CacheableMixer2EngineTest {

    @Test
    public void test() {
        CacheableMixer2Engine m2e = new CacheableMixer2Engine();
        StringBuilder sb = new StringBuilder();
        sb.append("<html lang=\"ja\" xmlns=\"http://www.w3.org/1999/xhtml\">");
        sb.append("</html>");
        m2e.loadHtmlTemplate(sb);
    }

}
