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
	 * class for the sake of the handleInput method and to be called through
	 * getCurrentDataHandler.
	 */
	private DataHandler dataHandler;

	/**
	 * The parsing handler for this language instance. May be called with
	 * getCurrentParseHandler.
	 */
	private ParseHandler parseHandler;

	/**
	 * The arguments for this language instance. This will change during runtime
	 * to accomodate for flags.
	 */
	private ArrayList<String> args;

	/**
	 * The instructions to reference when this language instance is executed.
	 */
	private ArrayList<? extends Instruction> instructions;

	/**
	 * Standard constructor for language instance. Note that all parameters
	 * except for the command line arguments will not be binding and may be
	 * replaced with the respective set methods.
	 * 
	 * @param langDetails
	 *            An instance of the LanguageDetails class desired to be used
	 *            for this language instance. May be replaced with
	 *            setCurrentLanguageDetail and retrieved with
	 *            getCurrentLanguageDetail
	 * @param dataHandler
	 *            An instance of the DataHandler class desired to be used for
	 *            this language instance. May be replaced with
	 *            setCurrentDataHandler and retrieved with
	 *            getCurrentDataHandler.
	 * @param parseHandler
	 *            An instance of the ParseHandler class desired to be used for
	 *            this language instance. May be replaced with
	 *            setCurrentParseHandler and retrieved with
	 *            getCurrentParseHandler.
	 * @param args
	 *            The arguments passed from the command line, but may be altered
	 *            beforehand.
	 */
	public Lang(LanguageDetail langDetails, DataHandler dataHandler, ParseHandler parseHandler,
			ArrayList<String> args) {

		this.setCurrentLanguageDetail(langDetails);
		this.setCurrentDataHandler(dataHandler);
		this.setCurrentParseHandler(parseHandler);
		this.args = args;
	}

	/**
	 * Helper method for .execute which calls upon the data handler to handle
	 * any command line arguments that are not flags.
	 * 
	 * @param args
	 *            The arguments passed from the command line after the flags have been configured.
	 */
	private void configureInput(ArrayList<String> args) {
		dataHandler.handleInput(args);
	}

	/**
	 * Helper method for .execute which handles all flags that DataHandler
	 * verifies is a valid flag, handling according to DataHandler's
	 * specification.
	 * 
	 * @param args
	 *            The arguments passed from the command line, exactly as passed.
	 */
	private void configureFlags(ArrayList<String> args) {
		String currentFlag = "";
		for (int currentFlagIndex = 0; currentFlagIndex < args.size(); currentFlagIndex++) {
			currentFlag = args.get(currentFlagIndex);
			if (dataHandler.isCommandLineInvocationFlag(currentFlag)) {
				int parameterCount = dataHandler.getFlagParameterCount(currentFlag);
				String[] parameters = new String[parameterCount];
				for (int currentParameter = 0; currentParameter < parameterCount; currentParameter++) {
					parameters[currentParameter] = args.remove(currentFlagIndex + 1);
				}
				dataHandler.getInstructionGivenFlag(args.remove(currentFlagIndex)).execute(parameters);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.VTC.langlib.structure.Instruction#execute()
	 */
	@Override
	public void execute() {
		configureFlags(args);

		if (args.size() > 0)
			configureInstructions(args.remove(0));
		else if (this.instructions == null)
			this.instructions = new ArrayList<Instruction>();

		configureInput(args);
	}

	/**
	 * When passed a string, this will request that the ParseHandler parse the
	 * file by the name of the given string, proceding to set the current
	 * instruction list to the instructions which it returns.
	 * 
	 * @param filename
	 *            The name of the file for the ParseHandler to parse.
	 */
	private void configureInstructions(String filename) {
		this.setCurrentInstructions(parseHandler.getProgramInstructions(filename));
	}

	/**
	 * Getter method for the LanguageDetail instance being used by the Lang
	 * instance.
	 * 
	 * @return langDetails the LanguageDetail instance being used by the Lang
	 *         instance.
	 */
	public final LanguageDetail getCurrentLanguageDetail() {
		return langDetails;
	}

	/**
	 * Setter method for the LanguageDetail instance being used by the Lang
	 * instance.
	 * 
	 * @param langDetails
	 *            the LanguageDetail instance being used by the Lang instance.
	 */
	public final void setCurrentLanguageDetail(LanguageDetail langDetails) {
		this.langDetails = langDetails;
	}

	/**
	 * Getter method for the DataHandler instance being used by the Lang
	 * instance.
	 * 
	 * @return dataHandler the DataHandler instance being used by the Lang
	 *         instance.
	 */
	public final DataHandler getCurrentDataHandler() {
		return dataHandler;
	}

	/**
	 * Setter method for the DataHandler instance being used by the Lang
	 * instance.
	 * 
	 * @param dataHandler
	 *            the DataHandler instance being used by the Lang instance.
	 */
	public final void setCurrentDataHandler(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}

	/**
	 * Getter method for the ParseHandler instance being used by the Lang
	 * instance.
	 * 
	 * @return parseHandler the ParseHandler instance being used by the Lang
	 *         instance.
	 */
	public final ParseHandler getCurrentParseHandler() {
		return parseHandler;
	}

	/**
	 * Setter method for the ParseHandler instance being used by the Lang
	 * instance.
	 * 
	 * @param parseHandler
	 *            the ParseHandler instance being used by the Lang instance.
	 */
	public final void setCurrentParseHandler(ParseHandler parseHandler) {
		this.parseHandler = parseHandler;
	}

	/**
	 * Getter method for the instructions list being used by the Lang instance.
	 * 
	 * @return instructions the instructions list being used by the Lang
	 *         instance.
	 */
	public final ArrayList<? extends Instruction> getInstructions() {
		return instructions;
	}

	/**
	 * Setter method for the instructions list being used by the Lang instance.
	 * 
	 * @param instructions
	 *            the instructions list being used by the Lang instance.
	 */
	public final void setCurrentInstructions(ArrayList<? extends Instruction> instructions) {
		this.instructions = instructions;
	}

}
