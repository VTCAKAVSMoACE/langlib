package com.VTC.langlib.structure;

/**
 * Fundamental unit of instructions in langlib-based languages. Instruction
 * instances can be stored directly as executable instructions for Lang to
 * execute individually as part of its own .execute method.
 * 
 * @see com.VTC.langlib.Lang#getInstructions()
 * 
 * @author vtcakavsmoace
 *
 */
public interface Instruction {

	/**
	 * Provided as a method to enact instructions specified by the
	 * implementation of Instruction. This method will be called by Lang or by
	 * any other member of the language capable of enacting instructions.
	 */
	public void execute();

}
