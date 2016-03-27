package com.VTC.langlib.parse;

import java.io.File;
import java.util.List;

import com.VTC.langlib.structure.Instruction;

public interface ParseHandler {

	/**
	 * Return whether or not a given string is a command line flag.
	 * ** Precondition: The flag passed is not null. **
	 * @param possibleFlag The string that possibly is a flag.
	 * @return isFlag Whether or not the string is indeed a flag.
	 */
	public boolean isCommandLineInvocationFlag(String possibleFlag);

	/**
	 * Get the number of parameters of the flag that occur after the flag is called.
	 * ** Precondition: The flag passed is guaranteed to be valid and is not null. **
	 * @param currentFlag The flag for which the parameter count is being retrieved
	 * @return parameterCount The number of parameters which the flag specifies.
	 */
	public int getFlagParameterCount(String currentFlag);

	/**
	 * Given the passed parameters, modify the state of the language accordingly.
	 * ** Precondition: The flag passed is guaranteed to be valid and is not null. **
	 * @param flag
	 * @param parameters
	 */
	public FlagOperation getInstructionsGivenFlag(String flag);
	
	/**
	 * Given the passed file, parse the file into a list of instructions.
	 * ** Precondition: The file passed exists and is not null. **
	 * @param file The file which is assumed to contain the program instructions.
	 * @return instructions
	 */
	public List<Instruction> getProgramInstructions(File file);
	
}
