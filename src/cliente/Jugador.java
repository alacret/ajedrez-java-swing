package cliente;

import java.util.ArrayList;
import java.util.List;

import piezas.Pieza;
import piezas.Rey;
import tablero.Casilla;
import tablero.Tablero;
import tablero.TableroControlador;

public class Jugador {

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Jugador))
			return false;
		return ((Jugador) obj).esBlanco() == this.esBlanco();
	}

	/**
	 * @uml.property  name="esBlanco"
	 */
	private boolean esBlanco;	
	/**
	 * @uml.property  name="miRey"
	 * @uml.associationEnd  
	 */
	private Rey miRey;

	public Jugador(boolean b) {
		this.esBlanco = b;
	}

	public Jugador() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean esBlanco(){
		return esBlanco;
	}
	
	public String toString(){
		return esBlanco ? "Jugador Blanco" : "Jugador Negro";
	}

	public boolean estoyAhogado(TableroControlador tableroModel, Tablero tablero) {
		
		List<Pieza> misPiezas = new ArrayList<Pieza>();
		
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++) {
				Casilla casilla = tableroModel.getCasilla(i, j);
				Pieza pieza = casilla.getPieza();				
				if (pieza != null)
					if (pieza.esBlanca() == esBlanco())
						misPiezas.add(pieza);
			}
		
		for (Pieza pieza : misPiezas)
			if(pieza.puedeMoverse(tableroModel,miRey,tablero))
				return false;
		
		return true;
	}

	/**
	 * @param miRey
	 * @uml.property  name="miRey"
	 */
	public void setMiRey(Rey miRey) {
		this.miRey = miRey;
	}

	/**
	 * @return
	 * @uml.property  name="miRey"
	 */
	public Rey getMiRey() {
		return miRey;
	}

}
