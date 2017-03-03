package com.carolinapinzon.java.world;

public class Vecino implements Comparable<Vecino>{
	
	public double similitud;
	public int indice;
	
	public Vecino(int indiceP, double similitudP) {
		indice = indiceP;
		similitud = similitudP;
	}
	
	
	public int compareTo(Vecino o) {
		// TODO Auto-generated method stub
		return (int) (o.similitud*1000 - this.similitud*1000);
	}

	public String toString() {
		return "Indice: " + this.indice + " | Contador: " + this.similitud;
	}
}

