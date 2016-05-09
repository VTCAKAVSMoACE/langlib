package com.VTC.langlib.parse;

/**
 * @author vtcakavsmoace
 *
 */
public abstract class Flag {
	
	/**
	 * 
	 */
	private final String reference;
	
	/**
	 * @param reference
	 */
	public Flag(String reference) {
		this.reference = reference;
	}
	
	/**
	 * @param parameters
	 * @throws InvalidSettingChangeException
	 */
	public abstract void execute(String[] parameters);
	
	public final boolean equals(Object anObject) {
		if (anObject instanceof Flag)
			return ((Flag) anObject).reference.equals(this.reference);
		else if (anObject instanceof String) 
			return anObject.equals(reference);
		return false;
	}
	
}
