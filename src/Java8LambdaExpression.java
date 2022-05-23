import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

// interface funcional
interface Pessoa 
{
	void falar();
}

interface Venda
{
	void vender(Produto p, boolean exibeTotal);
}

class Produto 
{
	
	private Integer codigo;
	private String 	nome;
	private Integer qtde;
	private Double 	preco;
	
	public Produto() {
		
	}

	public Produto(Integer codigo, String nome, Integer qtde, Double preco) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.qtde = qtde;
		this.preco = preco;
	}

	public Double getTotal()
	{
		return this.preco * this.qtde;
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

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", qtde=" + qtde + ", preco=" + preco + "]";
	}

	
}


public class Java8LambdaExpression 
{
	
	public static void main(String[] args) 
	{
		
		exemplo01();
		
		exemplo02();
		
		exemplo03();
		
		exemplo04();
				
	}
	
	private static void exemplo01()
	{
		
		System.out.println("Exemplo 01 ------------------------------------------------------");
		
		Thread t1 = new Thread(new Runnable() 
		{			
			@Override
			public void run() 
			{
				System.out.println("Thread 1 - sem lambda...");
			}
		});
		
		Runnable run2 = new Runnable() 
		{
			
			@Override
			public void run() 
			{
				System.out.println("Thread 2 - sem lambda...");				
			}
		};
		
		Thread t2 = new Thread(run2);
		
		Thread t3 = new Thread( () -> System.out.println("Thread 3 - com lambda..."));				
		
		t1.start();
		
		t2.start();
		
		t3.start();
		
	}
	
	private static void exemplo02()
	{
		
		System.out.println("Exemplo 02 ------------------------------------------------------");
		
		Pessoa p1 = new Pessoa() 
		{			
			@Override
			public void falar() 
			{
				System.out.println("Pessoa falando - sem lambda");				
			}
		};		
		p1.falar();	
		
		Pessoa p2 = () -> System.out.println("Pessoa falando - com lambda");	
		p2.falar();
		
	}
	
	private static void exemplo03()
	{
		
		System.out.println("Exemplo 03 ------------------------------------------------------");
		
		Venda ve1 = new Venda() 
		{			
			@Override
			public void vender(Produto p, boolean exibeTotal) 
			{
				System.out.println("Vendendo o produto - sem lambda:\n" + p);	
				if (exibeTotal)
				{
					System.out.println("\nTotal: " + p.getTotal());
				}
			}
		};
		
		var p1 = new Produto(1, "teste A", 60, 500.0);
		var p2 = new Produto(1, "teste B", 30, 650.0);
		ve1.vender(p1, true);
		ve1.vender(p2, true);
		
		Venda ve2 = (p, exibeTotal) -> 
		{
			System.out.println("Vendendo o produto - com lambda:\n" + p1);	
			if (exibeTotal)
			{
				System.out.println("\nTotal: " + p.getTotal());
			}
		};		
		ve2.vender(p1, true);
				
	}
	
	private static void exemplo04()
	{
		
		System.out.println("Exemplo 04 ------------------------------------------------------");
			
		List<Produto> carrinho = new ArrayList<>();
		
		carrinho.add(new Produto(1, "teste 5", 5, 50.0));
		carrinho.add(new Produto(2, "teste 4", 3, 90.0));
		carrinho.add(new Produto(3, "teste 3", 4, 10.0));
		carrinho.add(new Produto(4, "teste 2", 1, 200.0));
		carrinho.add(new Produto(5, "teste 1", 8, 2.5));
		
		System.out.println("ForEach 1 com lambda");
		carrinho.forEach( p -> 
		{
			System.out.println("\nCódigo: " 	+ p.getCodigo());
			System.out.println("\nNome: " 		+ p.getNome());
			System.out.println("\nQuantidade: " + p.getQtde());
			System.out.println("\nPreço: " 		+ p.getNome());
			System.out.println("\nTotal: " 		+ p.getTotal());
		});
		
		System.out.println("ForEach 2 com lambda");
		carrinho.forEach( p -> System.out.println(p));
		
		System.out.println("ForEach 3 sem lambda");
		Consumer<Produto> c = new Consumer<>() 
		{
			@Override
			public void accept(Produto t) 
			{
				System.out.println(t);
			}
		};
		carrinho.forEach(c);
		
		System.out.println("Sort sem lambda - código - decrescente");
		Comparator<Produto> ordemCodigo = new Comparator<>() 
		{
			@Override
			public int compare(Produto o1, Produto o2) 
			{
				return o2.getCodigo().compareTo(o1.getCodigo());
			}
		};
		carrinho.sort(ordemCodigo);
		carrinho.forEach( p -> System.out.println(p));		
		
		System.out.println("Sort com lambda - preço - crescente");
		carrinho.sort( (p1, p2) -> p1.getPreco().compareTo(p2.getPreco()));
		carrinho.forEach( p -> System.out.println(p));		
	
	}	
	
}
