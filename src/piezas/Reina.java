package piezas;

import tablero.Casilla;
import tablero.Posicion;
import tablero.Tablero;
import tablero.TableroControlador;

public class Reina extends Pieza {

	public Reina(boolean b, int y, int x) {
		this.esBlanca = b;
		if (esBlanca)
			this.IMAGEN = "wq.gif";
		else
			this.IMAGEN = "bq.gif";
		pos = new Posicion(x, y);
	}

	public String toString() {
		return "Reina";
	}

	@Override
	public boolean esMovimientoValido(Posicion posicion,
			TableroControlador tableromodel, Tablero tablero) {

		// if(estaMiReyEnJaque(tableromodel))
		// return false;

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

		// MOVIMIENTOS DE ALFIL
		if (Math.abs(xTo - xFr) == Math.abs(yTo - yFr)) {
			int x1, y1, x2, y2, incX, incY;
			if (xTo > xFr) {
				x1 = xFr + 1;
				x2 = xTo;
				incX = 1;
			} else {
				x1 = xFr - 1;
				x2 = xTo;
				incX = -1;
			}
			if (yTo > yFr) {
				y1 = yFr + 1;
				y2 = yTo;
				incY = 1;
			} else {
				y1 = yFr - 1;
				y2 = yTo;
				incY = -1;
			}
			for (; y2 != y1 && x2 != x1; x1 = x1 + incX, y1 = y1 + incY)
				if (tableromodel.getCasilla(x1, y1).getPieza() != null)
					return false;
			return true;
		}

		// MOVIMIENTOS DE TORRE
		if ((yFr - yTo) == 0) { // Movimiento en X
			int i, j;
			if (xTo > xFr) {
				j = xTo - 1;
				i = xFr + 1;
			} else {
				j = xFr - 1;
				i = xTo + 1;
			}
			for (; i < j; i++)
				if (tableromodel.getCasilla(i, yFr).getPieza() != null)
					return false;
			return true;
		}

		if ((xFr - xTo) == 0) { // Movimiento en Y
			int i, j;
			if (yTo > yFr) {
				j = yTo - 1;
				i = yFr + 1;
			} else {
				j = yFr - 1;
				i = yTo + 1;
			}
			for (; i < j; i++)
				if (tableromodel.getCasilla(xFr, i).getPieza() != null)
					return false;
			return true;
		}

		return false;
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
					if (!quedoElReyEnJaque(x + 1, y - 1, tableroModel, miRey, tablero))
						return true;

			if (x - 1 >= 0) {
				if (tableroModel.getCasilla(x - 1, y - 1).getPieza() == null
						|| tableroModel.getCasilla(x - 1, y - 1).getPieza()
								.esBlanca() != esBlanca())
					if (!quedoElReyEnJaque(x - 1, y - 1, tableroModel, miRey, tablero))
						return true;

				if (tableroModel.getCasilla(x, y - 1).getPieza() == null
						|| tableroModel.getCasilla(x, y - 1).getPieza()
								.esBlanca() != esBlanca())
					if (!quedoElReyEnJaque(x, y - 1, tableroModel, miRey, tablero))
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
					if (!quedoElReyEnJaque(x - 1, y + 1, tableroModel, miRey, tablero))
						return true;

				if (tableroModel.getCasilla(x, y + 1).getPieza() == null
						|| tableroModel.getCasilla(x, y + 1).getPieza()
								.esBlanca() != esBlanca())
					if (!quedoElReyEnJaque(x, y + 1, tableroModel, miRey, tablero))
						return true;
			}
		}
		// y = 0
		if (x + 1 <= 7)
			if (tableroModel.getCasilla(x + 1, y).getPieza() == null
					|| tableroModel.getCasilla(x + 1, y).getPieza().esBlanca() != esBlanca())
				if (!quedoElReyEnJaque(x + 1, y, tableroModel, miRey, tablero))
					return true;
		if (x - 1 >= 0)
			if (tableroModel.getCasilla(x - 1, y).getPieza() == null
					|| tableroModel.getCasilla(x - 1, y).getPieza().esBlanca() != esBlanca())
				if (!quedoElReyEnJaque(x - 1, y, tableroModel, miRey, tablero))
					return true;

		return false;
	}
}
