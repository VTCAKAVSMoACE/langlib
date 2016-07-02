package com.VTC.langlib.parse;

import java.util.ArrayList;

import com.VTC.langlib.structure.Instruction;

/**
 * ParseHandler interface, defining the structure of all langlib-based parsing.
 * This class should provide the methods through which the language may be
 * parsed and prepared for execution.
 * 
 * @author vtcakavsmoace
 *
 */
public interface ParseHandler {

	/**
	 * Given the passed file, parse the file into a list of instructions.
	 * Precondition: The file passed exists and is not null.
	 * 
	 * @param filename
	 *            The file which is assumed to contain the program instructions.
	 * @return instructions The instructions which should be run according to
	 *         the target program written in this langlib-based language.
	 */
	public ArrayList<? extends Instruction> getProgramInstructions(String filename);

}
