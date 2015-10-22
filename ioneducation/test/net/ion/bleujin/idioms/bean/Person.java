package net.ion.bleujin.idioms.bean;


public class Person {
	
	private String name ;
	private int age ;
	private Address address ;
	
	public static Person create(String name, int age){
		Person person = new Person();
		person.setName(name) ;
		person.setAge(age) ;
		
		person.address = new Address() ;
		return person ;
	}

	
	public void setName(String name){
		this.name = name ;
	}
	
	public String getName(){
		return name ;
	}
	
	public void setAge(int age){
		this.age = age ;
	}
	
	public int getAge(){
		return age ;
	}
	
	public Address getAddress(){
		return address ;
	}


	public void setAddress(Address address) {
		this.address = address ;
	}
	
	
}