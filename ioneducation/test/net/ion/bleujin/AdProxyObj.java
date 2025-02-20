package net.ion.bleujin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AdProxyObj<T> implements InvocationHandler {

	public interface AOPHandler<T> {
		public void pre(Object proxy, Method m, Object[] args) throws Exception ;
		public void after(Object proxy, Method m, Object[] args, Object result) throws Exception ;
		public T replace(Object target, Method m, Object[] args) throws Exception ;
	}
	
	public enum Type {
		PRE, AFTER
	}
	
	private Object target;
	private AOPHandler<T> handler ;
	
	public AdProxyObj(Object target, AOPHandler<T> handler) {
		this.target = target;
		this.handler = handler ;
	}

	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		handler.pre(proxy, m, args) ;
		final Object result =  handler.replace( target, m, args);
		handler.after(proxy, m, args, result) ;
		return result;
	}

}
