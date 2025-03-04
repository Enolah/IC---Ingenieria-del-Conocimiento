package vista.GUI;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import Clase.Clase;
import algoritmos.KMeans;
import util.MatrizToVectorVector;

public class KMedidasPanel extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPResultados panelResultados;
	private ArrayList<Clase> clases;
	public static final int OK_OPTION = 0;
	public static final int CANCEL_OPTION = 1;
	private int result = -1;
	
	private JPanel pnlK = new JPanel();
	
	
	public KMedidasPanel(){
		super (new JFrame(), "KMedias",true);
		initGUI();
	}


	private void initGUI() {
		
		//INFORMACION
				pnlK.setPreferredSize(new Dimension(600, 600));
				pnlK.setLayout(new BorderLayout());

				JPanel informacion = new JPanel();
				JLabel titulo = new  JLabel("Informacion:");
				titulo.setFont(new Font("Arial", Font.BOLD, 18));
				informacion.add(titulo);
				informacion.setLayout(new BorderLayout());
				informacion.add(titulo, BorderLayout.NORTH);

				//CENTRO DE LAS CLASES 
				JPanel centros = new JPanel();
				centros.setLayout(new BorderLayout());
				centros.setBorder(new EmptyBorder(10, 10, 10, 10));

				JLabel tituloCentros = new  JLabel("Centros:");
				tituloCentros.setFont(new Font("Arial", Font.BOLD, 18));		
				centros.add(tituloCentros, BorderLayout.NORTH);
				
				
				JPanel c1 = new JPanel();
				c1.setBorder(new EmptyBorder(10, 10, 10, 10));
				c1.setLayout(new GridLayout(2,1));
				
				JLabel x= new JLabel("4.6 3.0 4.0 0.0");
				c1.add(x);
				JLabel y= new JLabel("6.8 3.4 4.6 0.7");
				c1.add(y);
				
				centros.add(c1,BorderLayout.CENTER);
				informacion.add(centros, BorderLayout.CENTER);
				
				//PARAMETROS DE CONFIGURACION
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(3, 1));
				panel.setBorder(new EmptyBorder(10, 10, 10, 10));
				
				JLabel tituloParams = new  JLabel("Parametros:");
				tituloParams.setFont(new Font("Arial", Font.BOLD, 18));	
				tituloParams.setBorder(new EmptyBorder(10, 10, 10, 10));
				panel.add(tituloParams);
				
				JLabel lblNewLabel = new JLabel("Tolerancia = 0.01");
				panel.add(lblNewLabel);
				

				JLabel lblPesoExponencial = new JLabel("Peso exponencial = 2");
				panel.add(lblPesoExponencial);

				
				panel.setPreferredSize(informacion.getPreferredSize());

				informacion.add(panel,BorderLayout.SOUTH);
				pnlK.add(informacion, BorderLayout.NORTH);
				
				panelResultados = new JPResultados();
				panelResultados.setPreferredSize(new Dimension(180,200));
				pnlK.add(panelResultados,BorderLayout.SOUTH);
				
				JButton btnComprobar = panelResultados.getButton();
				
				for( ActionListener al : btnComprobar.getActionListeners() ) {
					btnComprobar.removeActionListener( al );
			    }
				
				btnComprobar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
					
						String[] nombre_clases = VentanaPrincipal.getInstance().getClasesNombres();
						
						double[][] datos_centros = VentanaPrincipal.getInstance().getCentros();
						
								
						ArrayList<Clase>  listaClases = VentanaPrincipal.getInstance().getClases();
						int filas=0;
						for(Clase clase : listaClases) {
							
							filas+=clase.getMatriz().length;
						}
						
						double[][] datos_entrenamiento = new double[filas][listaClases.get(0).getMatriz()[0].length]; 

						int pos = 0;
						
						for(Clase clase: listaClases) {
							//A�ADIR LOS ELEMENTOS DE LA CLASE
							double[][] matrizDeClase = clase.getMatriz();
							for(double[] array: matrizDeClase) {
								datos_entrenamiento[pos]=array;
								++pos;
							}
							
						}
						
						
						//EJEMPLOS
						try {
							ArrayList<double[]> ejemplos = VentanaPrincipal.getInstance().getEjemplos();
							
							double[][] datos_prueba = new double[ejemplos.size()][ejemplos.get(0).length];
							pos =0;
							for(double [] lista :ejemplos) {
								datos_prueba[pos]=lista;
								pos++;
							}
							

							double tolerancia = 0.01,
									peso_exponencial = 2;

							KMeans kmeans = new KMeans(datos_centros, nombre_clases, datos_entrenamiento, tolerancia, peso_exponencial);
							
							String s = "";
							for (Vector<Double> ejemplo : MatrizToVectorVector.metodoCutre(datos_prueba)) {
								s += kmeans.comprobarPunto(ejemplo);
								s += "\n";
							}
							
							panelResultados.setResultados(s);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Debe introducir un ejemplo antes de comprobarlo");
						}
					
					}
					
					
				});
				
		pnlK.setVisible(true);
		this.add(pnlK);
		
	}
	
	public int showConfirmDialog(String title) {
		setTitle(title);
		pack();
		setSize(500, 600);
		setVisible(true);
		return result;
	}
	
	public void refresh(){
		panelResultados.clear();
	}

	public ArrayList<Clase> getClases() {
		return clases;
	}

	public void setClases(ArrayList<Clase> clases) {
		this.clases = clases;
	}
}
