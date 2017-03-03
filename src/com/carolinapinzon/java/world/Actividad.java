package com.carolinapinzon.java.world;

public class Actividad implements Comparable<Actividad> {
	public String nombre;
	public double distancia;
	public double multiplicador;
	public String shortt;
	public String dia;
	public String horario;
	public boolean used;

	public Actividad(String nombreP, double distanciaP, double multiplicadorP, String shorttP, String diaP, String horarioP) {
		nombre = nombreP;
		distancia = distanciaP;
		multiplicador = multiplicadorP;
		shortt = shorttP;
		dia = diaP;
		horario = horarioP;
		used = false;
	}
	
	public void useActivity() {
		used = true;
	}

	public String toString() {
		return "Nombre: " + this.nombre + " | Multi: " + this.multi() + " | short: " + this.shortt + " | dia: " + this.dia + " | horario: " + this.horario;
	}

	public double multi() {
		return this.distancia*this.multiplicador;
	}

	public int compareTo(Actividad o) {
		// TODO Auto-generated method stub
		return (int) Math.floor((this.distancia * this.multiplicador) - (o.distancia * o.multiplicador));
	}
}
