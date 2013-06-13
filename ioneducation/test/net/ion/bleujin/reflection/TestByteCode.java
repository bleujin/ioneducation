package net.ion.bleujin.reflection;

import java.lang.reflect.Constructor;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import junit.framework.TestCase;

public class TestByteCode extends TestCase {

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
}
