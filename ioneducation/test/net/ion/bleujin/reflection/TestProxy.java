package net.ion.bleujin.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import junit.framework.TestCase;
import net.ion.bleujin.AdProxyObj;
import net.ion.bleujin.ProxyObj;
import net.ion.bleujin.AdProxyObj.Type;

public class TestProxy extends TestCase {

	public void testCreateProxy() throws Exception {
		Employee instance = Employee.create("bleujin", 20) ;
//		instance.sayHello() ;
		
		
		
		People people = (People)Proxy.newProxyInstance(this.getClass().getClassLoader(), instance.getClass().getInterfaces(), new ProxyObj(instance));
		
//		people.name() ;
//		people.age() ;
//		
		people.sayHello() ;
	}
	
	
	public void testAdvanceProxy() throws Exception {
		Employee instance = Employee.create("bleujin", 20) ;
		
		People people = (People)Proxy.newProxyInstance(this.getClass().getClassLoader(), instance.getClass().getInterfaces(), new AdProxyObj<Void>(instance, 
				new AdProxyObj.AOPHandler<Void>() {
					@Override
					public Void pre(Object proxy, Method m, Object[] args) {
						System.out.println(m.getName() + " invocated") ;
						return null ;
					}
					public Void after(Object proxy, Method m, Object[] args, Object result) {
						System.out.println(m.getName() + " executed and result = " + result) ;
						return null;
					}
		}));
		
		people.name() ;
		people.age() ;
		people.sayHello() ;
	}
	
	
}
