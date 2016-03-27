package com.VTC.langlib.data;

public abstract class Setting {
	
	private final String reference;
	
	public Setting(String reference) {
		this.reference = reference;
	}
	
	public final String getReference() {
		return reference;
	}
	
	public abstract void setSetting(Object newSetting);
	
	public abstract Object getSetting();
	
	public final boolean equals(Object anObject) {
		if (anObject instanceof Setting) {
			return this.getReference().equals(((Setting) anObject).getReference()) && this.getSetting().equals(((Setting) anObject).getSetting());
		} else if (anObject instanceof String) {
			return this.getReference().equals(anObject.toString());
		}
		return false;
	}
	
}
