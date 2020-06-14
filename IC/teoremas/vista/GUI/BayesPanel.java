package vista.GUI;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clase.Clase;
import algoritmos.Bayes;

public class BayesPanel extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPResultados panelResultados;
	private ArrayList<Clase> clases;
	public static final int OK_OPTION = 0;
	public static final int CANCEL_OPTION = 1;
	private int result = -1;
	
	private JPanel pnlB = new JPanel();
	
	public BayesPanel(){
		super(new JFrame(), "Bayes", true);
		initGUI();
	}

	private void initGUI() {
		
		pnlB.setLayout(new BorderLayout());
		pnlB.setPreferredSize(new Dimension(600, 600));
		
		JPanel informacion = new JPanel();
		informacion.setLayout(new BorderLayout());
		JLabel titulo = new  JLabel("Informacion:");
		titulo.setFont(new Font("Arial", Font.BOLD, 18));
		informacion.add(titulo, BorderLayout.NORTH);
		JLabel texto = new JLabel("<html> El algorimo de Bayes no necesita inicialización </html>");
		informacion.add(texto,BorderLayout.CENTER);
		JLabel nada= new JLabel("");
		informacion.add(nada,BorderLayout.SOUTH);
		
		pnlB.add(informacion,BorderLayout.NORTH);
		
				
		//Resultados de los ejemplos
				
		panelResultados = new JPResultados();
		panelResultados.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelResultados.setPreferredSize(new Dimension(180,200));
		pnlB.add(panelResultados,BorderLayout.CENTER);
		
		JButton btnComprobar = panelResultados.getButton();
		
		for( ActionListener al : btnComprobar.getActionListeners() ) {
			btnComprobar.removeActionListener( al );
	    }
		
		btnComprobar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Bayes bayes = new Bayes();
				
				
				for (int i = 0; i < VentanaPrincipal.getInstance().getClases().size(); i++) {
					bayes.aprenderClase(VentanaPrincipal.getInstance().getClases().get(i).getMatriz(), 
							VentanaPrincipal.getInstance().getClases().get(i).getNombreElemento());
				}
				
				String s = "";
				if (VentanaPrincipal.getInstance().getEjemplos().size() == 0)
					JOptionPane.showMessageDialog(null, "Debe introducir un ejemplo antes de comprobarlo");
				else {

					for (double[] ejemplo : VentanaPrincipal.getInstance().getEjemplos()) {
						s += bayes.predecirClase(ejemplo);
						s += "\n";
					}

					panelResultados.setResultados(s);
				}
			}
			
			
		});
		
		pnlB.setVisible(true);
		this.add(pnlB);
	}
	
	public void refresh(){
		panelResultados.clear();
	}
	
	public int showConfirmDialog(String title) {
		setTitle(title);
		pack();
		setSize(500, 600);
		setVisible(true);
		return result;
	}
}
