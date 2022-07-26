import java.util.Base64;

public class Java8Base64 
{

	public static void main(String[] args) 
	{
		
		try
		{
			
			final String textoOriginal = "A classe Base64 no Java 8!";
			System.out.println( "Mensagem original: " + textoOriginal );
			
	        String textoCodificado = Base64.getEncoder().encodeToString( textoOriginal.getBytes("utf-8") );
	        System.out.println( "Mensagem codificada: " + textoCodificado );
	        
	        String textoDecodificado = new String( Base64.getDecoder().decode( textoCodificado ), "utf-8");
	        System.out.println( "Mensagem decodificada: " + textoDecodificado );
	        
	        String nomeCodificado = Base64.getEncoder().encodeToString("Anderson Rocha de Souza".getBytes());
	        System.out.println("Nome codificado: " + nomeCodificado);
	        
		}
		catch(Exception e)
		{
				
		}

	}

}
