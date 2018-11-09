package mochila;

public class Item {
	private float valor;
	private float peso;
	private String nome;
	public Boolean adicionado = false;
	
	public Item( String nome,float valor, float peso) {
		this.nome = nome;
		this.valor = valor;
		this.peso = peso;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}

}

