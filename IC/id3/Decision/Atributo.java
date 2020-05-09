package Decision;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Atributo {

	private HashMap<String, Double> p;
	private HashMap<String, Double> n;
	private HashMap<String, Integer> s;
	private HashMap<String, Double> merito;
	private int total;
	private String nombre;

	public Atributo(String nombre) {
		this.nombre = nombre;
		p = new HashMap<String, Double>();
		n = new HashMap<String, Double>();
		s = new HashMap<String, Integer>();
		merito= new HashMap<String,Double>();
	}

	public void addElement(String opcion, boolean v) {
		if (v) {
			if (p.containsKey(opcion))
				p.put(opcion, p.get(opcion) + 1.0);
			else
				p.put(opcion, 1d);
		} else {
			if (n.containsKey(opcion))
				n.put(opcion, n.get(opcion) + 1.0);
			else
				n.put(opcion, 1d);

		}

	}

	
	
	
	public double calculaMerito(String actual) {

		double nValor=0;
		double pValor=0;
		double r=0;

		if (n.containsKey(actual)) {
			nValor = n.get(actual);
		} else {
			nValor = 0;
		}

		if (p.containsKey(actual)) {
			pValor = p.get(actual);
		} else {
			pValor = 0;
		}
		double aux= s.get(actual);
		r = aux/total;

		double entropia = 0;

		if (nValor == 0) {
			entropia = (-pValor * ((Math.log(pValor) / Math.log(2))));
		} else if (pValor == 0) {
			entropia = (-(nValor * (Math.log(nValor) / Math.log(2))));
		} else {
			entropia = (-pValor * ((Math.log(pValor) / Math.log(2))) - (nValor * (Math.log(nValor) / Math.log(2))));
		}

		return r*entropia;

	}

	
	public void borra(){
		
		p.clear();
		n.clear();
		s.clear();
		merito.clear();
		total=0;
	}
	
	
	
	public void actualiza() {

		Set<String> llavero = new HashSet<String>(p.keySet());
		for (String clave : n.keySet()) {

			if (!llavero.contains(clave)) {

				llavero.add(clave);
			}
		}

		for (String clave : llavero) {
			if (p.containsKey(clave) && n.containsKey(clave)) {
				total += (p.get(clave).intValue() + n.get(clave).intValue());
				s.put(clave, p.get(clave).intValue() + n.get(clave).intValue());
				p.put(clave, p.get(clave) / s.get(clave));
				n.put(clave, n.get(clave) / s.get(clave));

			} else if (p.containsKey(clave)) {
				total += p.get(clave).intValue();
				s.put(clave, p.get(clave).intValue());
				p.put(clave, p.get(clave) / s.get(clave));
			} else {
				total += n.get(clave).intValue();
				s.put(clave, n.get(clave).intValue());
				n.put(clave, n.get(clave) / s.get(clave));

			}

		}

		for(String clave: llavero){
				merito.put(clave,calculaMerito(clave));
		}
			

	}
	
	
	public double getMerito(){
		
		double meritoTotal=0;
		
		for(String value : merito.keySet()){
			
			meritoTotal+=merito.get(value);
		}
		
		return meritoTotal;
	}
	
	
	public int getPositivos() {
		
		return p.size();
		
	}
	
	
	public int getNegativos() {
		
		return n.size();
		
	}
	
	public double getPositivos(String clave){
		if(p.containsKey(clave))
			return p.get(clave)*s.get(clave);
		else
			return 0;
	}
	
	
	public double getNegativos(String clave){
		if(n.containsKey(clave))
			return n.get(clave)*s.get(clave);
		else
			return 0;
	}
	
	public double getNum(String clave){
		
		return s.get(clave);
	}
	
	public double getMerito(String clave){
		
		return merito.get(clave);
	}
	
	public Set<String> getClaves(){
		
		return s.keySet();
	}
	
	
	public boolean contiene(String clave){
		
		return s.containsKey(clave);
	}

	public String getP() {

		String cadena = "";

		cadena = p.toString() + " " + n.toString() + " " + s.toString() + " " + total;

		return cadena;

	}

	public String getName() {

		return nombre;
	}
}
