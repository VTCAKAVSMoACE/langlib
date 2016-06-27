package com.VTC.langlib.data;

public interface LanguageDetail {

	/**
	 * @return
	 */
	public String getName();
	
	/**
	 * @return
	 */
	public String getVersion();
	
	/**
	 * @return
	 */
	public String getAuthor();
	
	/**
	 * @return
	 */
	public String getMisc();
	
	/**
	 * 
	 */
	public default void reportLanguageDetails() {
		System.out.println(getName());
		System.out.println(getVersion());
		System.out.println(getAuthor());
		System.out.println(getMisc());
	}
	
}
