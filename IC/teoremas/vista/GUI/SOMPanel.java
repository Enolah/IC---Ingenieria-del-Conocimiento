package vista.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.util.function.IntToDoubleFunction;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Clase.Clase;
import algoritmos.KMeans;
import algoritmos.SOM;
import util.MatrizToVectorVector;

public class SOMPanel extends JDialog{
	
	private static final long serialVersionUID = 1L;
	private JPResultados panelResultados;
	private ArrayList<Clase> clases;
	public static final int OK_OPTION = 0;
	public static final int CANCEL_OPTION = 1;
	private int result = -1;
	
	private JPanel pnlS = new JPanel();
	
	public SOMPanel(){
		super(new JFrame(), "SOM", true);
		initGUI();
	}

	private void initGUI() {
		
		//INFORMACION
				pnlS.setPreferredSize(new Dimension(600, 600));
				pnlS.setLayout(new BorderLayout());

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
				panel.setLayout(new GridLayout(7, 1));
				panel.setBorder(new EmptyBorder(10, 10, 10, 10));
				
				JLabel tituloParams = new  JLabel("Parametros:");
				tituloParams.setFont(new Font("Arial", Font.BOLD, 18));	
				tituloParams.setBorder(new EmptyBorder(10, 10, 10, 10));
				panel.add(tituloParams);
					
				JLabel lblNewLabel = new JLabel("Tolerancia = 0.000001");
				panel.add(lblNewLabel);
			
				JLabel lblPesoExponencial = new JLabel("M�ximo de iteraciones = 1000");
				panel.add(lblPesoExponencial);
				
				JLabel razon = new JLabel("Raz�n de aprendizaje = 0.1");
				panel.add(razon);
				
				JLabel ini = new JLabel("Alfa Inicial = 0.1");
				panel.add(ini);
				
				JLabel fin = new JLabel("Alfa final = 0.01");
				panel.add(fin);
				
				JLabel text = new JLabel("Consideramos que un centro se actualiza cuando Kalfa(k) > T, "
						+ "siendo T = 0.00001");
				panel.add(text);
			
				
				panel.setPreferredSize(informacion.getPreferredSize());

				informacion.add(panel,BorderLayout.SOUTH);
				pnlS.add(informacion, BorderLayout.NORTH);
	
				panelResultados = new JPResultados();
				panelResultados.setPreferredSize(new Dimension(180,200));
				pnlS.add(panelResultados,BorderLayout.SOUTH);
				
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
							
							IntToDoubleFunction funcion = (i) -> 0.1;
							double tolerancia = 0.000001, alfa_ini= 0.1, alfa_fin=0.01;
							int max_iteraciones = 1000;

							SOM som = new SOM(datos_centros, nombre_clases, datos_entrenamiento, funcion, alfa_ini,alfa_fin,
									tolerancia,0.00001, max_iteraciones);
							
							String s = "";
							for (double[] prueba : datos_prueba) {
								s += som.predecirClase(prueba);
								s += "\n";
							}
							
							panelResultados.setResultados(s);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Debe introducir un ejemplo antes de comprobarlo");
						}
					
					}
					
					
				});
				
		pnlS.setVisible(true);
		this.add(pnlS);
		
	}

	public int showConfirmDialog(String title) {
		setTitle(title);
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
