package com.VTC.HQ9.structure;

import com.VTC.HQ9.HQ9;

public class Q implements com.VTC.langlib.structure.Instruction {
	
	public void execute() {
		System.out.print(HQ9.getCurrentInstance().getSourceCode());
	}
	
	public String toString() {
		return "Q";
	}

}
