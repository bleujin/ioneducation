package net.ion.bleujin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import net.ion.framework.util.Debug;

public class ProxyPeople implements InvocationHandler {

	private Object target;
	public ProxyPeople(Object target) {
		this.target = target;
	}
	
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		pre(m, args) ;
		final Object result = m.invoke(target, args);
		after(m, args) ;
		return result;
	}
	
	public void pre(Method m, Object[] args) {
		Debug.line("pre", m, args) ;
	}

	public void after(Method m, Object[] args) {
		Debug.line("after", m, args) ;
	}

}
