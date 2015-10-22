package net.ion.bleujin.idioms.chain;

import java.util.HashMap;
import java.util.Map;

import net.ion.bleujin.idioms.bean.Person;

public class CallChain {

	
	public Map toMap(Person person){
		
		Map<String, Object> map = new HashMap<String, Object>() ;
		
		map.put("name", person.getName()) ;
		map.put("age", person.getAge()) ;
		map.put("city", person.getAddress().getCity()) ;
		map.put("detail", person.getAddress().getDetail()) ;
		
		return map ;
	}
}
