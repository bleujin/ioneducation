package net.ion.bleujin.reflection;

import java.io.IOException;

import javax.script.ScriptException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.ion.bleujin.script.IdString;
import net.ion.bleujin.script.ResultHandler;
import net.ion.bleujin.script.javascript.InstantJavaScript;
import net.ion.bleujin.script.javascript.JScriptEngine;
import net.ion.framework.parse.gson.JsonObject;
import net.ion.framework.util.Debug;

public class TestScript {

	private InstantJavaScript s;

	@BeforeEach
	protected void setUp() throws Exception {
		JScriptEngine jengine = JScriptEngine.create();
		this.s = jengine.createScript(IdString.create("hello"), "sample", getClass().getResourceAsStream("sample.script"));

	}

	@Test
	public void testRunScript() throws IOException, ScriptException {

		s.call(new ResultHandler<Void>() {
			@Override
			public Void onSuccess(Object result, Object... args) {
				Debug.line(result, result.getClass());
				return null;
			}
			@Override
			public Void onFail(Exception ex, Object... args) {
				Debug.line(ex);
				return null;
			}
		}, "handle", "bleujin");
	}

	@Test
	public void testShortExpr() throws Exception {
		Object result = s.exec("handle", "bleujin");
		Debug.line(result, result.getClass());
	}
	
	
	@Test
	public void test0JsonNull() throws Exception {
		JsonObject json = JsonObject.fromString("") ;
	}
	

}
