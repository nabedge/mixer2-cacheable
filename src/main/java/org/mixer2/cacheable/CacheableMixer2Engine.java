package org.mixer2.cacheable;

import javax.cache.Cache;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mixer2.Mixer2Engine;
import org.mixer2.cacheable.util.DigestUtils;
import org.mixer2.jaxb.xhtml.Html;
import org.mixer2.xhtml.exception.TagTypeUnmatchException;

/**
 * TODO write javadoc.
 * 
 * need cache-api-0.5.jar (or higher) need mixer2-1.2.8.jar (or higher)
 * 
 * sample code...
 * 
 * @author nabedge
 */
public class CacheableMixer2Engine extends Mixer2Engine {

    private static Log log = LogFactory.getLog(CacheableMixer2Engine.class);

    private boolean cacheEnabled = true;

    private Cache<String, Html> cache = null;

    public CacheableMixer2Engine() {
        super();
        log.info("CachableMixer2Engine initialized");
    }

    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    public void setCacheEnabled(boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }

    /**
     * <p>
     * set cache object for loaded(unmashalled) template. The key of cache is
     * String, sha1 hash value of template string. You need not to create cache
     * key.
     * </p>
     * <p>
     * xhtmlテンプレートをunmarshalした結果のHtml型インスタンスをキャッシュするオブジェクトをセットします。
     * キャッシュのキーはStringで、自動的にテンプレート文字列自体のsha1ハッシュ値が使われます。
     * </p>
     * 
     * @param cache
     */
    public synchronized void setCache(Cache<String, Html> cache) {
        this.cache = cache;
    }

    public Cache<String, Html> getCache() {
        return cache;
    }

    /**
     * remove all cache. if cache is null, do nothing.
     */
    public void removeAllCache() {
        if (this.cache != null) {
            cache.removeAll();
        }
    }

    /**
     * TODO write javadoc always returns copy() of html object...
     */
    @Override
    protected final Html unmarshal(StringBuilder sb) throws JAXBException {
        Html resultHtml = null;
        if (cacheEnabled && cache != null) {
            String cacheKey = DigestUtils.sha1Hex(sb.toString());
            Html cachedHtml = cache.get(cacheKey);
            if (cachedHtml == null) {
                resultHtml = super.unmarshal(sb);
                try {
                    cache.putIfAbsent(cacheKey, resultHtml.copy(Html.class));
                } catch (TagTypeUnmatchException e) {
                    log.warn("pigs fly!", e);
                    // the value of cache has only Html object.
                }
            } else {
                resultHtml = cachedHtml;
            }
        } else {
            resultHtml = super.unmarshal(sb);
        }
        return resultHtml;
    }

}
