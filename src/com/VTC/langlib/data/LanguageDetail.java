package com.VTC.langlib.data;

/**
 * LanguageDetail interface which provides a structure for noting details about
 * any langlib-based languages. Data about the language should be stored in this
 * class.
 * 
 * @author vtcakavsmoace
 *
 */
public interface LanguageDetail {

	/**
	 * Getter method for the name of the language.
	 * 
	 * @return languageName The name of the language.
	 */
	public String getName();

	/**
	 * Getter method for the current version of the language.
	 * 
	 * @return languageVersion The version of the language.
	 */
	public String getVersion();

	/**
	 * Getter method for the name(s) of the author(s) of the language.
	 * 
	 * @return languageAuthor The name(s) of the author(s) of the language.
	 */
	public String getAuthor();

	/**
	 * Getter method for any extraneous data about the language that the
	 * author(s) wish to convey.
	 * 
	 * @return languageMisc Bonus content about the language.
	 */
	public String getMisc();

	/**
	 * Simply formatted information about the language. This is not the required
	 * way to do this, of course, but it is a quick and dirty way to report
	 * language details.
	 */
	public default void reportLanguageDetails() {
		System.out.println(getName());
		System.out.println(getVersion());
		System.out.println(getAuthor());
		System.out.println(getMisc());
	}

}
