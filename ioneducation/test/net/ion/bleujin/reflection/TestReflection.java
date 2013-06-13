package net.ion.bleujin.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import junit.framework.TestCase;

public class TestReflection extends TestCase {

	public void testConstructor() throws Exception {
		Constructor<Employee> cons = Employee.class.getDeclaredConstructor(String.class, int.class);
		cons.setAccessible(true) ;
		Employee bleujin = cons.newInstance("bleujin", 20) ;
		
		assertEquals("bleujin", bleujin.name()) ;
		assertEquals(20, bleujin.age()) ;
	}
	
	
	public void testMethod() throws Exception {
		final Class<Employee> clz = Employee.class;
		Constructor<Employee> cons = clz.getDeclaredConstructor(String.class, int.class);
		cons.setAccessible(true) ;
		Employee instance = cons.newInstance("bleujin", 20) ;

		Method m = clz.getMethod("name", String.class);
		Object result = m.invoke(instance, "hero");
		
		
		
		assertEquals(true, instance == result) ;
		assertEquals("hero", instance.name()) ;
		
		assertEquals(Employee.class, m.getReturnType()) ;
		assertEquals(String.class, m.getParameterTypes()[0]) ;
		assertEquals("name", m.getName()) ;
		assertEquals(true, m.getModifiers() % Modifier.PUBLIC == 0) ;
	}
	
	
	
	public void testField() throws Exception {
		final Class<Employee> clz = Employee.class;
		Constructor<Employee> cons = clz.getDeclaredConstructor(String.class, int.class);
		cons.setAccessible(true) ;
		Employee instance = cons.newInstance("bleujin", 20) ;
		
		
		Field field = clz.getDeclaredField("name");
		
		assertEquals(String.class, field.getType()) ;
		
		field.setAccessible(true) ;
		assertEquals("bleujin", field.get(instance)) ;
		
		field.set(instance, "hero") ;
		assertEquals("hero", instance.name()) ;
	}
	
}
