package net.ion.bleujin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

import net.ion.framework.util.IOUtil;

public class CustomClassLoader extends ClassLoader {

	String repoLocation = "./bin/";

	public CustomClassLoader() {
	}

	public CustomClassLoader(ClassLoader parent) {
		super(parent);
	}
	

	@Override
	protected Class<?> findClass(final String name) throws ClassNotFoundException {

		AccessControlContext acc = AccessController.getContext();
		try {
			return (Class) AccessController.doPrivileged(new PrivilegedExceptionAction() {
				public Object run() throws ClassNotFoundException {

					try {

						String path = name.replace('.', '/');
						FileInputStream fi = new FileInputStream(repoLocation + path + ".class");
						byte[] classBytes = IOUtil.toByteArray(fi) ;
						IOUtil.close(fi) ;
						return defineClass(name, classBytes, 0, classBytes.length);
					} catch (Exception e) {
						throw new ClassNotFoundException(name);
					}
				}
			}, acc);
		} catch (java.security.PrivilegedActionException pae) {
			return super.findClass(name);
		}
	}
}


