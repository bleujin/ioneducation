package net.ion.bleujin.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import junit.framework.TestCase;
import net.ion.bleujin.ProxyClassLoader;
import net.ion.framework.util.Debug;

public class TestByteCode extends TestCase {

	
	
	public void testStd() throws Exception  {
		Class clz = Thread.currentThread().getContextClassLoader().loadClass("net.ion.bleujin.SayHi") ;
		Constructor cons = clz.getDeclaredConstructor(String.class);
		cons.setAccessible(true) ;
		
		Object ins = cons.newInstance("bleujin");
		Method m = clz.getMethod("hi") ;
		
		Object result = m.invoke(ins) ;
		Debug.line(result);
	}
	


	public void testByteCode() throws Exception {

		ClassPool cp = ClassPool.getDefault();

		CtClass cc = cp.get("net.ion.bleujin.reflection.Employee");
		CtMethod m = cc.getDeclaredMethod("sayHello");
		m.insertBefore("{ System.out.println(\"Hello.say():\"); }");

		Class newClz = cc.toClass() ;
		final Constructor cons = newClz.getDeclaredConstructor(String.class, int.class);
		cons.setAccessible(true) ;
		People people = (People)cons.newInstance("bleujin", 20) ;
		
		people.sayHello() ;

	}
	
	
	
	public void testProxy() throws Exception {
		ProxyClassLoader p = new ProxyClassLoader(getClass().getClassLoader());
		p.before("sayHello", "{ System.out.println(\"Hello.say():\"); }");
		
		Class<?> newClz = p.loadClass("net.ion.bleujin.reflection.Employee") ;
		final Constructor cons = newClz.getDeclaredConstructor(String.class, int.class);
		cons.setAccessible(true) ;
		People people = (People)cons.newInstance("bleujin", 20) ;
		
		people.sayHello() ;
	}
	
}
