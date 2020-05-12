package ID3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Decision.Atributo;
import Decision.Ejemplos;
import Decision.Nodo;

public class ID3 {

	private ArrayList<Atributo> listaAtributos;
	private ArrayList<ArrayList<String[]>> entradas;
	private Ejemplos ejemplos;
	private String limite;

	public ID3() {

		limite = "";

		ejemplos = new Ejemplos();
		listaAtributos = new ArrayList<Atributo>();
		entradas = new ArrayList<ArrayList<String[]>>();

		entradas.add(new ArrayList<String[]>());
		entradas.add(new ArrayList<String[]>());

	}

	public void leerListaAtributos(String archivo) throws IOException {

		String currentDir = new File("").getAbsolutePath();

		File newFile = new File(currentDir, archivo);

		String cadena;
		FileReader lector = new FileReader(newFile);
		BufferedReader buffer = new BufferedReader(lector);
		
			// LEER EL ARCHIVO HASTA EL FINAL DE ARCHIVO
			while ((cadena = buffer.readLine()) != null) {
				// PARSEAR CADA LINEA
				String op[] = cadena.split(",");
				for (String decision : op) {

					Atributo nuevoAtributo = new Atributo(decision);
					listaAtributos.add(nuevoAtributo);
				}

			}
		
		buffer.close();

	}

	public Nodo leerOpciones(String archivo) throws IOException {

		String currentDir = new File("").getAbsolutePath();

		File newFile = new File(currentDir, archivo);

		String cadena;
		FileReader lector = new FileReader(newFile);
		BufferedReader buffer = new BufferedReader(lector);
		
			// LEER EL ARCHIVO HASTA EL FINAL DE ARCHIVO
			while ((cadena = buffer.readLine()) != null) {
				// PARSEAR CADA LINEA
				String op[] = cadena.split(",");
				ejemplos.addEjemplo(op);
				int i = 0;
				if (limite == "") {
					limite = op[op.length - 1];
				}
				for (String decision : op) {
					if (op[op.length - 1].compareTo(limite) == 0)
						listaAtributos.get(i).addElement(decision, false);
					else
						listaAtributos.get(i).addElement(decision, true);

					i++;
				}

			}
		
		buffer.close();

		for (Atributo atr : listaAtributos) {
			if (atr != listaAtributos.get(listaAtributos.size() - 1)) {
				atr.actualiza();
			}

		}

		listaAtributos.remove(listaAtributos.size() - 1);

		Nodo mejor = actualizaLista(listaAtributos, ejemplos);
		mejor.setEjemplos(ejemplos);
		recursividadTotal(mejor, listaAtributos);

		return mejor;
	}

	private Nodo actualizaLista(ArrayList<Atributo> lista, Ejemplos listaDeEjemplo) {
		Atributo atrMejor = null;
		double meritoMejor = Double.MAX_VALUE;

		int i = 0;

		for (Atributo atr : lista) {
			// actualizar el atr, primero elimina los datos del nodo anterior
			atr.borra();
			// ACTUALIZA CON LOS NUEVOS
			for (ArrayList<String> ejem : listaDeEjemplo.getEjemplo()) {

				if (ejem.get(ejem.size() - 1).compareTo(limite) == 0) {
					atr.addElement(ejem.get(i), false);

				} else {
					atr.addElement(ejem.get(i), true);

				}

			}
			atr.actualiza();
			double aux = atr.getMerito();
		//	System.out.println(atr.getName() + atr.getMerito());
			if (aux < meritoMejor) {
				meritoMejor = aux;
				atrMejor = atr;
			}
			++i;
		}
		//System.out.println(atrMejor.getName() + " " + atrMejor.getMerito());
		Nodo mejor = new Nodo(new ArrayList<Nodo>(), atrMejor.getPositivos(), atrMejor.getNegativos(), 0,
				atrMejor.getMerito(), atrMejor.getName());

		for (String atr : atrMejor.getClaves()) {
			Nodo aux = new Nodo(new ArrayList<Nodo>(), 0, 0, atrMejor.getNum(atr), atrMejor.getMerito(atr), atr);

			Ejemplos listaEjemplos = new Ejemplos();

			for (ArrayList<String> ejem : listaDeEjemplo.getEjemplo()) {
				if (ejem.contains(atr)) {
					listaEjemplos.add(ejem);

					if (ejem.get(ejem.size() - 1).compareTo(limite) == 0) {
						aux.addNegativo();
					} else
						aux.addPositivo();
				}

			}
			aux.setNum(aux.getNegativos() + aux.getPositivos());
			aux.setEjemplos(listaEjemplos);

			mejor.addHijo(aux);
		}

		return mejor;
	}

	private void recursividadTotal(Nodo mejor, ArrayList<Atributo> atributos) {
		ArrayList<Atributo> aux = new ArrayList<Atributo>();

		if (atributos.size() == 1) {
			for (Nodo hijo : mejor.getHijos()) {
				if (hijo.getPositivos() > 0 && hijo.getNegativos() == 0) {
					hijo.addHijo(new Nodo("SI"));
				} else if (hijo.getNegativos() > 0 && hijo.getPositivos() == 0) {
					hijo.addHijo(new Nodo("NO"));
				}
			}
		} else {
			// Quitamos el mejor anterior
			int nAtr = -1;
			for (int i = 0; i < atributos.size(); i++) {
				if (!atributos.get(i).getName().equals(mejor.getNombre())) {
					ArrayList<Atributo> listaAux = (ArrayList<Atributo>) atributos.clone();
					aux.add(listaAux.get(i));
				} else {
					nAtr = i;
				}
			}

			for (Nodo hijo : mejor.getHijos()) {
				// ELIMINAR LOS EJEMPLOS DE

				if (hijo.getPositivos() > 0 && hijo.getNegativos() == 0) {
					hijo.addHijo(new Nodo("SI"));
				} else if (hijo.getNegativos() > 0 && hijo.getPositivos() == 0) {
					hijo.addHijo(new Nodo("NO"));
				} else {
					hijo.eliminaColumna(nAtr);
					Nodo nuevoMejor = actualizaLista(aux, hijo.getEjemplos());
					hijo.addHijo(nuevoMejor);
					recursividadTotal(nuevoMejor, aux);
				}

			}
		}
	}

	public ArrayList<Atributo> getAtributos() {
		return listaAtributos;
	}

	public String toString() {

		String cadena = "";

		for (Atributo atributo : listaAtributos) {
			cadena += ' ' + atributo.getName();
			cadena += ' ' + atributo.getP();
			cadena += ("\n");
		}

		return cadena;
	}

}
