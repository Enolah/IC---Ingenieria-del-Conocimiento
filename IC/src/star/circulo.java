package star;

import javax.swing.*;
import java.awt.*;

public class circulo
{
    int n,x,y;
    boolean visitado;
    private Image circle;
    
    public circulo( int n, int x, int y )
    {
        this.n = n;
        this.x = x;
        this.y = y;
        ImageIcon iic = new ImageIcon( "/zelda.png" );
        circle = iic.getImage();
    }
    
    public void painter (Graphics G,TableroGUI pp,int num)
    {
        G.drawImage(circle,x-30,y-30,pp);
       // G.setColor(Color.RED);
       // G.drawString(""+num, x-4, y+4);
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    /*
    public boolean checa( int x, int y )
    {
        return( ( x == this.x ) && ( this.y == y ) );
    }*/
    
   /* public void ilumina()
    {
        ImageIcon iic = new ImageIcon ("circleChromeON.png");
        this.circle = iic.getImage();
    }
    
    public void apaga()
    {
        ImageIcon iic = new ImageIcon ("circleChrome.png");
        this.circle = iic.getImage();
    }
    
    public void iluminaOpcion()
    {
        ImageIcon iic = new ImageIcon ("circleChromeOption.png");
        this.circle = iic.getImage();
    }*/
}
