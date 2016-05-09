package com.VTC.langlib;

import java.util.ArrayList;

import com.VTC.langlib.data.DataHandler;
import com.VTC.langlib.data.LanguageDetail;
import com.VTC.langlib.parse.ParseHandler;
import com.VTC.langlib.structure.Instruction;

/**
 * 
 * Lang class, providing the framework for the main instantiable class of the
 * new language. Mainly here to provide a superclass which will store and return
 * all of the necessary values for the language to be able to interact with
 * itself (and customizable usage).
 * 
 * For the sake of being meta-language capable, Lang implements Instruction to
 * allow it to be recognized as a command.
 * 
 * @author vtcakavsmoace
 *
 */
public abstract class Lang implements Instruction {

	/**
	 * The language detailer for this language instance. May be called with
	 * getCurrentLanguageDetail, but information intended to be output may be
	 * relayed through reportLanguageDetails.
	 */
	private LanguageDetail langDetails;

	/**
	 * The data handler for this language instance. It is only here in this
	 * class for the sake of the handleInput method.
	 */
	private DataHandler dataHandler;

	/**
	 * 
	 */
	private ParseHandler parseHandler;

	/**
	 * 
	 */
	private ArrayList<? extends Instruction> instructions;

	/**
	 * @param langDetails
	 * @param dataHandler
	 * @param parseHandler
	 * @param args
	 * @throws Exception
	 */
	public Lang(LanguageDetail langDetails, DataHandler dataHandler, ParseHandler parseHandler, ArrayList<String> args)
			throws Exception {

		this.setCurrentLanguageDetail(langDetails);
		this.setCurrentDataHandler(dataHandler);
		this.setCurrentParseHandler(parseHandler);

		configureSettings();

		configureArguments(args);

		configureInstructions(args.remove(0));

		configureInput(args);
	}

	/**
	 * @param args
	 */
	private void configureInput(ArrayList<String> args) {
		dataHandler.handleInput(args);
	}

	/**
	 * @param args
	 * @throws InvalidSettingChangeException
	 */
	private void configureArguments(ArrayList<String> args) {
		String currentFlag = null;
		for (int currentFlagIndex = 0; currentFlagIndex < args.size(); currentFlagIndex++) {
			currentFlag = args.get(currentFlagIndex);
			if (parseHandler.isCommandLineInvocationFlag(currentFlag)) {
				int parameterCount = parseHandler.getFlagParameterCount(currentFlag);
				String[] parameters = new String[parameterCount];
				for (int currentParameter = 0; currentParameter < parameterCount; currentParameter++) {
					parameters[currentParameter] = args.remove(currentFlagIndex + 1);
				}
				parseHandler.getInstructionsGivenFlag(args.remove(currentFlagIndex)).execute(parameters);
			}
		}
	}

	/**
	 * @throws InvalidSettingChangeException
	 */
	public abstract void configureSettings();

	/**
	 * @param filename
	 */
	private void configureInstructions(String filename) {
		this.setCurrentInstructions(parseHandler.getProgramInstructions(filename));
	}

	/**
	 * @return the langDetails
	 */
	public final LanguageDetail getCurrentLanguageDetail() {
		return langDetails;
	}

	/**
	 * @param the
	 *            langDetails
	 */
	public final void setCurrentLanguageDetail(LanguageDetail langDetails) {
		this.langDetails = langDetails;
	}

	/**
	 * 
	 */
	public final void reportLanguageDetails() {
		System.out.println(langDetails.getName());
		System.out.println(langDetails.getVersion());
		System.out.println(langDetails.getAuthor());
		System.out.println(langDetails.getMisc());
	}

	/**
	 * @return the dataHandler
	 */
	public final DataHandler getCurrentDataHandler() {
		return dataHandler;
	}

	/**
	 * @param dataHandler
	 */
	public final void setCurrentDataHandler(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}

	/**
	 * @return the parseHandler
	 */
	public final ParseHandler getCurrentParseHandler() {
		return parseHandler;
	}

	/**
	 * @param parseHandler
	 *            the parseHandler to set
	 */
	public final void setCurrentParseHandler(ParseHandler parseHandler) {
		this.parseHandler = parseHandler;
	}

	/**
	 * @return the instructions
	 */
	public final ArrayList<? extends Instruction> getCurrentInstructions() {
		return instructions;
	}

	/**
	 * @param arrayList
	 *            the instructions to set
	 */
	public final void setCurrentInstructions(ArrayList<? extends Instruction> arrayList) {
		this.instructions = arrayList;
	}

}
