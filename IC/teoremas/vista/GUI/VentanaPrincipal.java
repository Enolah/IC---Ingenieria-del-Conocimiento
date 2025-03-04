package vista.GUI;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Clase.Clase;
import Clase.Elemento;
import Clase.LecturaElementos;


public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private static VentanaPrincipal instance;

	private static ArrayList<Clase> clases;



	public static void setEjemplos(ArrayList<Elemento> ejemplos) {
		VentanaPrincipal.ejemplos = ejemplos;
	}

	private HashMap<String,ArrayList<Elemento>> listaElementos;
	
	private JPanel panelDatos;
	private JPanel panelEjemplos;
	private JPanel informacion;
	private JPanel contenedor;
	private JLabel labelDatos;
	private JLabel labelEjemplos;

	private JMenuBar menu;
	private JMenuItem cargaEjemploArchivo;
	private JMenuItem cargarEjemploTexto;
	private JMenu cargaEjemplos;
	private JMenu algoritmo;
	private JMenuItem itemKMedias;
	private JMenuItem itemBayes;
	private JMenuItem itemLLoyd;
	private JMenuItem itemSOM;
	private JMenu salir;
	
	private JTextArea textoEjemplos;
	
	private static ArrayList<Elemento> ejemplos;

	
	
	public static VentanaPrincipal getInstance(){
		
		if(instance==null)
			instance= new VentanaPrincipal();
	
		return instance;
	}
	
	
	
	
public void initView(){
		ejemplos = new ArrayList<Elemento>();
	
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		WindowAdapter exitListener = new WindowAdapter() {

		    @Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "�Quieres salir de la aplicacion?", 
		             "Confirmacion de salida", JOptionPane.YES_NO_OPTION, 
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		           System.exit(0);
		        }
		    }
		};
		
	this.addWindowListener(exitListener);
	
	instance.setTitle("Algorimo Bayes, KMedias, Lloyd y SOM");
	instance.setPreferredSize(new Dimension(900,700));
	instance.setResizable(true);
	instance.pack();
	
	//panelDatos
	panelDatos = new JPanel();

	//DATOS
	labelDatos = new JLabel("Datos:");
	labelDatos.setFont(new Font("Arial", Font.BOLD, 18));

	panelDatos.add(labelDatos);
	
	panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));

	panelDatos.setPreferredSize(new Dimension(180,600));
	JPanel panelTest = new JPanel();
	panelTest.setLayout(new BoxLayout(panelTest, BoxLayout.Y_AXIS));
	panelTest.setPreferredSize(new Dimension(150, 600));
	JScrollPane scroll = new JScrollPane (panelTest,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	panelDatos.add(scroll);
	
	for(ArrayList<Elemento> key : listaElementos.values()){
		
		
			JTextArea textArea = new JTextArea();
			String cadena ="";
			
			for(Elemento elem: key) {
				
				cadena+=elem.getListaString() +'\n';
			}
			
			textArea.setText(cadena);
			
			textArea.setEditable(false);
			
			JScrollPane scroll2 = new JScrollPane (textArea, 
					   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll2.setBorder(new TitledBorder("Clase " + key.get(0).nombreElemento + ": "));
			
			panelTest.add(scroll2);
			
	
	}
	
	
	//panelejemplos
		panelEjemplos = new JPanel();

		//Ejemplo
		labelEjemplos = new JLabel("Ejemplos:");
		labelEjemplos.setFont(new Font("Arial", Font.BOLD, 18));

		panelEjemplos.add(labelEjemplos);
		
		panelEjemplos.setLayout(new BoxLayout(panelEjemplos, BoxLayout.Y_AXIS));

		panelEjemplos.setPreferredSize(new Dimension(180,600));
		
		JPanel panelTest2 = new JPanel();
		panelTest2.setLayout(new BoxLayout(panelTest2, BoxLayout.Y_AXIS));
		panelTest2.setPreferredSize(new Dimension(150, 500));
		JScrollPane scroll2 = new JScrollPane (panelTest2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelEjemplos.add(scroll2);
		textoEjemplos= new JTextArea();
		textoEjemplos.setText("");
		textoEjemplos.setEditable(false);
		JScrollPane scroll3 = new JScrollPane (textoEjemplos, 
						   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
		panelTest2.add(scroll3);

		//Pasos a seguir para ejecutar el programa
		
		informacion = new JPanel();
		informacion.setLayout(new BorderLayout());
		JPanel centro= new JPanel();
		
		JLabel titulo = new  JLabel("Pasos:");
		titulo.setFont(new Font("Arial", Font.BOLD, 18));
		centro.add(titulo);
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
		
		JLabel paso1= new JLabel("<html>1� Seleccionamos un m�todo para cargar un ejemplo</html>");
		paso1.setFont(new Font("Arial", Font.ITALIC, 18));
		JLabel paso2= new JLabel("<html>2� Elegimos un algoritmo del men�</html>");
		paso2.setFont(new Font("Arial", Font.ITALIC, 18));
		JLabel paso3= new JLabel("<html>3� Pulsamos el bot�n de comprobar</html>");
		paso3.setFont(new Font("Arial", Font.ITALIC, 18));
		
		centro.add(paso1);
		centro.add(paso2);
		centro.add(paso3);
		
		informacion.add(centro, BorderLayout.CENTER);
		
	
	//BARRA DE MENU
		menu = new JMenuBar();
		
	//CARGAR EJEMPLOS
	
	cargaEjemplos = new JMenu("Cargar ejemplo");

	cargarEjemploTexto= new JMenuItem("Cargar manualmente");
	cargarEjemploTexto.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
		
			cargarEjemploDesdeTexto();
		}
	});
	
	
	cargaEjemploArchivo = new JMenuItem("Cargar desde archivo");
	
	cargaEjemploArchivo.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
		
			cargarEjemploDesdeArchivo();
		}
	});
	
	cargaEjemplos.add(cargaEjemploArchivo);
	cargaEjemplos.add(cargarEjemploTexto);
	
	
	//SELECCION DE ALGORTIMOS
	
	algoritmo= new JMenu("Algoritmos");
	
	itemBayes= new JMenuItem("Bayes");
	itemBayes.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			cambiaBayes();
		}
	});
	
	itemKMedias = new JMenuItem("K-Medias Borroso");
	itemKMedias.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			cambiaKmedias();
		}
	});
	
	itemLLoyd = new JMenuItem("LLoyd");
	itemLLoyd.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			cambiaLLoyd();
		}	
	});
	
	itemSOM= new JMenuItem("SOM");
	itemSOM.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			cambiaSOM();
		}
	});
	
	//a�adir al menu
	algoritmo.add(itemBayes);
	algoritmo.add(itemKMedias);
	algoritmo.add(itemLLoyd);
	algoritmo.add(itemSOM);
	
	
	
	//BOTON SALIR
	
	salir = new JMenu("Salir");
	
	JMenuItem si = new JMenuItem("Si");
	si.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
			
		}
	});
	
	JMenuItem no = new JMenuItem("No");
	no.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
		}
	});
	
	salir.add(si);
	salir.add(no);
	
	
	//A�adimos todo a la barra del menu
	
	menu.add(cargaEjemplos);
	menu.add(algoritmo);
	menu.add(salir);
	
	
	
	contenedor = new JPanel();
	contenedor.setLayout(new FlowLayout());
	contenedor.setPreferredSize(new Dimension(900, 300));
	contenedor.setBorder(new EmptyBorder(10, 10, 10, 10));
    contenedor.setComponentOrientation(
            ComponentOrientation.LEFT_TO_RIGHT);
	instance.setContentPane(contenedor);
	

	instance.setJMenuBar(menu);

	contenedor.add(panelDatos);
	contenedor.add(panelEjemplos);
	contenedor.add(informacion);

	
	instance.pack();
	
	instance.setVisible(true);

}

