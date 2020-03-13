package star;

public class Pair<T1, T2> {
	private int _first;
	private int _second;

	public Pair(int first, int second) {
		_first = first;
		_second = second;
	}

	public int getFirst() {
		return _first;
	}

	public int getSecond() {
		return _second;
	}

}




//public class GlobalMembers
//{
//		public static void reconstruirElCamino()
//		{
//			coordenadas recorridoInverso = new coordenadas();
//			Nodo nodoActual = redDeNodos[redDeNodos.size() - 1];
//			while (nodoActual.getPadre()[0] != null)
//			{
//				recorridoInverso.push_back(nodoActual.getPadre()[0].getPosicion());
//				nodoActual = nodoActual.getPadre()[0];
//			}
//			if (ejecutarPorPasos)
//			{
//				for (int i = recorridoInverso.size() - 1 ; i > -1 ; --i)
//				{
//					rejillaObjetivo.setElementoInterno(C_RECORRIDO,recorridoInverso[i]);
//					System.out.print(rejillaObjetivo);
//					System.out.print("\n");
//					System.out.print("PRESIONA 'ENTER' PARA CONTINUAR.");
//					System.out.print("\n");
//					System.out.print("\n");
//					System.out.print("-----------------------------------------------");
//					System.out.print("\n");
//					System.in.read()
//				}
//			}
//			else
//			{
//				for (int i = recorridoInverso.size() - 1 ; i > -1 ; --i)
//				{
//					rejillaObjetivo.setElementoInterno(C_RECORRIDO,recorridoInverso[i]);
//				}
//			}
//			rejillaObjetivo.setElementoInterno(C_SALIDA,rejillaObjetivo.getSalida());
//			for (coordenada meta : rejillaObjetivo.getMetas())
//			{
//				rejillaObjetivo.setElementoInterno(C_META,meta);
//			}
//			for (coordenada puntoDePaso : rejillaObjetivo.getPuntosDePaso())
//			{
//				rejillaObjetivo.setElementoInterno(C_PUNTO_DE_PASO,puntoDePaso);
//			}
//			System.out.print(rejillaObjetivo);
//			System.out.print("\n");
//		}
//
////C++ TO JAVA CONVERTER WARNING: The following constructor is declared outside of its associated class:
//		public static aEstrella(rejilla r, boolean pasos)
//		{
//			this.solucionEncontrada = false;
//			this.problemaInsatisfactible = false;
//			this.rejillaObjetivo = r;
//			this.ejecutarPorPasos = pasos;
//		}
//		public final void close()
//		{
//			for (Nodo nodo : vectorDeReciclaje)
//			{
//				if (nodo != null)
//				{
//				nodo.close();
//				}
//			}
//		}
//		public static final void resolverConPuntosDePaso()
//		{
//			double costeTotalDelRecorrido = 0;
//			coordenadas metasIniciales = rejillaObjetivo.getMetas();
//			for (int i = 0; i < rejillaObjetivo.getPuntosDePaso().size() && !problemaInsatisfactible ; ++i)
//			{
//				rejillaObjetivo.setMetas({rejillaObjetivo.getPuntosDePaso()[i]});
//				resolver();
//				costeTotalDelRecorrido += redDeNodos[redDeNodos.size() - 1].getCosteTotal();
//				if (solucionEncontrada)
//				{
//					solucionEncontrada = false;
//				}
//				while (!listaAbierta.empty())
//				{
//					listaAbierta.pop();
//				}
//				while (!redDeNodos.empty())
//				{
//					redDeNodos.pop_back();
//				}
//				rejillaObjetivo.setSalida(rejillaObjetivo.getPuntosDePaso [i]);
//			}
//			if (!problemaInsatisfactible)
//			{
//				rejillaObjetivo.setMetas(metasIniciales);
//				resolver();
//				if (!problemaInsatisfactible)
//				{
//					costeTotalDelRecorrido += redDeNodos[redDeNodos.size() - 1].getCosteTotal();
//					System.out.print("EL COSTE TOTAL DEL RECORRIDO ES: ");
//					System.out.print(costeTotalDelRecorrido);
//					System.out.print("\n");
//					System.out.print("\n");
//				}
//			}
//		}
//		public static final void resolver()
//		{
//			Nodo inicial = new Nodo(null,rejillaObjetivo.getSalida(),0,0,rejillaObjetivo.getMetas());
//			listaAbierta.push(inicial);
//			vectorDeReciclaje.push_back(inicial);
//			while (!solucionEncontrada && !problemaInsatisfactible)
//			{
//				if (!listaAbierta.empty())
//				{
//					Nodo nodoActual = listaAbierta.top();
//					listaAbierta.pop();
//					redDeNodos.push_back(nodoActual);
//					for (coordenada c : rejillaObjetivo.getMetas())
//					{
//						if (nodoActual.getPosicion().first == c.first && nodoActual.getPosicion().second == c.second)
//						{
//							solucionEncontrada = true;
//						}
//					}
//					if (!solucionEncontrada)
//					{
//						expandir(nodoActual);
//					}
//				}
//				else
//				{
//					problemaInsatisfactible = true;
//				}
//			}
//			if (!problemaInsatisfactible)
//			{
//				reconstruirElCamino();
//				System.out.print("EL COSTE DEL RECORRIDO ES: ");
//				System.out.print(redDeNodos[redDeNodos.size() - 1].getCosteTotal());
//				System.out.print("\n");
//				System.out.print("\n");
//			}
//			else
//			{
//				System.out.print("EL PROBLEMA ES INSATISFACTIBLE.");
//				System.out.print("\n");
//				System.out.print("\n");
//			}
//		}
//}
//}