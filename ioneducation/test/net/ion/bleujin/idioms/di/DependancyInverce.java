package net.ion.bleujin.idioms.di;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.ion.bleujin.idioms.bean.Person;

public class DependancyInverce {

	
	public void writePerson() throws IOException{
		FileWriter writer = new FileWriter(new File("./resource/person.txt")) ;
		
		Person person = Person.create("bleujin", 20);
		
		writer.write("name:" + person.getName()) ;
		writer.write(",age:" + person.getAge()) ;
		writer.write(",city:" + person.getAddress().getCity()) ;
		writer.write(",detail:" + person.getAddress().getDetail()) ;
	}
	
}





