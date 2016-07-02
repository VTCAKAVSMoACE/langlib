package com.VTC.langlib.parse;

/**
 * Flag abstract class, providing a structure for flag operations to abide by.
 * Flag instances are executed by Lang to set up options for the language.
 * 
 * @author vtcakavsmoace
 *
 */
public abstract class Flag {

	/**
	 * The reference name or this flag. This is the name by which the flag
	 * should be called on the command line.
	 */
	private final String reference;

	/**
	 * Constructor for Flags. Subclasses of flag should create an empty
	 * constructor with a call to super(reference).
	 * 
	 * @param reference
	 *            The name by which the flag should be called on the command
	 *            line.
	 */
	public Flag(String reference) {
		this.reference = reference;
	}

	/**
	 * Abstract method for subclasses of Flag to implement as the actions taken
	 * by this flag when called when passed various flag arguments.
	 * 
	 * @param parameters
	 *            A string array through which the flag arguments from the
	 *            command line are passed.
	 */
	public abstract void execute(String[] parameters);

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public final boolean equals(Object anObject) {
		if (anObject instanceof Flag)
			return ((Flag) anObject).reference.equals(this.reference);
		else if (anObject instanceof String)
			return anObject.equals(reference);
		return false;
	}

}
