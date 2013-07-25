package org.mixer2.cacheable;

import javax.cache.Cache;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mixer2.Mixer2Engine;
import org.mixer2.cacheable.util.DigestUtils;
import org.mixer2.jaxb.xhtml.Html;

/**
 * TODO write javadoc.
 * 
 * <ul>
 * <li>cache-api-0.8.jar (or higher)</li>
 * <li>mixer2-1.2.9.jar (or higher)</li>
 * </ul>
 * 
 * sample code...
 * 
 * @author nabedge
 */
public class CacheableMixer2Engine extends Mixer2Engine {

    private static Log log = LogFactory.getLog(CacheableMixer2Engine.class);

    private boolean cacheEnabled = true;

    private Cache<String, Html> cache = new SimpleCache();

    public CacheableMixer2Engine() {
        super();
        log.info("CachableMixer2Engine initialized");
    }

    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    /**
     * on/off function
     * @param cacheEnabled if set false, cache function will be disabled.
     */
    public void setCacheEnabled(boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }

    /**
     * <p>
     * set cache object for loaded(unmashalled) template. The key of cache is
     * String, sha1 hash value of template string. You need not to create cache
     * key. Before set the new one, call removeAllCache() to remove cache on old
     * object.
     * </p>
     * <p>
     * xhtmlテンプレートをunmarshalした結果のHtml型インスタンスをキャッシュするオブジェクトをセットします。
     * キャッシュのキーはStringで、自動的にテンプレート文字列自体のsha1ハッシュ値が使われます。
     * あたらしいCacheオブジェクトをセットする前に、 それ以前のキャッシュオブジェクトに対してremoveAllCache()をコールします。
     * </p>
     * 
     * @param cache
     */
    public synchronized void setCache(Cache<String, Html> cache) {
        removeAllCache();
        this.cache = null;
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
        Html result = null;
        if (cacheEnabled && cache != null) {
            String cacheKey = DigestUtils.sha1Hex(sb.toString());
            Html cached = cache.get(cacheKey);
            if (cached == null) {
                result = super.unmarshal(sb);
                cache.putIfAbsent(cacheKey, result.copyNoException(Html.class));
            } else {
                result = cached.copyNoException(Html.class);
            }
        } else {
            result = super.unmarshal(sb);
        }
        return result;
    }

}
