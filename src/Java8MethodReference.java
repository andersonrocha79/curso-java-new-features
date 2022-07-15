import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@FunctionalInterface // indica que é uma interface funcional e não pode ter mais de um método abstrato
interface Figura2D
{
	void desenha(Double largura, Double altura);
}

class Retangulo
{
	public void desenhaRetangulo(Double largura, Double altura)
	{
		System.out.println("Desenha retângulo\nLargura: " + largura + "\nAltura: " + altura);
	}
}

interface Desenho2D
{
	Quadrado desenha(Double largura);
}

class Quadrado
{
	public Quadrado(Double largura)
	{
		System.out.println("Desenha quadrado\nLargura: " + largura + "\nAltura: " + largura);
	}
}

class Impressora
{
	public static void imprime(Produto p)
	{
		System.out.println(p.getNome() + " = " + p.getPreco());
	}
}


public class Java8MethodReference 
{

	public static void main(String[] args) 
	{
		
		exemplo01();
		
		exemplo02();
		
		exemplo03();
		
	}
	
	private static void exemplo01()
	{
		
		System.out.println("Exemplo 01 ------------------------------------------------------");
		
		System.out.println("Com Classe Anônima");
		Figura2D fig0 = new Figura2D() 
		{			
			@Override
			public void desenha(Double largura, Double altura) 
			{				
				System.out.println("Desenha figura\nLargura: " + largura + "\nAltura: " + altura);
			}
		};		
		fig0.desenha(15.3, 19.8);
		
		// lambda expression
		System.out.println("Com Lambda");
		Figura2D fig1 = (l, a) -> System.out.println("Desenha figura\nLargura: " + l + "\nAltura: " + a);
		fig1.desenha(8.0, 1.5);
		
		
		System.out.println("Com Method Reference");
		// objeto específico
		Retangulo ret = new Retangulo();
		Figura2D fig2 = ret::desenhaRetangulo;
		fig2.desenha(10.5, 7.0);
		
	}
	
	private static void exemplo02()
	{
		
		System.out.println("Exemplo 02 ------------------------------------------------------");
			
		List<Produto> carrinho = new ArrayList<>();
		
		carrinho.add(new Produto(1, "produto 5", 5, 50.0));
		carrinho.add(new Produto(2, "produto 4", 3, 90.0));
		carrinho.add(new Produto(3, "produto 3", 4, 10.0));
		carrinho.add(new Produto(4, "produto 2", 1, 200.0));
		carrinho.add(new Produto(5, "produto 1", 8, 2.5));
		
		System.out.println("Com Classe Anônima");
		carrinho.forEach(new Consumer<Produto>() 
		{
			@Override
			public void accept(Produto t) 
			{
				System.out.println(t.getNome());				
			}
		});
		
		System.out.println("Com Lambda");
		carrinho.forEach( p -> System.out.println(p.getNome()));		
		
		System.out.println("Com Method Reference - referenciando o metodo estático de outra classe que recebe Produto como Parametro");
		carrinho.forEach(Impressora::imprime);
		
		System.out.println("Com Method Reference - referenciando objeto Produto que está no forEach");
		carrinho.forEach(Produto::imprime);		
	
	}	
	
	private static void exemplo03()
	{
		
		System.out.println("Exemplo 03 ------------------------------------------------------");
		
		System.out.println("Com Classe Anonima");
		Desenho2D desenho0 = new Desenho2D() 
		{
			@Override
			public Quadrado desenha(Double largura) 
			{
				System.out.println("Desenha quadrado\nLargura: " + largura + "\nAltura: " + largura);
				return new Quadrado(largura);
			}
		};
		desenho0.desenha(7.0);
		
		System.out.println("Com Lambda Expression");
		Desenho2D desenho1 = (l) -> new Quadrado(l); System.out.println("desenha quadrado");
		desenho1.desenha(75.9);
		
			
		System.out.println("Com Method Reference - referenciando o construtor da classe");
		Desenho2D desenho2 = Quadrado::new;
		desenho2.desenha(10.2);
	
	}	

}
