package net.ion.bleujin;

public class SayHi {

	private String name;

	public SayHi(String name) {
		this.name = name ;
	}
	
	public String hi() {
		return name + " .. !" ;
	}
}
