package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Change extends JDialog{

	private static final long serialVersionUID = 1L;
	public static final int OK_OPTION= 0;
	public static final int CANCEL_OPTION=1;
	private int result= -1;
	
	private JComboBox<Object> time;
	private JComboBox<Object> temp; 
	private JComboBox<Object> hume;
	private JComboBox<Object> vien;


	private JButton aceptar;
	private JButton cancelar;
	

	
	public Change(){
		super(new JFrame(), "Cambio Datos",true);
	
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		JLabel la = new JLabel ("<html>Selecciona los datos deseados para los atributos."
				+ "Pulsa OK para confirmar los datos </html>");
		
		panelPrincipal.add(la, BorderLayout.NORTH);
		JLabel tiempo= new JLabel ("Tiempo Exterior: ");
		time= new JComboBox<Object>();
		time.addItem("soleado");
		time.addItem("nublado");
		time.addItem("lluvioso");
		
		JLabel temperatura= new JLabel ("Temperatura: ");
		temp = new JComboBox<Object>();
		temp.addItem("caluroso");
		temp.addItem("templado");
		temp.addItem("frio");
		
		JLabel humedad= new JLabel ("Humedad: ");
		hume = new JComboBox<Object>();
		hume.addItem("alta");
		hume.addItem("normal");
		
		JLabel viento= new JLabel ("Viento: ");
		vien = new JComboBox<Object>();
		vien.addItem("verdad");
		vien.addItem("falso");
		
		
		JPanel medio = new JPanel();
		panelPrincipal.setPreferredSize(new Dimension(350,200));
		medio.add(tiempo);
		medio.add(time);
		medio.add(temperatura);
		medio.add(temp);
		medio.add(humedad);
		medio.add(hume);
		medio.add(viento);
		medio.add(vien);
		
		panelPrincipal.add(medio,BorderLayout.CENTER);
		aceptar= new JButton("OK");
		cancelar = new JButton("Cancel");
		JPanel sur= new JPanel();
		sur.setLayout(new FlowLayout());
		sur.add(aceptar);
		sur.add(cancelar);
		aceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				result=OK_OPTION;
				setVisible(false);
				dispose();
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				result= CANCEL_OPTION;
				setVisible(false);
				dispose();
			}
		});
		
		panelPrincipal.add(sur,BorderLayout.SOUTH);
		
		panelPrincipal.setVisible(true);
		this.add(panelPrincipal);
	}
	
	/*getter que nos devuelven elcontenido de dichos elementos*/

	public String getTime() {
		return (String) time.getSelectedItem();
	}

	public String getTemp() {
		return (String) temp.getSelectedItem();
	}

	public String getHume() {
		return (String) hume.getSelectedItem();
	}

	public String getVien() {
		return (String) vien.getSelectedItem();
	}
	
	
	public int showConfirmDialog(String title){
		setTitle(title);

		pack();
		setSize(650,150);
		setVisible(true);
		return result;
	}


}