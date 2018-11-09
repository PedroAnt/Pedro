package mochila;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Calculo {
	protected ArrayList<Item> todosItens = new ArrayList<Item>();
	private Mochila[][] matriz = null;
	
	public void LerItens() {
		System.out.println("item da lista | Valor do item | Peso do item");
		File file = new File("dados.txt"); //pega os dados do item no arquivo, se for alterar os pesos ou o valor tem que ser naquele arquivo
		BufferedReader buffer = null;
		try {
			buffer = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = null;
		try {
			while ((line = buffer.readLine()) != null) {
				System.out.println(line);
				String[] splited = line.split("\\s+");
				String nome = splited[0];
				int valor = Integer.parseInt(splited[1]);
				int peso = Integer.parseInt(splited[2]);
				todosItens.add( new Item(nome, valor, peso) );
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}
	
	public Mochila Guloso(){
		System.out.println("------------------------------");
		Scanner entrada = new Scanner(System.in);
		System.out.print("Tamanho da mochila:");
		float size = entrada.nextFloat();
		Mochila mochila = new Mochila(size);
		System.out.println("\nNo Guloso cabe os item ou o item de maior peso:");
		for (Item Item: this.todosItens) {
			Item maxItem = this.maxItem();
			mochila.dentro(maxItem);
			maxItem.adicionado = true;

		}
		
		return mochila;
	}
	
	public Item maxItem() {
		Item maxItem = null;
		for (Item Item: this.todosItens) {
			if(!Item.adicionado) {
				if( maxItem == null || (Item.getValor() >= maxItem.getValor()) && !Item.adicionado ){
					maxItem = Item;
				}
			}
			
		}
		return maxItem;
	
	}
	public void Dinamico(Mochila m1){
		System.out.println("------------------------------");
		System.out.println("\nDinamico");
		int s = (int) m1.getSize();
		int i = this.gettdItens().size();
		this.matriz = new  Mochila[i][s+1];
		int i1 = 0, i2 =0;
		for(Item item: this.gettdItens()) {
			while(s >= i2) {

				matriz[i1][i2]= DinamicoAlg(item, i2, matriz, i1);

				i2++;
			}
			i2 = 0;
			i1++;
		}
	}
	
	public Mochila DinamicoAlg(Item i,float s, Mochila[][] m,int n) {
		Mochila novaMochila = new Mochila(s);
		if(s <= 0 ) 
			return novaMochila;		
		
		if (!novaMochila.dentro(i) && n != 0)
			return m[n-1][(int) s];
		
		if(n==0)
			return novaMochila;
					
		int resto = (int) (novaMochila.getSize() - novaMochila.pesar());
		novaMochila.juntar(m[n-1][resto]);
	
		Mochila velhaMochila = m[n-1][(int) s];
		
		if((novaMochila.valor() > velhaMochila.valor()) ) {
			return novaMochila;
		}
		
		return velhaMochila;
	}
	
	public void DinamicoResultado() {
		Scanner entrada = new Scanner(System.in);
		int i = this.gettdItens().size();
		int s = entrada.nextInt();
		this.printMochila(this.matriz[i-1][s]);
	}
	
	public void printMochila(Mochila mochila) {
		for (Item Item: mochila.Listar()) {
			System.out.print(Item.getNome() + ' ');
		}
		System.out.println();
	}
	
	public float getTotalValorMochila(Mochila mochila) {
		float total = 0;
		for (Item Item: mochila.Listar()) {
			total = Item.getValor() + total;
		}
		return total;
	}
	
	public float getTotalPesoMochila(Mochila mochila) {
		float peso = 0;
		for (Item Item: mochila.Listar()) {
			peso = Item.getPeso() + peso;
		}
		return peso;
	}
	
	public  void imprimirMatriz() {
		if(this.matriz == null)
			return ;
		
		for(Mochila[] linha: this.matriz) {
			for(Mochila mochila: linha) {
				System.out.print(" - " + mochila.valor());
			}
			System.out.println();
		}
	}
	
	public ArrayList<Item> gettdItens(){
		return this.todosItens;
	}
}
