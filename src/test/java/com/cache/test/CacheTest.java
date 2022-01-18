package com.cache.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.cache.controller.CacheImpl;


class CacheTest {

	private CacheImpl impl;

	@Test
	void testForCount() {
		impl = new CacheImpl();
		impl.addCacheData("1", "cache1", 100);
		impl.addCacheData("2", "cache2", 150);
		impl.addCacheData("3", "cache3", 200);
		assertEquals(impl.size(), 3);
	}
	
	@Test
	void testForValue() {
		impl = new CacheImpl();
		impl.addCacheData("1", "cache1", 100);
		impl.addCacheData("2", "cache2", 150);
		impl.addCacheData("3", "cache3", 200);
		assertEquals(impl.getCacheDataOnKey("1"),"cache1");
		assertNull(impl.getCacheDataOnKey("4"));
	}

}
