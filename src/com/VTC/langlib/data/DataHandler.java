package com.VTC.langlib.data;

import java.util.List;

import com.VTC.langlib.parse.Flag;

/**
 * DataHandler interface, defining the structure of all langlib-based data
 * handling. All data, such as stacks or variables, should be stored and
 * manipulated via this class.
 * 
 * @author vtcakavsmoace
 *
 */
public interface DataHandler {

	/**
	 * Handles input through command line arguments intended for the target
	 * program's use.
	 * 
	 * Precondition: all flags are handled by the Lang instance before calling
	 * this method.
	 * 
	 * @param commandLineArguments
	 *            The arguments intended to be added as data to the Lang
	 *            instance by whatever means chosen by the implemented
	 *            DataHandler.
	 */
	public void handleInput(List<String> commandLineArguments);

	/**
	 * Return whether or not a given string is a command line flag.
	 * 
	 * Precondition: The flag passed is not null.
	 * 
	 * @param possibleFlag
	 *            The string that possibly is a flag.
	 * @return isFlag Whether or not the string is indeed a flag.
	 */
	public boolean isCommandLineInvocationFlag(String possibleFlag);

	/**
	 * Get the number of parameters of the flag that occur after the flag is
	 * called.
	 * 
	 * Precondition: The flag passed is guaranteed to be valid and is not null.
	 * 
	 * @param currentFlag
	 *            The flag for which the parameter count is being retrieved
	 * @return parameterCount The number of parameters which the flag specifies.
	 */
	public int getFlagParameterCount(String currentFlag);

	/**
	 * Given the passed parameters, modify the state of the language
	 * accordingly.
	 * 
	 * Precondition: The flag passed is guaranteed to be valid and is not null.
	 * 
	 * @param flag
	 *            The flag whose instructions are attempted to being retrieved.
	 * @return flagInstructions The instructions intended to be enacted due to
	 *         this flag's existence.
	 */
	public Flag getInstructionGivenFlag(String flag);

}