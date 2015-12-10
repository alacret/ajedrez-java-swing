 package piezas;

import tablero.Casilla;
import tablero.Posicion;
import tablero.Tablero;
import tablero.TableroControlador;

public abstract class Pieza {
	/**
	 * @uml.property  name="iMAGEN"
	 */
	protected String IMAGEN;
	/**
	 * @uml.property  name="esBlanca"
	 */
	protected boolean esBlanca;
	/**
	 * @uml.property  name="pos"
	 * @uml.associationEnd  
	 */
	protected Posicion pos;

	public String getRutaImagen() {
		return IMAGEN;
	}

	public String toString() {
		return "";
	}

	public Posicion getPosicion() {
		return this.pos;
	}

	abstract public boolean esMovimientoValido(Posicion posicion,
			TableroControlador tableromodel, Tablero tablero);

	public boolean esBlanca() {
		return esBlanca;
	}

	public boolean isBlanca() {
		return esBlanca;
	}

	public void setPosicion(Posicion posicionHasta) {
		this.pos = posicionHasta;

	}

	abstract public boolean puedeMoverse(TableroControlador tableroModel, Rey miRey, Tablero tablero);

	protected boolean quedoElReyEnJaque(int x, int y, TableroControlador tableroModel,
			Rey miRey, Tablero tablero) {

		Casilla casillaDesde = tableroModel.getCasilla(pos.getX(), pos.getY());
		Casilla casillaHasta = tableroModel.getCasilla(x, y);
		tableroModel.mover(casillaDesde, new Posicion(x, y), casillaHasta);

		if (miRey.estoyEnJaque(tableroModel, tablero)) {
			tableroModel.devolverJugada();
			return true;
		}
		tableroModel.devolverJugada();
		return false;
	}
}
