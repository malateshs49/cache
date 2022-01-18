package com.cache.model;

public interface Cache {

	void addCacheData(String key, String value, long periodInMillis);

	void removeFromCache(String key);

	String getCacheDataOnKey(String key);

	void clearAll();

	long size();

}
