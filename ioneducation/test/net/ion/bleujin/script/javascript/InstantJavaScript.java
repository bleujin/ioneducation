package net.ion.bleujin.script.javascript;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

import net.ion.bleujin.script.InstantScript;
import net.ion.bleujin.script.ResultHandler;

public class InstantJavaScript implements InstantScript {

	private JScriptEngine app;
	private String explain;
	private Object compiledScript ;
	InstantJavaScript(JScriptEngine app, String explain, Object compiledScript) {
		this.app = app ;
		this.explain = explain ;
		this.compiledScript = compiledScript ;
	}
	
	public static InstantJavaScript create(JScriptEngine app, String explain, Object compiledScript) {
		return new InstantJavaScript(app, explain, compiledScript);
	}

	public Object compiled() {
		return compiledScript;
	}
	
	public <T> T exec(ResultHandler<T> rhandler, Object... args) {
		return call(rhandler, "handle", args) ;
	}
	
	public <T> T call(ResultHandler<T> rhandler, String method, Object... args) {
		return app.execHandle(this, rhandler, method, args) ;
	}

	public <T> Future<T> execAsync(ResultHandler<T> rhandler, Object... args) {
		return app.runAsyncHandle(this, rhandler, args);
	}

	public boolean hasMethod(String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return app.hasMethod(this, methodName);
	}

	public Object exec(String methodName, Object... args) throws Exception {
		AtomicReference<Object> ref = new AtomicReference<Object>() ;
		AtomicReference<Exception> eref = new AtomicReference<Exception>() ;
		
		call(new ResultHandler() {
			public Object onSuccess(Object result, Object... args) {
				ref.set(result);
				return null;
			}

			public Object onFail(Exception ex, Object... args) {
				eref.set(ex);
				return null;
			}
			
		}, methodName, args) ;
		
		if (eref.get() != null) throw eref.get() ;
		return ref.get() ;
	}

}
