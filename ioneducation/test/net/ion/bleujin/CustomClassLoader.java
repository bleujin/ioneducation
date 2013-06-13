package net.ion.bleujin;

import java.io.FileInputStream;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

public class CustomClassLoader extends ClassLoader {

	String repoLocation = "./libsrc/";

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

					FileInputStream fi = null;
					try {

						String path = name.replace('.', '/');
						fi = new FileInputStream(repoLocation + path + ".class");
						byte[] classBytes = new byte[fi.available()];
						fi.read(classBytes);
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