package com.bpcl.util;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.spy.memcached.MemcachedClient;

/**
 * 
 * @author Pradeep
 *
 */
@Component
public class MemCache {

	private MemcachedClient mcc = null;

	@Value("${memcache.host}")
	private String memcacheHost;

	@Value("${memcache.port}")
	private int memcachePort;

	public MemcachedClient getInstance() throws IOException {
		if (mcc == null) {
			mcc = new MemcachedClient(new InetSocketAddress(memcacheHost, memcachePort));
		}
		return mcc;
	}

}