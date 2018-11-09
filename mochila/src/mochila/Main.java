package mochila;

public class Main {

	public static void main(String[] args) {
		Calculo calculo = new Calculo();
		calculo.LerItens();
		Mochila m1 = calculo.Guloso();
		
		calculo.printMochila(m1);
		
		calculo.Dinamico(m1);
		
		calculo.imprimirMatriz();
			
	}

}
