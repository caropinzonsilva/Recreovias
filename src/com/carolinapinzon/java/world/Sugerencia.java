package com.carolinapinzon.java.world;

public class Sugerencia implements Comparable<Sugerencia>{
	
	public double sugerencia;
	public String nombre;
	
	public Sugerencia(String nombreP, double sugerenciaP) {
		nombre = nombreP;
		sugerencia = sugerenciaP;
	}
	
	
	public int compareTo(Sugerencia o) {
		// TODO Auto-generated method stub
		return (int) (o.sugerencia*1000 - this.sugerencia*1000);
	}

	public String toString() {
		return "Nombre: " + this.nombre + " | Contador: " + this.sugerencia;
	}
}
