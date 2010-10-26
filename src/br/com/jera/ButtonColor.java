package br.com.jera;

public enum ButtonColor {
	
	GREEN ("Green"),
	BLUE ("Blue"),
	RED ("Red"),
	YELLOW ("Yellow"),
	ORANGE ("Orange");
	
	private String name;
	
	ButtonColor(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}


}
