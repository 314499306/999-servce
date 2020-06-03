package com.hollysys.tn.common.cache;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.CacheEntry;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * for cache management.
 * supports cluster enviroment.
 * @author sunf
 */
public class CacheManager {
	private static GeneralCacheAdministrator cacheAdmin = new GeneralCacheAdministrator();

	/**
	 * shuts down the cache administrator.
	 * should NOT be called by functions code.
	 * should be called by System admin module.
	 */
	public static void stop() {
		cacheAdmin.destroy();	
	}

	/**
	 * flushes the entire cache immediately.
	 */
	public static void flushAll() {		
//		cacheAdmin.flushAll();
		cacheAdmin.destroy();	
		cacheAdmin = new GeneralCacheAdministrator();
	}

	/**
	 * flushes a single cache entry.
	 * 
	 * @param key key is the cache to be flushed
	 */
	public static void flushEntry(String key) {
		cacheAdmin.flushEntry(key);
	}

	/**
	 * obtains a cache.
	 * 
	 * @return a <code>Cache</code> for more control
	 */
	protected static Cache getCache() {
		return cacheAdmin.getCache();
	}

	/**
	 * gets an object from the cache.
	 * it will returns a <code>null</code>
	 * when no cache entry could be found with the supplied key, 
	 * or when an entry was found but is considered out of date. 
	 * 
	 * if the cache entry is a new entry that is currently being constructed 
	 * this method will block until the new entry becomes available. 
	 * similarly, it will block if a stale entry is currently being rebuilt 
	 * by another thread and cache blocking is enabled (cache.blocking=true).
	 * @param key the cache key
	 * @return the content with the same key as the parameter
	 */
	public static Object getFromCache(String key) {
		Object obj=null;
		try {
			obj=cacheAdmin.getFromCache(key, CacheEntry.INDEFINITE_EXPIRY);
		} catch (NeedsRefreshException e) {			
			cacheAdmin.cancelUpdate(key);
		}
		return obj;
	}

	/**
	 * put an object in a cache.
	 * with the same key will overwrite the old content in the cache.
	 * @param key key to the content
	 * @param content content of the key
	 */
	public static void putInCache(String key, Object content) {
		cacheAdmin.putInCache(key, content);		
	}

	/**
	 * puts an object in a cache by groups.
	 * @param key The unique key for this cached object
	 * @param content The object to store
	 * @param groups The groups that this object belongs to
	 */
	public static void putInCache(String key, Object content, String[] groups) {
		cacheAdmin.putInCache(key, content, groups);
	}
	
	/**
	 * puts an object in a cache by groups.
	 * @param key the unique key for this cached object
	 * @param content the object to store
	 * @param groups the group that this object belongs to
	 */
	public static void putInCache(String key, Object content, String group) {
		cacheAdmin.putInCache(key, content, new String[] {group});
	}
	
	/**
	 * flushes all items that belong to the specified group. 
	 * @param group the name of the group to flush
	 */
	public static void flushGroup(String group) {
		cacheAdmin.flushGroup(group);
	}
	
	/**
	 * removes an object(key and content) from the cache.
	 * 
	 * @param key key to be removed
	 */
	public static void removeEntry(String key) {
		cacheAdmin.removeEntry(key);
	}
	
	/**
	 * tests whether the specified key has cached object
	 * @param key the unique key for cached object 
	 * @return true if exists, false otherwise
	 */
	public static boolean isCacheKeyExisting(String key) {
		return getFromCache(key)==null?false:true;
	}

}
