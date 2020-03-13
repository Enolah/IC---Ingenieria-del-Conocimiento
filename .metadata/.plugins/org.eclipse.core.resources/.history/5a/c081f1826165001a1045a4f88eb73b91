package star;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;





public class Main extends JFrame{

	private TableroGUI tableroGUI1; 
	private CasillasGUI casillas;
	private mapaMatriz map;
	
	public Main(){
		init();
	}
	
	private void init(){
		tableroGUI1 = new TableroGUI( 10, true ); //tipo tablero
        this.setLocationRelativeTo( null );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
     //   this.setSize(500, 500);
        
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3,1,1,5));
        this.getContentPane().setLayout(new BorderLayout());
       
      
        GroupLayout tableroGUI1Layout = new GroupLayout( tableroGUI1 );
        tableroGUI1.setLayout( tableroGUI1Layout ); //creamos un tablero de tipo group
        tableroGUI1Layout.setHorizontalGroup(
            tableroGUI1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );
        tableroGUI1Layout.setVerticalGroup(
            tableroGUI1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableroGUI1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tableroGUI1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        
        this.getContentPane().add(panel,BorderLayout.WEST);
        
        JButton b= new JButton("Aceptar");
        b.setOpaque(true);
//		b.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				map.setReferencias(CasillasGUI., y)
//				
//			}
//		});
//        
        JButton b1= new JButton("Salir");
        b1.setOpaque(true);
        b1.addActionListener((new ActionListener() {
        	   public void actionPerformed(ActionEvent e) {
        		     System.exit(0);
        		   }
        		 }));
        
        JButton b2= new JButton("Replay");
        b2.setOpaque(true);
        b2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
			//	casillas.initComponents();
			//	tableroGUI1.initComponents();
			//	init();	//se restaura el panel pero la ejecucion ha termnado
			//inicializar matriz??
			}});
        
        panel2.add(b);
        panel2.add(b1);
        panel2.add(b2);
       
        this.getContentPane().add(panel2,BorderLayout.CENTER);
        
        pack();
        
        
    }
		
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Mio v = new Mio();
				 v.setVisible(true);
			}
		});

	}
	
	  
}