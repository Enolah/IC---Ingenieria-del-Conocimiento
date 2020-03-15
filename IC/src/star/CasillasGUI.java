package star;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CasillasGUI extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private TableroGUI tablero;
	private ImageIcon fondo;
	private static int[] casillaMarcada = new int[2];
	mapaMatriz map;
	JMenuItem itemInicio;
	JMenuItem itemMeta;

	public CasillasGUI(TableroGUI t, mapaMatriz map) {
		this.map = map;
		initComponents();
		this.tablero = t;
		if (this.tablero.getTipoTablero() == true) {// tablero responde a clics
			this.addMouseListener(this);
		}
	}

	public void setFondo(ImageIcon fondo) {
		this.fondo = fondo;
	}

	public ImageIcon getFondo() {
		return this.fondo;
	}

	void initComponents() {
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 161, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 193, Short.MAX_VALUE));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(fondo.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);

	}

	// metodos del raton no implementados
	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 3) {
			CasillasGUI.setCasillaMarcada(tablero.getCoordenadas((CasillasGUI) e.getComponent()));
			this.tablero.pintarStart(CasillasGUI.getCasillaMarcada()[0], CasillasGUI.getCasillaMarcada()[1]);

			map.setReferencias(CasillasGUI.getCasillaMarcada()[1], CasillasGUI.getCasillaMarcada()[0]);

		} else if (e.getButton() == 2) {
			CasillasGUI.setCasillaMarcada(tablero.getCoordenadas((CasillasGUI) e.getComponent()));

			this.tablero.pintarStartFinish(CasillasGUI.getCasillaMarcada()[0], CasillasGUI.getCasillaMarcada()[1]);
			if (map.setReferencias(CasillasGUI.getCasillaMarcada()[1], CasillasGUI.getCasillaMarcada()[0]) == 1) {
				try {
					lista nueva = map.getCamino();
					nodo aux = nueva.primero;
					while (aux.siguiente != null) {

						this.tablero.pintarWalk(aux.cordY, aux.cordX);
						aux = aux.siguiente;
					}
				} catch (NullPointerException ev) {
					JOptionPane.showMessageDialog(null, "no hay camino");
				}
				this.tablero.pintaCarrito();
			}

		} else {
			CasillasGUI.setCasillaMarcada(tablero.getCoordenadas((CasillasGUI) e.getComponent()));
			this.tablero.pintar(CasillasGUI.getCasillaMarcada()[0], CasillasGUI.getCasillaMarcada()[1]);
			map.setParedes(CasillasGUI.getCasillaMarcada()[1], CasillasGUI.getCasillaMarcada()[0]);
		}
	}

	public static int[] getCasillaMarcada() {
		return casillaMarcada;
	}

	public static void setCasillaMarcada(int[] aCasillaMarcada) {
		casillaMarcada = aCasillaMarcada;
	}

}
