package com.cache.model;

public class CacheObject {

	private String cacheValue;
	private long expiryTime;

	public CacheObject(String cacheValue, long expiryTime) {
		this.cacheValue = cacheValue;
		this.expiryTime = expiryTime;

	}

	public long getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(long expiryTime) {
		this.expiryTime = expiryTime;
	}

	public String getCacheValue() {
		return cacheValue;
	}

	public void setCacheValue(String cacheValue) {
		this.cacheValue = cacheValue;
	}

	public boolean isExpired() {
		return System.currentTimeMillis() > expiryTime;
	}

}
