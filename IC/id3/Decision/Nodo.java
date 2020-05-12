package Decision;

import java.util.ArrayList;

public class Nodo {

	
	private ArrayList<Nodo> hijos;
	private double positivos;
	private double negativos;
	private double num;
	private double entropia;
	private String nombre;
	private Ejemplos ejemplos;
	
	
	
	public Nodo() {
		hijos = new ArrayList<Nodo>();
		
	}
	

	
	public Nodo(ArrayList<Nodo> hijos, double positivos, double negativos, double num, double entropia, String nombre) {
		this.hijos = hijos;
		this.positivos = positivos;
		this.negativos = negativos;
		this.num = num;
		this.entropia = entropia;
		this.nombre = nombre;
	}


	public Nodo(String nombre) {
		this.hijos = new ArrayList<Nodo>();
		this.positivos = 0;
		this.negativos = 0;
		this.num = 0;
		this.entropia = 0;
		this.nombre = nombre;
	}


	
	public void addHijo(Nodo miNodo) {
		
		hijos.add(miNodo);
	}
	
	public ArrayList<Nodo> getHijos() {
		return hijos;
	}
	public void setHijos(ArrayList<Nodo> hijos) {
		this.hijos = hijos;
	}
	public double getPositivos() {
		return positivos;
	}
	public void setPositivos(double positivos) {
		this.positivos = positivos;
	}
	public double getNegativos() {
		return negativos;
	}
	public void setNegativos(double negativos) {
		this.negativos = negativos;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public double getEntropia() {
		return entropia;
	}
	public void setEntropia(double entropia) {
		this.entropia = entropia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void eliminaColumna(int columna){
		
		ejemplos.eliminarEjemplo(columna);
		
	}
	

	public Ejemplos getEjemplos() {
		return ejemplos;
	}



	public void setEjemplos(Ejemplos listaEjemplos) {
		this.ejemplos = listaEjemplos;
	}


	public void addPositivo() {
		positivos++;
	}
	public void addNegativo() {
		negativos++;
	}
	
	
	
	
	
	
}
