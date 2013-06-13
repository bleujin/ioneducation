package net.ion.bleujin.reflection;

public class Employee implements People{

	private String name;
	private int age;

	
	private Employee(String name, int age){
		this.name = name ;
		this.age = age ;
	}
	
	public final static Employee create(String name, int age){
		return new Employee(name, age) ;
	}
	
	public Employee name(String name){
		this.name = name ;
		return this ;
	}
	
	public Employee age(int age){
		this.age = age ;
		return this ;
	}
	
	public String name(){
		return name ;
	}
	
	public int age(){
		return age ;
	}

	@Override
	public void sayHello() {
		System.out.println("hello " + name()) ;
	}
	
	
	
}
