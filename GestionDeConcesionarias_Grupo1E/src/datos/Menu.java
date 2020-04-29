package datos;

import java.util.*;

//clase que define el menu 
public class Menu {
	private ArrayList<Plato> primerosDisponibles;
	private ArrayList<Plato> segundosDisponibles;
	private ArrayList<Plato> postresDisponibles;
	private ArrayList<Bebida> bebidas;
	private String diaSemana;

	public Menu() {
		super();

	}

	public Menu(ArrayList<Plato> primerosDisponibles, ArrayList<Plato> segundosDisponibles,
			ArrayList<Plato> postresDisponibles, ArrayList<Bebida> bebidas, String diaSemana) {
		super();
		this.primerosDisponibles = primerosDisponibles;
		this.segundosDisponibles = segundosDisponibles;
		this.postresDisponibles = postresDisponibles;
		this.bebidas = bebidas;
		this.diaSemana = diaSemana;
	}

	public ArrayList<Plato> getPrimerosDisponibles() {
		return primerosDisponibles;
	}

	public void setPrimerosDisponibles(ArrayList<Plato> primerosDisponibles) {
		this.primerosDisponibles = primerosDisponibles;
	}

	public ArrayList<Plato> getSegundosDisponibles() {
		return segundosDisponibles;
	}

	public void setSegundosDisponibles(ArrayList<Plato> segundossDisponibles) {
		this.segundosDisponibles = segundossDisponibles;
	}

	public ArrayList<Plato> getPostresDisponibles() {
		return postresDisponibles;
	}

	public void setPostresDisponibles(ArrayList<Plato> postresDisponibles) {
		this.postresDisponibles = postresDisponibles;
	}

	public ArrayList<Bebida> getBebidas() {
		return bebidas;
	}

	public void setBebidas(ArrayList<Bebida> bebidas) {
		this.bebidas = bebidas;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	@Override
	public String toString() {
		String resultado = new String();
		int contadorBebidas = 0;
		
		resultado += "********* Primeros Platos **********\n" +
				"\t 1) " + this.primerosDisponibles.get(0).getNombre() + "\n" +
				"\t 2) " + this.primerosDisponibles.get(1).getNombre() + "\n" +
				"\t 3) " + this.primerosDisponibles.get(2).getNombre() + "\n" +
				"************** Segundo Platos *********** \n" +
				"\t 1) " + this.segundosDisponibles.get(0).getNombre() + "\n" +
				"\t 2) " + this.segundosDisponibles.get(1).getNombre() + "\n" +
				"\t 3) " + this.segundosDisponibles.get(2).getNombre() + "\n" +
				"************** Postres Platos ********************* \n" +
				"\t 1) " + this.postresDisponibles.get(0).getNombre() + "\n" +
				"\t 2) " + this.postresDisponibles.get(1).getNombre() + "\n" +
				"\t 3) " + this.postresDisponibles.get(2).getNombre() + "\n" +
				"*************** Bebidas ************************** \n";
		for(Bebida aux: this.bebidas) {
			contadorBebidas++;
			
			resultado += "\t "+ contadorBebidas + ") " + aux.getNombre() + "\n";
		}
		
		return resultado;
	}

}
