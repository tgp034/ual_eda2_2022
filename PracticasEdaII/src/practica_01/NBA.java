package practica_01;

import java.io.*;
import java.util.*;

public class NBA {

	public static void main(String[] arg) throws IOException {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String nombre;

		ArrayList<Jugador> nba = new ArrayList<Jugador>();

		// Apertura del fichero y creacion de BufferedReader para poder
		// hacer una lectura comoda (disponer del metodo readLine()).
		archivo = new File(
				"C:\\Users\\motal\\OneDrive\\Escritorio\\Universidad\\Segundo\\EDA II\\Prácticas\\Practica_01\\NbaStats.csv");
		fr = new FileReader(archivo);
		br = new BufferedReader(fr);

		// Lectura del fichero
		String linea;
		linea = br.readLine();
		Jugador aux = new Jugador("", "", "", 0, 0);
		// Año(1), nombre(2), posicion(4), equipo(6), porcentaje(7), puntuacion(8)

		while ((linea = br.readLine()) != null) {
			String[] separado = linea.split(";");

			for (int i = 0; i <= separado.length; i++) {
				switch (i) {

				case 2:
					nombre = separado[i];
					aux.setNombre(nombre);
					break;
				case 4:
					ArrayList<String> posiciones = new ArrayList<String>();
					posiciones.add(separado[i]);
					aux.setPosiciones(posiciones);
					break;
				case 6:
					ArrayList<String> equipos = new ArrayList<String>();
					equipos.add(separado[i]);
					aux.setEquipos(equipos);
					break;
				case 7:
					if (separado[i] == "")
						aux.setAciertos(0.0);
					else
						aux.setAciertos(Double.valueOf(separado[i]));
					break;
				case 8:
					aux.setPuntos(Integer.valueOf(separado[i]));
					break;
				default:
					break;

				}
			}
			
			Jugador nuevo = new Jugador(aux.getNombre(), aux.getPosiciones().get(0), aux.getEquipos().get(0),
					aux.getAciertos(), aux.getPuntos());
			if (nba.isEmpty())
				nba.add(nuevo);

			else if (nuevo.getNombre().equals(nba.get(nba.size() - 1).getNombre())) { // Compara si un jugador y otro
																						// tienen el mismo nombre
				Jugador ultimo = nba.get(nba.size() - 1);

				if (!ultimo.getPosiciones().contains(nuevo.getPosiciones().get(0))) { // Comparar las posiciones y si ya
																						// la contiene que no haga nada
					ArrayList<String> nuevasPos = ultimo.getPosiciones();
					nuevasPos.add(nuevo.getPosiciones().get(0));
					nuevo.setPosiciones(nuevasPos);
				} else {
					nuevo.setPosiciones(ultimo.getPosiciones());
				}

				if (!ultimo.getEquipos().contains(nuevo.getEquipos().get(0))) {
					ArrayList<String> nuevosEquipos = ultimo.getEquipos();
					nuevosEquipos.add(nuevo.getEquipos().get(0));
					nuevo.setEquipos(nuevosEquipos);
				} else {
					nuevo.setEquipos(ultimo.getEquipos());
				}
			} else {
				nuevo.setMedia();
				nba.add(nuevo);
			}

			nuevo.setMedia();
			nba.set(nba.size() - 1, nuevo);
		}

		quicksort(nba, 0, nba.size() - 1);
		// System.out.println(nba.toString());
		for (int i = nba.size() - 1; i > (nba.size() - 1) - 10; i--) {
			System.out.println(nba.get(i).toString());
		}
		br.close();
	}

	public static void quicksort(ArrayList<Jugador> NBA, int izq, int der) {

		Jugador pivote = NBA.get(izq); // tomamos primer elemento como pivote
		int i = izq; // i realiza la búsqueda de izquierda a derecha
		int j = der; // j realiza la búsqueda de derecha a izquierda
		Jugador aux;

		while (i < j) { // mientras no se crucen las búsquedas
			while (NBA.get(i).getMedia() <= pivote.getMedia() && i < j)
				i++; // busca elemento mayor que pivote
			while (NBA.get(j).getMedia() > pivote.getMedia())
				j--; // busca elemento menor que pivote
			if (i < j) { // si no se han cruzado
				aux = NBA.get(i); // los intercambia
				NBA.set(i, NBA.get(j));
				NBA.set(j, aux);
			}
		}

		NBA.set(izq, NBA.get(j)); // se coloca el pivote en su lugar de forma que tendremos
		NBA.set(j, pivote); // los menores a su izquierda y los mayores a su derecha

		if ((der - (j + 1) < 10)) { // comprobamos que el sublista derecha (valores mayores que el pivote) tiene al
									// menos 10 elementos
			if (izq < j - 1)
				quicksort(NBA, izq, j - 1); // ordenamos subarray izquierdo
		}

		if (j + 1 < der)
			quicksort(NBA, j + 1, der); // ordenamos subarray derecho

	}

}
