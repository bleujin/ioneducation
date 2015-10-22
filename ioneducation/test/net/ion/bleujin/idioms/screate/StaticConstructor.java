package net.ion.bleujin.idioms.screate;

import net.ion.bleujin.idioms.bean.Address;
import net.ion.bleujin.idioms.bean.Person;

public class StaticConstructor {

	
	public void handle(){
		Person person = new Person();
		person.setName("bleujin") ;
		person.setAge(20) ;
		Address address = new Address();
		person.setAddress(address) ;
		
		
		// .....
	}
	
}
