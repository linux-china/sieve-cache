package org.mvnsearch;

import org.junit.jupiter.api.Test;
import org.mvnsearch.SieveCache;

public class SieveCacheTest {
    private static SieveCache cache = new SieveCache();

    @Test
    public void testCache() {
        cache.put("nick", "Jackie");
        System.out.println(cache.get("nick"));
        cache.delete("nick");
        System.out.println(cache.get("nick"));
    }

    @Test
    public void testShowCache() {
        cache.put("1", "First");
        cache.put("2", "Two");
        cache.put("3", "Three");
        cache.put("4", "Four");
        cache.showItems();
        cache.delete("2");
        System.out.println("========");
        cache.showItems();
    }

    @Test
    public void testCapacity() {
        SieveCache cache = new SieveCache(3);
        cache.put("1", "First");
        cache.put("2", "Two");
        cache.put("3", "Three");
        cache.put("4", "Four");
        cache.put("5", "Five");
        cache.showItems();
    }
}
