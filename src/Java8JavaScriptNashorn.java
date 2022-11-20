import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Java8JavaScriptNashorn 
{

	// executa arquivo javascript a partir do java
	// tem que estar no java 1.8 pra rodar este código
	
	public static void main(String[] args) 
	{
		
		ScriptEngine ee   = new ScriptEngineManager().getEngineByName("Nashorn");
		Bindings     bind = ee.getBindings(ScriptContext.ENGINE_SCOPE);
		
		bind.put("goodbye", "Até Logo");
		bind.put("autor", "Desenvolvido por ARS - 20/11/2022");
		
		try 
		{
			ee.eval(new FileReader("C:\\cursos\\curso-java-new-features\\src\\OlaMundo.js"));
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ScriptException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
