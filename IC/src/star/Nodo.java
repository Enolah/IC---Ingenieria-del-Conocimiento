package star;
import java.util.*;
import java.util.ArrayList;

import javax.swing.Painter;

public class Nodo {
	private Pair<Integer,Integer> posicion;
	private double costeTotal;
	private double penalizacion;
	//private ArrayList<Integer> coordenada;
	private ArrayList<Nodo> hijo = new ArrayList<Nodo>();
	private ArrayList<Nodo> padre = new ArrayList<Nodo>();
	private double costeDesdeInicioHastaMi;
	private double costeDesdeMiHastaLaMeta;
	private double costeDeDesplazarseDesdePadreHastaMi;


	public Nodo(Nodo p, Pair pos, final double coste, final double penaliza, final Pair metas)
	{
		this.costeDesdeInicioHastaMi = (p != null)? (p.getCosteDesdeInicioHastaMi() + coste) : 0;
		this.costeDeDesplazarseDesdePadreHastaMi = coste;
		this.padre = new ArrayList<Nodo> ();
		this.costeDesdeMiHastaLaMeta = 0;
		this.penalizacion = penaliza;
//C++ TO JAVA CONVERTER TODO TASK: The following line was determined to be a copy assignment (rather than a reference assignment) - this should be verified and a 'copyFrom' method should be created:
//ORIGINAL LINE: this.posicion = pos;
		this.posicion= pos;
		this.costeTotal = 0;

		double distanciaMejor = MAS_INFINITO;

		for (Pair<Integer,Integer> posicion : metas)
		{

			distanciaMejor = Math.min(distanciaEuclidea(this.posicion, posicion),distanciaMejor);
		}

		this.costeDesdeMiHastaLaMeta = distanciaMejor;

		this.costeTotal = this.penalizacion + this.costeDesdeInicioHastaMi + this.costeDesdeMiHastaLaMeta;

	}

	public double distanciaEuclidea(final Pair p1, final Pair p2)
	{
		return Math.sqrt(Math.pow(p1.getFirst() - p2.getFirst(),2) + Math.pow(p1.getSecond() - p2.getSecond(),2));
	}

	public final void nuevoPadre(Nodo nodoPadre)
	{
		this.padre.add(nodoPadre);
	}
	public final void nuevoHijo(Nodo nodoHijo)
	{
		this.hijo.add(nodoHijo);
	}
	public final void intercambiarPadres()
	{
		Collections.swap(padre, 0, 1);
	}
	public final void eliminarUltimoPadre()
	{
		padre.remove(padre.size() - 1);
	}
	public void eliminarHijo(final Nodo nodoHijo)
	{
		boolean encontrado = false;
		for (int i = 0 ; i < hijo.size() && !encontrado ; ++i)
		{
			if (hijo.get(i).getPosicion().first == nodoHijo.getPosicion().first && hijo.get(i).getPosicion().second == nodoHijo.getPosicion().second)
			{
				std::swap(hijo.get(i),hijo.get(hijo.size() - 1));
				encontrado = true;
				hijo.remove(hijo.size() - 1);
			}
		}
	}
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: double getCosteTotal() const
	public final double getCosteTotal()
	{
		return costeTotal;
	}
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: double getPenalizacion() const
	public final double getPenalizacion()
	{
		return penalizacion;
	}
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: const coordenada& getPosicion() const
	public final Pair getPosicion()
	{
		return posicion;
	}
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: STLVector<Nodo*> getHijo() const
	public final ArrayList<Nodo> getHijo()
	{
		return new ArrayList<Nodo>(hijo);
	}
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: STLVector<Nodo*> getPadre() const
	public final ArrayList<Nodo> getPadre()
	{
		return new ArrayList<Nodo>(padre);
	}
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: double getCosteDesdeInicioHastaMi() const
	public final double getCosteDesdeInicioHastaMi()
	{
		return costeDesdeInicioHastaMi;
	}
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: double getCosteDesdeMiHastaLaMeta() const
	public final double getCosteDesdeMiHastaLaMeta()
	{
		return costeDesdeMiHastaLaMeta;
	}
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: double getCosteDeDesplazarseDesdePadreHastaMi() const
	public final double getCosteDeDesplazarseDesdePadreHastaMi()
	{
		return costeDeDesplazarseDesdePadreHastaMi;
	}
	public void setCosteTotal(final double costeTotal)
	{
		this.costeTotal = costeTotal;
	}
	public void setcosteDeDesplazarseDesdePadreHastaMi(final double coste)
	{
		this.costeDeDesplazarseDesdePadreHastaMi = coste;
	}
	public void setCosteDesdeInicioHastaMi(final double costeDesdeInicioHastaMi)
	{
		this.costeDesdeInicioHastaMi = costeDesdeInicioHastaMi;
	}
}
