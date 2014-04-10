package org.mixer2.cacheable;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.integration.CompletionListener;
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.EntryProcessorResult;

import org.mixer2.jaxb.xhtml.Html;

/**
 * <p>
 * Simple implementation of Cache.
 * One instance of java.util.concurrent.ConcurrentHashMap<String,Html> stores objects.
 * </p>
 * 
 * @author nabedge
 *
 */
public class SimpleCache implements Cache<String, Html> {

    private ConcurrentHashMap<String, Html> map = new ConcurrentHashMap<String, Html>();

    /**
     * @return number of object in this cache.
     */
    public int size() {
        return map.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Html get(String key) {
        return map.get(key);
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public Map<String, Html> getAll(Set<? extends String> keys) {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public void loadAll(Set<? extends String> keys,
            boolean replaceExistingValues, CompletionListener completionListener) {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, Html value) {
        map.put(key, value);
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public Html getAndPut(String key, Html value) {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public void putAll(Map<? extends String, ? extends Html> map) {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean putIfAbsent(String key, Html value) {
        Html html = map.putIfAbsent(key, value);
        if (html == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(String key) {
        Html html = map.remove(key);
        if (html == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public boolean remove(String key, Html oldValue) {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public Html getAndRemove(String key) {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public boolean replace(String key, Html oldValue, Html newValue) {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public boolean replace(String key, Html value) {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public Html getAndReplace(String key, Html value) {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public void removeAll(Set<? extends String> keys) {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAll() {
        map.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        map.clear();
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public String getName() {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public CacheManager getCacheManager() {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public void close() {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public boolean isClosed() {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public <T> T unwrap(Class<T> clazz) {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public Iterator<javax.cache.Cache.Entry<String, Html>> iterator() {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public <C extends Configuration<String, Html>> C getConfiguration(
            Class<C> clazz) {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public <T> T invoke(String key,
            EntryProcessor<String, Html, T> entryProcessor, Object... arguments)
            throws EntryProcessorException {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public <T> Map<String, EntryProcessorResult<T>> invokeAll(
            Set<? extends String> keys,
            EntryProcessor<String, Html, T> entryProcessor, Object... arguments) {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public void registerCacheEntryListener(
            CacheEntryListenerConfiguration<String, Html> cacheEntryListenerConfiguration) {
        throw new UnsupportedOperationException("not implemented.");
    }

    /**
     * <strong>NOT implemented</strong>
     */
    @Override
    public void deregisterCacheEntryListener(
            CacheEntryListenerConfiguration<String, Html> cacheEntryListenerConfiguration) {
        throw new UnsupportedOperationException("not implemented.");
    }

}
