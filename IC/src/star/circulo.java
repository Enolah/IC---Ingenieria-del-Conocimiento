package star;

import javax.swing.*;
import java.awt.*;

public class circulo {
	int n, x, y;
	boolean visitado;
	private Image circle;

	public circulo(int n, int x, int y) {
		this.n = n;
		this.x = x;
		this.y = y;
		ImageIcon iic = new ImageIcon("/zelda.png");
		circle = iic.getImage();
	}

	public void painter(Graphics G, TableroGUI pp, int num) {
		G.drawImage(circle, x - 30, y - 30, pp);

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
