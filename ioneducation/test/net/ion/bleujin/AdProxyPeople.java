package net.ion.bleujin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AdProxyPeople<T> implements InvocationHandler {

	public interface AOPHandler<T> {
		public T pre(Object proxy, Method m, Object[] args) ;
		public T after(Object proxy, Method m, Object[] args, Object result) ;
	}
	
	public enum Type {
		PRE, AFTER
	}
	
	private Object target;
	private AOPHandler<T> handler ;
	
	public AdProxyPeople(Object target, AOPHandler<T> handler) {
		this.target = target;
		this.handler = handler ;
	}

	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		handler.pre(proxy, m, args) ;
		final Object result = m.invoke(target, args);
		handler.after(proxy, m, args, result) ;
		return result;
	}

}
