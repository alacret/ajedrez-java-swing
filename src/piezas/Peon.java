package piezas;

import tablero.Casilla;
import tablero.Posicion;
import tablero.Tablero;
import tablero.TableroControlador;

public class Peon extends Pieza {

	public Peon(boolean b, int y, int x) {
		this.esBlanca = b;
		if (esBlanca)
			this.IMAGEN = "wp.gif";
		else
			this.IMAGEN = "bp.gif";
		pos = new Posicion(x, y);
	}

	public String toString() {
		return "Peon";
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
		
		
		int mov = esBlanca ? -1 : 1;
		
		Casilla casillaTo = tableromodel.getCasilla(xTo, yTo);

		int yFr = pos.getY();
		int xFr = pos.getX();

		if ((xFr == xTo && (yFr + mov) == yTo) && casillaTo.getPieza() == null)
			return true;

		if (Math.abs(xFr - xTo) == 1 && (yTo - yFr) == mov
				&& casillaTo.getPieza() != null)
			return true;

		if (((mov * -2.5) + 3.5) == yFr && Math.abs(yFr - yTo) == 2 && (xFr - xTo) == 0)
			return true;

		return false;
	}

	@Override
	public boolean puedeMoverse(TableroControlador tableroModel, Rey miRey, Tablero tablero) {
		int y = pos.getY();
		int x = pos.getX();
		
		int mov = esBlanca() ? -1 : 1;
		
		//  y -1
		if(y + mov >= 0 && y + mov <= 7)
			if(tableroModel.getCasilla(x,y+mov).getPieza() == null)
				if(!quedoElReyEnJaque(x,y+mov,tableroModel,miRey, tablero))
					return true;
		
		if(x - 1 >= 0)
			if(tableroModel.getCasilla(x-1,y+mov).getPieza() != null && tableroModel.getCasilla(x-1,y+mov).getPieza().esBlanca() != esBlanca())
				if(!quedoElReyEnJaque(x-1,y+mov,tableroModel,miRey, tablero))
					return true;
		
		if(x + 1 <= 7)
			if(tableroModel.getCasilla(x+1,y+mov).getPieza() != null && tableroModel.getCasilla(x+1,y+mov).getPieza().esBlanca() != esBlanca())
				if(!quedoElReyEnJaque(x+1,y+mov,tableroModel,miRey, tablero))
					return true;		

		
		return false;
	}
}