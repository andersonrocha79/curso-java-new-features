import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


class ProdutoTeste2
{
	
	private Integer	codigo;
	private String 	nome;
	
	public ProdutoTeste2()
	{
		
	}
	
	public ProdutoTeste2(Integer codigo, String nome)
	{
		this.codigo = codigo;
		this.nome = nome;
	}
	
	@Override
	public String toString()
	{
		return nome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}

class OrdenaProdutoPorPreco implements Comparator<Produto> 
{ 
    public int compare(Produto p1, Produto p2) 
    { 
        return p1.getPreco().compareTo(p2.getPreco()); 
    } 
} 

public class Java8Stream 
{

	public static void main(String[] args) 
	{
		
		List<Produto> listaProdutos = new ArrayList<Produto>(); 
		
		listaProdutos.add(new Produto(1000, "Geladeira 470L", 50, 2999.00));
		listaProdutos.add(new Produto(2000, "TV UHD 50''", 30, 3500.00));
		listaProdutos.add(new Produto(1001, "TV UHD 65''", 40, 5000.00));
		listaProdutos.add(new Produto(3000, "Microondas 20L", 6, 399.00));
		listaProdutos.add(new Produto(1001, "Geladeira 120L", 7, 900.00));
		listaProdutos.add(new Produto(4000, "Computador i5 2.9Ghz 4GB 1TB HD", 12, 2429.00));
		listaProdutos.add(new Produto(1002, "Geladeira 500L", 5, 3100.00));
		
		System.out.println("Usando a forma 'antiga'...");
		
		// Cria lista só de geladeiras
		List<Produto> listaGeladeiras = new ArrayList<Produto>();
		for (Produto p : listaProdutos) 
		{
			if (p.getNome().contains("Geladeira")) 
			{
				listaGeladeiras.add(p);
			}
		}
		
		// Ordena a lista de geladeiras por preço crescente
		Collections.sort(listaGeladeiras, new OrdenaProdutoPorPreco());
		
		// Cria a lista só de nomes em letras maiúsculas das geladeiras
		List<String> listaNomesGeladeiras = new ArrayList<String>();
		for(Produto p : listaGeladeiras) 
		{
			listaNomesGeladeiras.add(p.getNome().toUpperCase());
		}
		
		for(String nome : listaNomesGeladeiras) 
		{
			System.out.println(nome);
		}
		
		// **************************************************************************************************
		
		System.out.println("\nUsando Java Streams...");
		
		// Obtém um fluxo de dados (stream)
		listaProdutos.stream()
		
		// Interface Predicate - filtrar os nomes dos produtos que contém 'Geladeira'
		.filter(p -> p.getNome().contains("Geladeira"))
		
		// Interface Comparator - ordenar o preço em ordem crescente de acordo com a implementação da classe OrdenaProdutoPorPreco
		.sorted((p1, p2) -> p1.getPreco().compareTo(p2.getPreco()))
		
		// Interface Function - aplicar uma dada função (toUpperCase) aos elementos dessa stream
		.map(p -> p.getNome().toUpperCase())
		
		// Interface Consumer - percorrer cada elemento da stream e exibi-los no console utilizando method reference
		.forEach(System.out::println);
		
		System.out.println("\nUsando Java Streams... Ordenação diferente 1");
		
		listaProdutos.stream()
		.filter(p -> (p.getPreco() >= 300 && p.getPreco() <= 1000)) // produtos com preço entre 300 e 1000
		.sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))   // ordena pelo nome do produto
		.map(p -> p.getCodigo() + " - " + p.getNome() + " - preço: " + p.getPreco())    	// gera uma lista de strings  
		.forEach(System.out::println);   							// lista o resultado gerando com "toString"
				
		System.out.println("\nUsando Java Streams... Ordenação diferente 2");
		
		listaProdutos.stream()
		.filter(p -> (p.getPreco() >= 3000 && p.getPreco() <= 5000)) 	// produtos com preço entre 3000 e 5000
		.sorted((p1, p2) -> p1.getPreco().compareTo(p2.getPreco()))   	// ordena pelo valor
		.map(p -> new ProdutoTeste2(p.getCodigo(), p.getNome() + " - preço: " + p.getPreco()))    // gera uma lista de objetos de outro tipo  
		.forEach(p -> System.out.println(p.getCodigo() + " - " + p.getNome()));		
	} 

}
