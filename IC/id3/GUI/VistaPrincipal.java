package GUI;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.FlowLayout;

import java.awt.Label;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import Decision.Atributo;

import Decision.Nodo;

public class VistaPrincipal  extends JFrame{

	private static final long serialVersionUID = 1L;
	Change myDialog;
	private JPanel botonera;
	private JPanel arbolPanel;
	private JPanel pnlOpciones;
	private mxGraph graph;
	private Label result;
	private Label resultado;
	private static VistaPrincipal instance;
	
	private Nodo arbol;
	private ArrayList<Atributo> listaAtributos;
	
	public static VistaPrincipal getInstance(){
		
		if(instance==null)
			instance= new VistaPrincipal();
	
		return instance;
	}
	
	
	
	public VistaPrincipal(){
		
			super("Algoritmo ID3");
			graph= new mxGraph();
	}
	
	
	
	public void initView(){
		
	
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		WindowAdapter exitListener = new WindowAdapter() {

		    @Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Quieres salir de la aplicacion?", 
		             "Confirmacion de salida", JOptionPane.YES_NO_OPTION, 
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		           System.exit(0);
		        }
		    }
		};
	this.addWindowListener(exitListener);
	

	//PREPARA EL GRAFICO PARA ACTUALIZAR
	graph.getModel().beginUpdate();
	
	try{
		
		
	 recursivoPinta(arbol, graph,null );
	
	}finally{
	   graph.getModel().endUpdate();
	}
	
	graph.setCellsEditable(false);
	
	 mxIGraphLayout layout = new mxHierarchicalLayout(graph);
     layout.execute(graph.getDefaultParent());

      final mxGraphComponent graphComponent = new mxGraphComponent(graph);
      graphComponent.setBackground(Color.WHITE);
	
    JPanel pnlEncabezado= new JPanel();
    pnlEncabezado.setLayout(new BorderLayout());
	botonera = new JPanel();
	arbolPanel = new JPanel();
	arbolPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
	
	arbolPanel.add(graphComponent);

	this.add(graphComponent,BorderLayout.CENTER);


	
	botonera.setLayout(new FlowLayout(FlowLayout.CENTER, 1,0));
	botonera.setBorder(new EmptyBorder(10, 10, 10, 10));
	
	
	result = new Label("Resultado de los Datos introducidos: Jugar= ");
	resultado = new Label("         ");
	
	botonera.add(result);
	botonera.add(resultado);
	pnlEncabezado.add(botonera,BorderLayout.PAGE_END);


	pnlOpciones= new JPanel();
	pnlOpciones.setLayout(new FlowLayout(FlowLayout.CENTER, 1,0));
	pnlOpciones.setBorder(new EmptyBorder(10, 10, 10, 10));
	
	JButton btnDatos= new JButton("Datos");
	btnDatos.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			myDialog = new Change();
			int res = myDialog.showConfirmDialog("Seleccion de nuevos atributos para el juego");
			if (res == 0) 
				comprueba();
			
			
		}
	});
	
	JButton btnExit= new JButton("Salir");
	btnExit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			exit();
			
		}
	});
	
	pnlOpciones.add(btnDatos);
	pnlOpciones.add(btnExit);
	pnlEncabezado.add(pnlOpciones,BorderLayout.PAGE_START);
	this.add(pnlEncabezado,BorderLayout.PAGE_START);
	this.setSize(graphComponent.getSize());
	
	
	this.pack();	
	this.setResizable(false);
	this.setVisible(true);
	
	}
	
	public void exit(){
		int confirm = JOptionPane.showOptionDialog(
	             null, "¿Quieres salir de la aplicacion?", 
	             "Confirmacion de salida", JOptionPane.YES_NO_OPTION, 
	             JOptionPane.QUESTION_MESSAGE, null, null, null);
	        if (confirm == 0) 
	           System.exit(0);
	        
	}

	private void recursivoPinta(Nodo padre, mxGraph graph, Object verticePadre){
		
		Object v1;
		
		if(verticePadre!=null){
			 v1 = verticePadre;

		}else
		 v1 = graph.insertVertex(graph.getDefaultParent(), null, padre.getNombre(),0, 0, 100,100,  padre.getNombre(), false);
		
		for(Nodo edge: padre.getHijos()){
			
			for(Nodo vertices: edge.getHijos()) {
					
				   Object v2 = graph.insertVertex(graph.getDefaultParent(), null, vertices.getNombre(),0,0,100,100);
				   	graph.insertEdge(graph.getDefaultParent(), null, edge.getNombre(), v1, v2);
				   	if(vertices.getHijos().size()>0)
				   		recursivoPinta(vertices, graph, v2);
				   	
			}
			
		}
		
		
	}


	public void setArbol(Nodo arbol) {
		this.arbol = arbol;
	}
	
	public void setAtributos(ArrayList<Atributo> lista) {
		this.listaAtributos = lista;
	}
	
	
	private void comprueba(){

		
		ArrayList<String> aux= new ArrayList<String>();
		
		aux.add(myDialog.getTime());
		aux.add(myDialog.getTemp());
		aux.add(myDialog.getHume());
		aux.add(myDialog.getVien());
		

		for (String s : aux) {
			System.out.println(s);
		}
		if(aux.size()!=1){
			String solucion = encuentraSolucion(arbol, aux, listaAtributos);
			resultado.setText(solucion);
		}else{
			
			JOptionPane.showMessageDialog(null, "Los elementos deben de estar separados por comas", "Error", JOptionPane.ERROR_MESSAGE);

		}
		
	}
	
	private String encuentraSolucion(Nodo nodo,ArrayList<String> ejemplos, ArrayList<Atributo> lista){
		if(nodo!=null) {
		if(nodo.getNombre().equals("SI")){
			return "SI";
		} else 
			if (nodo.getNombre().equals("NO")){
				return "NO";
			} else {
					String claveDeNodo="";
					int posClave=-1;
					for(Atributo atr: lista){
						//ESTOY EN EL NODO QUE BUSCO
						if(atr.getName().equals(nodo.getNombre())){
							int i=0;
							for(String elemento : ejemplos){
								if(atr.contiene(elemento)){
									claveDeNodo=elemento;
									posClave=i;
								}
								++i;
							}
							
						}
					}
					
					
					if(claveDeNodo==""){
						JOptionPane.showMessageDialog(null, "Uno de los elementos no se puede evaluar", "Error", JOptionPane.ERROR_MESSAGE);
						return "";
					}else{
						Nodo siguiente=null;
						for(Nodo aux: nodo.getHijos()){
							
							if(aux.getNombre().equals(ejemplos.get(posClave)))
								siguiente=aux;
						}
						ejemplos.remove(posClave);
							return encuentraSolucion(siguiente.getHijos().get(0), ejemplos, lista);
						
					}
		}	
		
		}else{
			return "INDETERMINADO";
		}
	}
}
