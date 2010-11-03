package br.com.jera.botaoteca;

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
