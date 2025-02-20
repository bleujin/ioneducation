package net.ion.bleujin;

import java.io.FileInputStream;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

import com.oracle.truffle.regex.tregex.string.StringUTF16;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import net.ion.framework.util.IOUtil;
import net.ion.framework.util.StringUtil;

public class ProxyClassLoader extends ClassLoader{

	
	private String methodName ;
	private String beforeLogic ;
	private ClassPool cp = ClassPool.getDefault();
	
	public ProxyClassLoader(ClassLoader parent) {
		super(null);
	}
	

	@Override
	protected Class<?> findClass(final String name) throws ClassNotFoundException {

		try {
			CtClass cc = cp.get(name);
			if (StringUtil.isNotBlank(methodName) && StringUtil.isNotBlank(beforeLogic)) {
				CtMethod m = cc.getDeclaredMethod(methodName);
				m.insertBefore(beforeLogic);
			}

			return cc.toClass() ;
		} catch (NotFoundException | CannotCompileException pae) {
			return super.findClass(name);
		}
	}
	
	
	public void before(String methodName, String logic) {
		this.methodName = methodName ;
		this.beforeLogic = logic ;
	}
}
