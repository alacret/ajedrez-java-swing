package piezas;

import tablero.Casilla;
import tablero.Posicion;
import tablero.Tablero;
import tablero.TableroControlador;

public class Torre extends Pieza {

	public Torre(boolean b, int y, int x) {
		this.esBlanca = b;
		if (esBlanca)
			this.IMAGEN = "wr.gif";
		else
			this.IMAGEN = "br.gif";

		pos = new Posicion(x, y);
	}

	public String toString() {
		return "Torre";
	}

	@Override
	public boolean esMovimientoValido(Posicion posicion,
			TableroControlador tableroModel, Tablero tablero) {

		int xTo = posicion.getX();
		int yTo = posicion.getY();
		
		Casilla casillaHasta = tableroModel.getCasilla(xTo,yTo);
		Pieza pieza2 = casillaHasta.getPieza();

		if (pieza2 != null) {
			if (esBlanca() == pieza2.esBlanca()) {
				tablero.mensaje("Pieza de tu mismo color...");
				return false;
			}
		}
		

		int yFr = pos.getY();
		int xFr = pos.getX();

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
				if (tableroModel.getCasilla(i, yFr).getPieza() != null)
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
				if (tableroModel.getCasilla(xFr, i).getPieza() != null)
					return false;
			return true;
		}
		return false;
	}

	@Override
	public boolean puedeMoverse(TableroControlador tableroModel, Rey miRey, Tablero tablero) {
		int y = pos.getY();
		int x = pos.getX();
		
		if(y - 1 >= 0 && (tableroModel.getCasilla(x,y-1).getPieza() == null || tableroModel.getCasilla(x,y-1).getPieza().esBlanca() != esBlanca()))
			if(!quedoElReyEnJaque(x,y-1,tableroModel,miRey,tablero))
				return true;
		
		if(y + 1 <= 7 && (tableroModel.getCasilla(x,y+1).getPieza() == null || tableroModel.getCasilla(x,y+1).getPieza().esBlanca() != esBlanca()))
			if(!quedoElReyEnJaque(x,y+1,tableroModel,miRey, tablero))
				return true;
		
		if(x - 1 >= 0 && (tableroModel.getCasilla(x-1,y).getPieza() == null || tableroModel.getCasilla(x-1,y+1).getPieza().esBlanca() != esBlanca()))
			if(!quedoElReyEnJaque(x-1,y,tableroModel,miRey, tablero))
				return true;
		
		if(x + 1 <= 0 && (tableroModel.getCasilla(x+1,y).getPieza() == null || tableroModel.getCasilla(x+1,y).getPieza().esBlanca() != esBlanca()))
			if(!quedoElReyEnJaque(x+1,y,tableroModel,miRey,tablero))
				return true;
		
		return false;
	}
}