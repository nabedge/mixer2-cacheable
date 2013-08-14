mixer2-cacheable
================

Install
-------
for maven pom.xml .

    <dependency>
        <groupId>org.mixer2</groupId>
        <artifactId>mixer2-cacheable</artifactId>
        <version>1.0.1</version>
    </dependency>
    <dependency>
        <groupId>org.mixer2</groupId>
        <artifactId>mixer2</artifactId>
        <version>1.2.12</version><!-- or higher  -->
    </dependency>
        <dependency>
        <groupId>javax.cache</groupId>
        <artifactId>cache-api</artifactId>
        <version>0.8</version><!-- or higher  -->
    </dependency>

for manual management of jar .

 http://mixer2.org/dist/mixer2-cacheable-1.0.1-jars.zip
 
Usage
-----

sample for manual instantiation code.

before:

    Mixer2Engine mixer2Engine = new Mixer2Engine();

after:

    Mixer2Engine mixer2Engine = new CacheableMixer2Engine();

sample for Spring DI container configuration xml.

before:

    <bean
        id="mixer2Engine"
        class="org.mixer2.Mixer2Engine"
        scope="singleton" />

after:

    <bean
        id="mixer2Engine"
        class="org.mixer2.cacheable.CacheableMixer2Engine"
        scope="singleton" />

In default, Engine has SimpleCache instance that uses 
CuncurrentHashMap as cache store.

To use custom cache instance,
you should implement javax.cache.Cache interface
and call CacheableMixer2Engine#setCache(yourCache) .


Mixer2
------

for detail, see javadoc and [mixer2.org](http://mixer2.org/)

