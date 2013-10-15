package net.ion.bleujin.idioms.dcheck;

import org.apache.http.annotation.NotThreadSafe;


@NotThreadSafe
public class DoubleCheckedLocking {

	
	private static DoubleCheckedLocking self ;
	
	public static DoubleCheckedLocking badInstance(){
		if (self == null){
			synchronized(DoubleCheckedLocking.class){
				if (self == null){
					self = new DoubleCheckedLocking() ;
				}
			}
		}
		return self ;
	}
	
	public synchronized static DoubleCheckedLocking goodInstance(){
		if (self == null){
			self = new DoubleCheckedLocking() ;
		}
		return self ;
	}

	
	
}
