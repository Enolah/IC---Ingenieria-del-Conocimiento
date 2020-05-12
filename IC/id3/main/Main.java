package main;

import java.io.IOException;

import javax.swing.JOptionPane;

import Decision.Nodo;
import GUI.VistaPrincipal;
import ID3.ID3;

public class Main {

	public static void main(String[] args) {

	
		ID3 id3 = new  ID3();
		Nodo arbol;
		
		try {
			//Introducimos los archivos .txt que queremos leer
			id3.leerListaAtributos(args[0].toString());
			arbol= id3.leerOpciones(args[1].toString());
			
			
			VistaPrincipal vista = VistaPrincipal.getInstance();
			
		
			vista.setArbol(arbol);
			vista.setAtributos(id3.getAtributos());
			
			vista.initView();
			System.out.println(id3.toString());
		
		
		}catch (IOException e) {

			JOptionPane.showMessageDialog(null, "Archivos  no encontrados", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}