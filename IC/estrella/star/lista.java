package star;

public class lista {
	nodo primero, ultimo, aux;
	String nombreLista;

	/////// CONSTRUCTOR//////////////////////////////////////////////////////////
	public lista(String nombreLista) {
		this.primero = this.ultimo = null;
		this.nombreLista = nombreLista;
	}

	/////// METODO PARA AGREGAR
	/////// NODOS/////////////////////////////////////////////
	public void agregar(nodo nodin) {
		// para lista vacia
		if (primero == null) {
			primero = ultimo = nodin;
		}
		// en caso de mas nodos
		else {
			aux = primero;
			while (aux.siguiente != null) {
				aux = aux.siguiente;
			}
			aux.siguiente = nodin;
			ultimo = aux.siguiente;
		}
	}

	/////// METODO QUE ELIMINA EL PRIMERO DE LA LISTA Y LO
	/////// DEVUELVE//////////////
	public nodo eliminaPrimero() {
		nodo temp = primero;
		primero = primero.siguiente;
		return temp;
	}

	/////// METODO QUE ELIMINA EL NODO QUE LE
	/////// MANDES/////////////////////////////
	public nodo eliminar(nodo nodin) {
		nodo aux = primero;
		if ((nodin.cordX == primero.cordX) && (nodin.cordY == primero.cordY)) {
			return eliminaPrimero();
		}
		while (aux.siguiente != null) {
			if ((nodin.cordX == aux.siguiente.cordX) && (nodin.cordY == aux.siguiente.cordY)) {
				aux.siguiente = aux.siguiente.siguiente;
			} else
				aux = aux.siguiente;
		}
		return nodin;
	}

	/////// METODO QUE BUSCA EN LA LISTA EL NODO CON MENOR COSTO, LO
	/////// ELIMINA/////
	/////// Y LO
	/////// REGRESA/////////////////////////////////////////////////////////
	public nodo eliminaCostoMenor() {
		nodo aux = primero;
		nodo menor = primero;

		while (aux != null) {
			if (aux.costoF < menor.costoF)
				menor = aux;
			else
				aux = aux.siguiente;
		}

		return eliminar(menor);
	}

	////// METODO PARA DETERMINAR SI LA LISTA ESTA
	////// VACIA/////////////////////////
	public boolean esVacia() {
		return primero == null;
	}
}
