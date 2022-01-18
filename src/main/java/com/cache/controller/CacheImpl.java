package com.cache.controller;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.cache.model.Cache;
import com.cache.model.CacheObject;

import java.lang.ref.SoftReference;

public class CacheImpl implements Cache {

	private final ConcurrentHashMap<String, SoftReference<CacheObject>> cache = new ConcurrentHashMap<>();

	public CacheImpl() {
		int cleanUpTime = 10;
		Thread thread = new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					//runs in 10 seconds
					Thread.sleep(cleanUpTime * 1000);
					cache.entrySet().removeIf(entry -> Optional.ofNullable(entry.getValue()).map(SoftReference::get)
							.map(CacheObject::isExpired).orElse(false));
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
	}

	public void addCacheData(String key, String value, long periodInMillis) {
		if (key == null) {
			return;
		}
		if (value == null) {
			cache.remove(key);
		} else {
			long expiryTime = System.currentTimeMillis() + periodInMillis;
			cache.put(key, new SoftReference<>(new CacheObject(value, expiryTime)));
		}

	}

	public void removeFromCache(String key) {
		cache.remove(key);

	}

	public String getCacheDataOnKey(String key) {
		return Optional.ofNullable(cache.get(key)).map(SoftReference::get)
				.filter(cacheObject -> !cacheObject.isExpired()).map(CacheObject::getCacheValue).orElse(null);
	}

	public void clearAll() {
		cache.clear();

	}

	public long size() {
		return cache.entrySet().stream().filter(entry -> Optional.ofNullable(entry.getValue()).map(SoftReference::get)
				.map(cacheObject -> !cacheObject.isExpired()).orElse(false)).count();
	}
	
}