public void setClases(ArrayList<Clase> clases) {
	
	this.clases=clases;
	
	
}


public void setListaElementos(HashMap<String,ArrayList<Elemento>> listaElementos) {
	this.listaElementos = listaElementos;
}

private void cambiaBayes() {

	BayesPanel b= new BayesPanel();
	int res= b.showConfirmDialog("Algoritmo de Bayes");
	if(res==0){
		
	}
}

	private void cambiaSOM(){
		
		SOMPanel s= new SOMPanel();
		int res= s.showConfirmDialog("Algoritmo de SOM");
		if(res==0){
			
		}
		
	}

private void cambiaLLoyd() {

	LLoydPanel l= new LLoydPanel();
	int res= l.showConfirmDialog("Algoritmo de Lloyd");
	if(res==0){
		
	}
	

	
}
private void cambiaKmedias() {

	KMedidasPanel k= new KMedidasPanel();
	int res= k.showConfirmDialog("Algoritmo de KMedias");
	if(res==0){
		
	}
}




private void cargarEjemploDesdeArchivo() {
	
	JFileChooser jf = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter( "text", ".txt");
	jf.setFileFilter(filter);
	int resultado= jf.showDialog(instance, "Cargar");
	
	if(resultado==JFileChooser.APPROVE_OPTION) {
		String cadena = "";
		cadena = jf.getSelectedFile().getAbsolutePath();
		
		try {
			Elemento elem= LecturaElementos.leeEjemplo(cadena);
			cadena = textoEjemplos.getText();
			ejemplos.add(elem);
			cadena+=elem.getListaString()+"\n";
			textoEjemplos.setText(cadena);
		} catch (IOException e) {
		
			e.printStackTrace();
		}

	}
	
	
	
	
	
}

private void cargarEjemploDesdeTexto() {
	
	String texto = JOptionPane.showInputDialog("<html>Introduzca un elemento separado por comas, siguiendo el siguiente formato: 0.0,0.0,0.0,0.0 </html>");
	
	if(texto!=null) {
		double[] lista=null;
		ArrayList<Double> numeros = new ArrayList<Double>();
		String op[] = texto.split(",");
		for (String elementData : op) {
		if (elementData.matches("[0-9]+.+[0-9]+"))
			numeros.add(Double.parseDouble(elementData));
		
		
	}
		lista = new double[numeros.size()];
		
		int i=0;
		for(double num : numeros) {
			lista[i]=num;
			++i;
		}
		Elemento elem = new Elemento(lista, null);
		ejemplos.add(elem);
		String cadena = textoEjemplos.getText();
		cadena+=elem.getListaString()+"\n";
		textoEjemplos.setText(cadena);
	}
		
	
	
}


public  ArrayList<Clase> getClases() {
	return clases;
}


public  String[] getClasesNombres() {
	String[] lista = new String[clases.size()];
	int i=0;
	for(Clase elem: clases) {
		lista[i]=elem.getNombreElemento();
	++i;
	}
	
	return lista;
}

public double[][] getCentros() {
	
	double[][] lista = new double[clases.size()][clases.get(0).getCentro().length];
	int i=0;
	for(Clase elem: clases) {
		lista[i]=elem.getCentro();
	++i;
	}
	
	return lista;
	
}

public  ArrayList<double[]> getEjemplos() {
	
	ArrayList<double[]> lista = new ArrayList<double[]>();
	
	for(Elemento elem: ejemplos)
		lista.add(elem.getLista());
	
	return lista;
}


}
