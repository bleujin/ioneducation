package net.ion.bleujin.async;

import junit.framework.TestCase;

public class PreKnowledge extends TestCase {

	
	private enum LANG {
		KOREAN {
			public String greet(){
				return "AnNyung" ;
			}
			
		}, ENGLISH{
			public String greet(){
				return "Hi" ;
			}
		}, INDO {
			public String greet(){
				return "Namaste" ;
			}
		} ;
		
		public abstract String greet() ;
	}

	private LANG lang;
	public PreKnowledge(String langCode){
		this.lang = LANG.valueOf(langCode) ;
	}
	
	
	public String greeting(String name){
		return lang.greet() + " " + name ;
	}
	
	
	
	public static void main(String[] args) {
		PreKnowledge kn = new PreKnowledge("INDO");
		
		System.out.println(kn.greeting("bleujin")) ;
	}
}
