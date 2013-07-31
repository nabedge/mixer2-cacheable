package org.mixer2.cacheable;

public class CacheableMixer2EngineSingleton {

    private CacheableMixer2EngineSingleton() {
    }

    private static final CacheableMixer2Engine m2e = new CacheableMixer2Engine();

    public static CacheableMixer2Engine getInstance() {
        return m2e;
    }

}
