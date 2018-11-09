package mochila;

import java.util.ArrayList;

public class Mochila {
	private  ArrayList<Item> Itens = new ArrayList<Item>();
	  private float size;
	  
	  public Mochila(float size) {
		  this.size = size;
	  }
	  
	  public ArrayList<Item> Listar(){
		  return this.Itens;
	  }
	  
	  public Boolean dentro(Item i){
		  if(this.coube(i)) {
			  this.Itens.add(i);
			  return true;
		  }
		  return false;
	  }
	  
	  public Boolean fora(Item i){
		  int id = 0;
		  for (Item Item: Itens) {
			    if(i.getNome() == Item.getNome()){
			    	this.Itens.remove(id);
			    	return true;
			    }
			    id++;
		  }
		  return false;
	  }
	  
	  public Boolean juntar(Mochila m){
		  if((this.size - this.pesar()) >= m.pesar()) {
			  for (Item Item: m.Listar()) {
				    this.dentro(Item);
			  }
			  return true;
		  }
		  return false;
	  }
	  
	  private Boolean coube(Item i){
		  float tPeso = i.getPeso() + this.pesar();
		  return tPeso <= this.size;
	  }
	  
	  public float pesar() {
		  float tPeso = 0;
		  for (Item Item: this.Itens) {
			    tPeso = tPeso + Item.getPeso();
		  }
		  return tPeso;
	  }
	  
	  public float valor() {
		  float tValor = 0;
		  for (Item Item: this.Itens) {
			    tValor = tValor + Item.getValor();
		  }
		  return tValor;
	  }
	  
	  public float getSize() {
		  return this.size;
	  }
}

