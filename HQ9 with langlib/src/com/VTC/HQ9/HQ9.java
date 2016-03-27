package com.VTC.HQ9;

import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

import com.VTC.HQ9.data.DataHandler;
import com.VTC.HQ9.data.LanguageDetail;
import com.VTC.HQ9.parse.ParseHandler;
import com.VTC.langlib.structure.Instruction;

public class HQ9 extends com.VTC.langlib.Lang {
	
	private static HQ9 currentInstance;

	public static void main(String[] args) throws IOException {
		currentInstance = new HQ9(new LanguageDetail(), new DataHandler(), new ParseHandler(), new ArrayList<String>(Arrays.asList(args)));
		currentInstance.execute();
	}
	
	public HQ9(LanguageDetail langDetails, DataHandler dataHandler, ParseHandler parseHandler, ArrayList<String> args) {
		super(langDetails, dataHandler, parseHandler, args);
	}

	@Override
	public void execute() {
		for (Instruction currInstruct : getCurrentInstructions()) {
			currInstruct.execute();
		}
	}
	
	public static HQ9 getCurrentInstance() {
		return currentInstance;
	}

	public String getSourceCode() {
		String toReturn = "";
		for (Instruction currInstruct : getCurrentInstructions()) {
			toReturn += currInstruct.toString();
		}
		return toReturn;
	}

}
