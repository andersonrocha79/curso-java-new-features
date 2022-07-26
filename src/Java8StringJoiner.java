import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Java8StringJoiner {

	public static void main(String[] args) 
	{
		
		
		// string tokeninzer **************************************
		
		String nomes = " João, Pedro, Maria, Ana, Paulo";
		
		StringTokenizer st = new StringTokenizer(nomes, ",");

		while(st.hasMoreTokens())
		{
			System.out.println(st.nextToken().trim());
		}
		
		
		// string joiner ******************************************
		
		StringJoiner sj = new StringJoiner(", ");
		
		sj.add("João");
		sj.add("Pedro");
		sj.add("Maria");
		sj.add("Ana");
		sj.add("Paulo");
		
		System.out.println(sj);

	}

}
