package net.ion.bleujin.script;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import javax.script.ScriptException;

import net.ion.bleujin.script.groovy.GScriptEngine;
import net.ion.bleujin.script.javascript.JScriptEngine;

public abstract class Scripter {
	
	public abstract <T extends InstantScript> T createScript(IdString lid, String explain, InputStream input) throws IOException, ScriptException ;
	public abstract <T extends InstantScript> T createScript(IdString lid, String explain, Reader reader) throws IOException, ScriptException ;
	
	public static JScriptEngine javascript() {
		return JScriptEngine.create();
	}
	public static GScriptEngine groovy() {
		return GScriptEngine.create() ;
	}
}
