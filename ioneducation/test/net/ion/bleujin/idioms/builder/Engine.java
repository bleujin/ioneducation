package net.ion.bleujin.idioms.builder;

import java.util.HashMap;
import java.util.Map;

public class Engine {

	
	private boolean useCache ;
	private boolean useInterpreting ;
	private Map<String, String> cache = new HashMap<String, String>() ;
	
	
	public void useCache(boolean useCache){
		this.useCache = useCache ;
	}
	
	public void useInterprinting(boolean useInterprinting){
		this.useInterpreting = useInterprinting ;
	}
	
	public String transform(String key){
		if (useCache && cache.containsKey(key)){
			return cache.get(key) ;
		} else {
			String result = key.toUpperCase() ;
			cache.put(key, result) ;
			return result ;
		}
	}
	
}
