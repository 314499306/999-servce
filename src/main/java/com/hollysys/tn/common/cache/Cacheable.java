package com.hollysys.tn.common.cache;

/**
 * this interface is for other class to implements, so that the CacheRegister can
 * refresh the registered classes.
 * @author sunf
 *
 */
public interface Cacheable {
	/**
	 * refreshes the registered observers.
	 */
	public void refresh();	

}
