package practica_1;

import java.util.ArrayList;

public class Jugador {

private String nombre;
    
    private ArrayList<String> equipos;
    private ArrayList<String> posiciones;

    //Cosas para hacer los scores anuales 
    private int puntos;
    private double aciertos;
    private double scoreAnual;
    private ArrayList<String> scoresAnuales;
    
    //Media de los scores anuales
    private double mediaTotal;

    //Contrsuctor usando los datos que leeremos del archivo
    public Jugador(String nombre, String posicion, String equipo, double acierto,  int puntos){
        this.nombre = nombre;
        
        this.equipos = new ArrayList<String>();
        this.equipos.add(equipo);
        
        this.posiciones = new ArrayList<String>();
        this.posiciones.add(posicion);
        
        this.aciertos = acierto;
        this.puntos = puntos;
        this.scoreAnual = acierto*puntos/100;
        
        this.scoresAnuales = new ArrayList<String>();
        scoresAnuales.add(String.valueOf(scoreAnual));
        
    }
    
    //Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<String> equipos) {
        this.equipos = equipos;
    }

    public ArrayList<String> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(ArrayList<String> posiciones) {
        this.posiciones = posiciones;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int punt) {
        this.puntos = punt;
    }

    public double getMedia() {
        return mediaTotal;
    }

    public ArrayList<String> getScoresAnuales() {
		return scoresAnuales;
	}

	public void setScoresAnuales(ArrayList<String> scoresAnuales) {
		this.scoresAnuales = scoresAnuales;
	}

	public double getAciertos() {
        return aciertos;
    }

    public void setAciertos(double aciertos) {
        this.aciertos = aciertos;
    }
    
    //metodo para hallar la media del jugador
    public void setMedia() {
        double suma = 0;
        for(int i = 0; i < this.scoresAnuales.size(); i++) {
            suma += Double.parseDouble(scoresAnuales.get(i)); 
        }
        this.mediaTotal = (suma / this.scoresAnuales.size());
    }
    
    @Override
    public String toString() {
        return this.nombre + ": \n Posiciones: " + this.posiciones.toString() 
        +"\t Equipos: "+ this.equipos.toString()+"\n Media:"+ this.mediaTotal + "\n\n";
    }
}
