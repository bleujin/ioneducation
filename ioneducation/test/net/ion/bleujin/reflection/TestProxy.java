package net.ion.bleujin.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import junit.framework.TestCase;
import net.ion.bleujin.AdProxyObj;
import net.ion.bleujin.ProxyObj;
import net.ion.framework.util.Debug;

public class TestProxy extends TestCase {

	public void testCreateProxy() throws Exception {
		People ori = Employee.create("bleujin", 20) ;
//		instance.sayHello() ;
		
		People proxy = (People)Proxy.newProxyInstance(this.getClass().getClassLoader(), ori.getClass().getInterfaces(), new ProxyObj(ori));
		
//		people.name() ;
//		people.age() ;
//		
		proxy.sayHello() ;
	}
	
	
	public void testAdvanceProxy() throws Exception {
		People ori = Employee.create("bleujin", 20) ;
		
		People proxy = (People)Proxy.newProxyInstance(this.getClass().getClassLoader(), ori.getClass().getInterfaces(), new AdProxyObj<Object>(ori, 
				new AdProxyObj.AOPHandler<Object>() {
			
					public void pre(Object proxy, Method m, Object[] args) {
						System.out.println(m.getName() + " invocated") ;
					}
					public void after(Object proxy, Method m, Object[] args, Object result) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
						System.out.println(m.getName() + " executed and result = " + m.invoke(ori, args)) ;
					}
					public Object replace(Object proxy, Method m, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//						return m.invoke(ori, args) ;
						return "Hello" ;
					}
					
		}));
		
		String rtn = proxy.name() ;
		Debug.line(rtn) ;
	}
	
	
}
