package com.VTC.HQ9.parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.VTC.HQ9.structure.*;
import com.VTC.langlib.parse.Flag;
import com.VTC.langlib.structure.Instruction;

public class ParseHandler implements com.VTC.langlib.parse.ParseHandler {

	@Override
	public boolean isCommandLineInvocationFlag(String possibleFlag) {
		/** WHAT ARE THOSE?!? */
		return false;
	}

	@Override
	public int getFlagParameterCount(String currentFlag) {
		/** This is never called anyway, since all is command line invocation flags booleans are false */
		return 0;
	}

	@Override
	public Flag getInstructionsGivenFlag(String flag) {
		/** Nothing to see here. */
		return null;
	}

	@Override
	public ArrayList<Instruction> getProgramInstructions(String filename) {
		String stringToParse = "";
		try {
			File file = new File(filename);
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();

			stringToParse = new String(data, "UTF-8");
		} catch (IOException e) {}
		ArrayList<Instruction> instructions = new ArrayList<Instruction>(0);
		for (char currParse : stringToParse.toCharArray())
			switch(currParse) {
			case 'H':
				instructions.add(new H());
				break;
			case 'Q':
				instructions.add(new Q());
				break;
			case '9':
				instructions.add(new Nine());
				break;
			}
		return instructions;
	}

}
