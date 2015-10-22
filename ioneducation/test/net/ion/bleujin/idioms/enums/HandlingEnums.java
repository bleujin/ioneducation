package net.ion.bleujin.idioms.enums;

public class HandlingEnums {

	
	
	public void badHandle(ChatMessage message){
		
		if ("join".equals(message.getType())){
			// 
		} else if ("chat".equals(message.getType())){
			// 
		} else if ("invite".equals(message.getType())){
			// 
		} else if ("exit".equals(message.getType())) {
			// 
		}
	}
	
	
	
}




class ChatMessage {
	
	public String getType(){
		return null ;
	}
	
	
}
