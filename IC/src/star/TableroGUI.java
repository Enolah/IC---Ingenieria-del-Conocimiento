package star;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TableroGUI extends JPanel implements Runnable{
    private ImageIcon agua, tocado, startFinish, caminoWalk,carrito,start;
    private boolean tipoTablero;
    private CasillasGUI [][] casillas ;
    mapaMatriz map;///////////////
    Thread animacion;////////
    circulo circuloA;/////////
    int x0,y0,x1=200,y1=65+35;
    int termin = 0;
    lista nueva = new lista("nuevesita");
    nodo nuevo;
    
    public TableroGUI() {
        initComponents();
    }

    public TableroGUI(int size, boolean tipo) {
        initComponents();
        map = new mapaMatriz( size );////////////
        x0= (1*35)-5;/////( (i+1) *35 )-5;
        y0= (1*35)-5;
        circuloA = new circulo(0,x0,y0);///////////
        int x,y;
        setLayout(new GridLayout(size, size));
        this.tipoTablero = tipo;
        cargarImagenes();
        casillas = new CasillasGUI[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                casillas[i][j] = new CasillasGUI(this,map);/////////
                casillas[i][j].setFondo(agua);
                x = (i * 35)+1;
                y = (j * 35)+1;
                casillas[i][j].setBounds(x, y, 34, 34);
                this.add(casillas[i][j]);
            }
        }
    }
    
    public boolean getTipoTablero(){
        return this.isTipoTablero();
    }
    
    //////////////////////////
    public void pintar(int x, int y){
        this.casillas[x][y].setFondo(tocado);
        this.repaint();
    }
    
    public void pintarStartFinish(int x, int y){
        this.casillas[x][y].setFondo(startFinish);
        this.repaint();
    }
    
    public void pintarStart(int x, int y){
    	this.casillas[x][y].setFondo(start);
        this.repaint();
    }
    
    public void pintarWalk(int x, int y){
        this.casillas[x][y].setFondo(caminoWalk);
       nuevo = new nodo(x,y);
        nueva.agregar(nuevo);
        //animacion.start();
        this.repaint();
    }
    
    public void pintaCarrito(){
       // animacion = new Thread();
       //x1=x*30;
        //y1=y*30;
        animacion.start();
      // nueva.muestralista();
    }
    
    /////// METODO PARA CARGAR LAS IMAGENES QUE SE UTILIZAN ///////
    private void cargarImagenes() {
        this.agua = TableroGUI.cargarFondo("/pasto.gif");
        this.tocado = TableroGUI.cargarFondo("/pared.gif");
        this.startFinish = TableroGUI.cargarFondo("/trifuerza.gif");
        this.caminoWalk = TableroGUI.cargarFondo("/walk2.gif");
        this.carrito = TableroGUI.cargarFondo("/circleChrome.gif");
        this.start= TableroGUI.cargarFondo("/link.gif");
    }
    
    protected static ImageIcon cargarFondo( String ruta ){
        java.net.URL localizacion = TableroGUI.class.getResource(ruta);
        if( localizacion != null ) {
            return new ImageIcon( localizacion );
        } else {
            System.err.println( "No se ha encontrado el archivo: " + ruta );
            return null;
        }
    }
    
    public int[] getCoordenadas( CasillasGUI casilla ){
        int [] coordenadas = new int[2];
        for( int i = 0; i < this.casillas.length; i++ ){
            for( int j = 0; j < this.casillas.length; j++ ){
                if( this.casillas[i][j] == casilla ){
                    coordenadas[0] = i;
                    coordenadas[1] = j;
                }
            }
        }
        return coordenadas;
    }
    
    public CasillasGUI[][] getCasillas() {
        return casillas;
    }
    
    public void setCasillas(CasillasGUI[][] casillas) {
        this.casillas = casillas;
    }
    
    public boolean isTipoTablero() {
        return tipoTablero;
    }    
    public void setTipoTablero(boolean tipoTablero) {
        this.tipoTablero = tipoTablero;
    }
                              
    public void initComponents() {

        setLayout(null);
        animacion = new Thread(this);//////////////
        setBackground(new Color(0, 0, 0));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        setPreferredSize(new Dimension(351, 351));
    }
    
    public void paint (Graphics G)
    {
        super.paint(G);
        
        try{
            circuloA.painter(G,this,0); //se pinta con el paint pero del circulo
        }catch(NullPointerException e){;}
    }
    
    public void run()
    {
        int j;
        nodo aux = map.puntoMeta;
        nodo aux2 = nueva.primero;
       // nueva.muestralista();
        y0 = ( ( aux.cordX +1) *35 )-5;
        x0 = ( ( aux.cordY +1) *35 )-5;
        y1 = ( ( aux2.cordY +1) *35 )-5;
        x1 = ( ( aux2.cordX +1) *35 )-5;
        
        while( aux2 != null ){
        //    System.out.println("repeticion 0 de "+(((x0+5)/35)-1)+" "+(((y0+5)/35)-1)+" hacia "+(((x1+5)/35)-1)+" "+(((y1+5)/35)-1));

        
        
       //     System.out.println("repeticion1 de "+(((x0+5)/35)-1)+" "+(((y0+5)/35)-1)+" hacia "+(((x1+5)/35)-1)+" "+(((y1+5)/35)-1));
            if( ( y0 < y1 ) && ( x0 ==x1 ) ){
       //        System.out.println("caso 1");
                for(int w = y0; w < y1; w++){
                    circuloA.x = x0;
                    circuloA.y = w;
                
                    this.repaint();
                    try {Thread.sleep(10);} catch (InterruptedException e) {;}
                }
            }
        
            else if( ( y0 > y1 ) && ( x0 ==x1 ) ){
        //        System.out.println("caso 2 ");
                for(int w = y0; w > y1; w--){
                    circuloA.x = x0;
                    circuloA.y = w;
                
                    this.repaint();
                    try {Thread.sleep(10);} catch (InterruptedException e) {;}
                }
            }
        
            else if( ( y0 == y1 ) && ( x0 < x1 ) ){
        ///        System.out.println("caso 3");
                for(int w = x0; w < x1; w++){
                    circuloA.x = w;
                    circuloA.y = y0;
                
                    this.repaint();
                    try {Thread.sleep(10);} catch (InterruptedException e) {;}
                }
            }
        
            else if( ( y0 == y1 ) && ( x0 > x1 ) ){
         //       System.out.println("caso 4");
                for(int w = x0; w > x1; w--){
                    circuloA.x = w;
                    circuloA.y = y0;
                
                    this.repaint();
                    try {Thread.sleep(10);} catch (InterruptedException e) {;}
                }
            }
            else{
          //      System.out.println(" aqui el porque "+(((x0+5)/35)-1)+" "+(((y0+5)/35)-1)+" hacia "+(((x1+5)/35)-1)+" "+(((y1+5)/35)-1));
       
            }
            aux=aux2;
            aux2=aux2.siguiente;
            if(aux2!=null){
                x0 = ( ( aux.cordX +1) *35 )-5;
                y0 = ( ( aux.cordY +1) *35 )-5;
            
                y1 = ( ( aux2.cordY +1) *35 )-5;
                x1 = ( ( aux2.cordX +1) *35 )-5;
            }
        }//fin while

        
        termin = 1;
    }
                     
}
