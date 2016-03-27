package com.VTC.HQ9.structure;

public class Nine implements com.VTC.langlib.structure.Instruction {

	public void execute() {
		for (int i = 99; i > 2;) {
			System.out.print(
					i + " bottles of beer on the wall, " + i + " bottles of beer.\nTake one down and pass it around, ");
			i--;
			System.out.println(i + " bottles of beer on the wall.\n");
		}
		System.out.println(
				"2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n");
		System.out.println(
				"1 bottle of beer on the wall, 1 bottle of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.");
		System.exit(0);
	}
	
	public String toString() {
		return "9";
	}

}
