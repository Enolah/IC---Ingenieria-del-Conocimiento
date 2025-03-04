package main;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Decision.Nodo;
import GUI.VistaPrincipal;
import ID3.ID3;

public class Main extends JFrame {

	public static void main(String[] args)  {

		JPanel mainPanel = new JPanel();
		
		ID3 id3 = new  ID3();
		Nodo arbol;
		
		try {
			//Introducimos los archivos .txt que queremos leer
			JFileChooser fc = new JFileChooser();
			int respuesta = fc.showOpenDialog(mainPanel);
			// leemos atributosJuego.txt
			if (respuesta == JFileChooser.APPROVE_OPTION) {
				File atributos_juego = fc.getSelectedFile();
				System.out.println(atributos_juego.getName());
				id3.leerListaAtributos(atributos_juego.getName());
				
				// leemos juego.txt
				JFileChooser fc1 = new JFileChooser();
				int respuesta1 = fc1.showOpenDialog(mainPanel);
				if (respuesta1 == JFileChooser.APPROVE_OPTION) {
					File juego = fc1.getSelectedFile();
					System.out.println(juego.getName());
					arbol= id3.leerOpciones(juego.getName());
					
					
					//programa principal
					VistaPrincipal vista = VistaPrincipal.getInstance();
					
					
					vista.setArbol(arbol);
					vista.setAtributos(id3.getAtributos());
					
					vista.initView();
					System.out.println(id3.toString());
				}
				
			}
			
			
			
		
		
		}catch (IOException e) {

			JOptionPane.showMessageDialog(null, "Archivos  no encontrados", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}