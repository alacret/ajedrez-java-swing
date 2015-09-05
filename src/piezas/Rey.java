package piezas;

import java.util.ArrayList;
import java.util.List;

import tablero.Casilla;
import tablero.Posicion;
import tablero.Tablero;
import tablero.TableroControlador;

public class Rey extends Pieza {

	/**
	 * @uml.property  name="piezasDeJaque"
	 */
	private List<Pieza> piezasDeJaque = new ArrayList<Pieza>();
	/**
	 * @uml.property  name="esJaque"
	 */
	private boolean esJaque;

	public Rey(boolean b, int y, int x) {
		this.esBlanca = b;
		if (esBlanca)
			this.IMAGEN = "wk.gif";
		else
			this.IMAGEN = "bk.gif";
		pos = new Posicion(x, y);
	}

	public String toString() {
		return "Rey";
	}

	@Override
	public boolean esMovimientoValido(Posicion posicion,
			TableroControlador tableromodel, Tablero tablero) {

		int xTo = posicion.getX();
		int yTo = posicion.getY();
		
		Casilla casillaHasta = tableromodel.getCasilla(xTo,yTo);
		Pieza pieza2 = casillaHasta.getPieza();

		if (pieza2 != null) {
			if (esBlanca() == pieza2.esBlanca()) {
				tablero.mensaje("Pieza de tu mismo color...");
				return false;
			}
		}
		

		int yFr = pos.getY();
		int xFr = pos.getX();

		if (Math.abs(xFr - xTo) == 1 && (yFr - yTo) == 0
				|| Math.abs(xFr - xTo) == 1 && Math.abs(yFr - yTo) == 1
				|| (xFr - xTo) == 0 && Math.abs(yFr - yTo) == 1)
			return true;

		return false;
	}

	public boolean estoyEnJaque(TableroControlador tableroModel, Tablero tablero) {

		for (int i = 0; i <= 7; i++)
			for (int j = 0; j <= 7; j++) {
				Casilla casilla = tableroModel.getCasilla(i, j);
				Pieza pieza = casilla.getPieza();
				if (pieza != null && !pieza.equals(this))
					if (pieza.esBlanca() != esBlanca()) {
						boolean esMovimientoValido = pieza.esMovimientoValido(pos, tableroModel, tablero);
						if (esMovimientoValido) {
							return true;
						}
						
					}
			}
		return false;
	}

	public List<Pieza> getPiezasDeJaque() {
		return piezasDeJaque;
	}

	public void setEstaEnJaque(boolean b) {
		this.esJaque = b;

	}

	public boolean enJaque() {
		return this.esJaque;
	}

	@Override
	public boolean puedeMoverse(TableroControlador tableroModel, Rey miRey, Tablero tablero) {
		int y = pos.getY();
		int x = pos.getX();
		// y -1
		if (y - 1 >= 0) {
			if (x + 1 <= 7)
				if (tableroModel.getCasilla(x + 1, y - 1).getPieza() == null
						|| tableroModel.getCasilla(x + 1, y - 1).getPieza()
								.esBlanca() != esBlanca())
					if (!quedoElReyEnJaque(x + 1, y - 1, tableroModel, miRey,tablero))
						return true;

			if (x - 1 >= 0) {
				if (tableroModel.getCasilla(x - 1, y - 1).getPieza() == null
						|| tableroModel.getCasilla(x - 1, y - 1).getPieza()
								.esBlanca() != esBlanca())
					if (!quedoElReyEnJaque(x - 1, y - 1, tableroModel, miRey,tablero))
						return true;

				if (tableroModel.getCasilla(x, y - 1).getPieza() == null
						|| tableroModel.getCasilla(x, y - 1).getPieza()
								.esBlanca() != esBlanca())
					if (!quedoElReyEnJaque(x, y - 1, tableroModel, miRey,tablero))
						return true;
			}
		}

		// y +1
		if (y + 1 <= 7) {
			if (x + 1 <= 7)
				if (tableroModel.getCasilla(x + 1, y + 1).getPieza() == null
						|| tableroModel.getCasilla(x + 1, y + 1).getPieza()
								.esBlanca() != esBlanca())
					if (!quedoElReyEnJaque(x + 1, y + 1, tableroModel, miRey, tablero))
						return true;

			if (x - 1 >= 0) {
				if (tableroModel.getCasilla(x - 1, y + 1).getPieza() == null
						|| tableroModel.getCasilla(x - 1, y + 1).getPieza()
								.esBlanca() != esBlanca())
					if (!quedoElReyEnJaque(x - 1, y + 1, tableroModel, miRey,tablero))
						return true;

				if (tableroModel.getCasilla(x, y + 1).getPieza() == null
						|| tableroModel.getCasilla(x, y + 1).getPieza()
								.esBlanca() != esBlanca())
					if (!quedoElReyEnJaque(x, y + 1, tableroModel, miRey,tablero))
						return true;
			}
		}
		// y = 0
		if (x + 1 <= 7)
			if (tableroModel.getCasilla(x + 1, y).getPieza() == null
					|| tableroModel.getCasilla(x + 1, y).getPieza().esBlanca() != esBlanca())
				if (!quedoElReyEnJaque(x + 1, y, tableroModel, miRey,tablero))
					return true;
		if (x - 1 >= 0)
			if (tableroModel.getCasilla(x - 1, y).getPieza() == null
					|| tableroModel.getCasilla(x - 1, y).getPieza().esBlanca() != esBlanca())
				if (!quedoElReyEnJaque(x - 1, y, tableroModel, miRey,tablero))
					return true;

		return false;
	}
}
