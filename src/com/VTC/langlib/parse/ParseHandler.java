package com.VTC.langlib.parse;

import java.util.ArrayList;

import com.VTC.langlib.structure.Instruction;

public interface ParseHandler {
	
	/**
	 * Given the passed file, parse the file into a list of instructions.
	 * ** Precondition: The file passed exists and is not null. **
	 * @param file The file which is assumed to contain the program instructions.
	 * @return instructions
	 */
	public ArrayList<? extends Instruction> getProgramInstructions(String filename);
	
}
