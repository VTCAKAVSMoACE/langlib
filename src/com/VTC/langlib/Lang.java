package com.VTC.langlib;

import java.io.File;
import java.util.List;

import com.VTC.langlib.data.DataHandler;
import com.VTC.langlib.data.LanguageDetail;
import com.VTC.langlib.data.Setting;
import com.VTC.langlib.parse.ParseHandler;
import com.VTC.langlib.structure.Instruction;

public abstract class Lang implements Instruction {

	private LanguageDetail langDetails;

	private DataHandler dataHandler;

	private List<Setting> settings;

	private ParseHandler parseHandler;

	private List<Instruction> instructions;

	public Lang(LanguageDetail langDetails, DataHandler dataHandler, ParseHandler parseHandler, List<String> args) {

		this.setCurrentLanguageDetail(langDetails);
		this.setCurrentDataHandler(dataHandler);
		this.setCurrentParseHandler(parseHandler);

		configureArguments(args);

		configureInstructions(args.remove(0));

		configureInput(args);
	}

	private void configureInput(List<String> args) {
		dataHandler.handleInput(args);
	}

	private void configureArguments(List<String> args) {
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
				;
			}
		}
	}

	private void configureInstructions(String filename) {
		File fileToParse = new File(filename);
		this.setCurrentInstructions(parseHandler.getProgramInstructions(fileToParse));
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
	 * @return the settings
	 */
	public final List<Setting> getCurrentSettings() {
		return settings;
	}

	/**
	 * @param settings
	 *            the settings to set
	 */
	public final void setCurrentSettings(List<Setting> settings) {
		this.settings = settings;
	}

	/**
	 * @param settings
	 *            the settings to set
	 */
	public final void modifySetting(Setting setting) {
		int toReplace = settings.indexOf(setting.getReference());
		if (toReplace == -1) {
			settings.add(setting);
			return;
		}
		settings.set(toReplace, setting);
	}

	/**
	 * @return the instructions
	 */
	public final List<Instruction> getCurrentInstructions() {
		return instructions;
	}

	/**
	 * @param instructions
	 *            the instructions to set
	 */
	public final void setCurrentInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	public final void modifyInstruction(Instruction instruction, int index) {
		this.instructions.set(index, instruction);
	}

}
