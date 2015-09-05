package piezas;

import tablero.Casilla;
import tablero.Posicion;
import tablero.Tablero;
import tablero.TableroControlador;

public class Caballo extends Pieza {

	public Caballo(boolean b, int y, int x) {
		this.esBlanca = b;
		if(esBlanca)
			this.IMAGEN = "wn.gif";
		else
			this.IMAGEN = "bn.gif";
		pos = new Posicion(x, y);	
	}
	
	public String toString(){
		return "Caballo"; 
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
		
		if(Math.abs(xTo - xFr) == 2 && Math.abs(yTo - yFr) == 1)
			return true;
		
		if(Math.abs(yTo - yFr) == 2 && Math.abs(xTo - xFr) == 1)
			return true;
		
		return false;
	}

	@Override
	public boolean puedeMoverse(TableroControlador tableroModel, Rey miRey, Tablero tablero) {
		int y = pos.getY();
		int x = pos.getX();
	
		if(x + 2 <=7){
			if(y + 1 <= 7)
				if(tableroModel.getCasilla(x+2,y+1).getPieza() == null || tableroModel.getCasilla(x+2,y+1).getPieza().esBlanca() != esBlanca())
					if(!quedoElReyEnJaque(x+2,y+1,tableroModel,miRey, tablero))
							return true;

			if(y - 1 <= 7)
				if(tableroModel.getCasilla(x+2,y-1).getPieza() == null || tableroModel.getCasilla(x+2,y-1).getPieza().esBlanca() != esBlanca())
					if(!quedoElReyEnJaque(x+2,y-1,tableroModel,miRey,tablero))
							return true;
		}
	
		if(x - 2 >=0){
			if(y + 1 <= 7)
				if(tableroModel.getCasilla(x-2,y+1).getPieza() == null || tableroModel.getCasilla(x-2,y+1).getPieza().esBlanca() != esBlanca())
					if(!quedoElReyEnJaque(x-2,y+1,tableroModel,miRey, tablero))
							return true;

			if(y - 1 <= 7)
				if(tableroModel.getCasilla(x-2,y-1).getPieza() == null || tableroModel.getCasilla(x-2,y-1).getPieza().esBlanca() != esBlanca())
					if(!quedoElReyEnJaque(x-2,y-1,tableroModel,miRey, tablero))
							return true;
		}
		
	
		if(y + 2 <=7){
			if(x + 1 <= 7)
				if(tableroModel.getCasilla(x+1,y+2).getPieza() == null || tableroModel.getCasilla(x+1,y+2).getPieza().esBlanca() != esBlanca())
					if(!quedoElReyEnJaque(x+1,y+2,tableroModel,miRey, tablero))
							return true;

			if(x - 1 <= 7)
				if(tableroModel.getCasilla(x-1,y+2).getPieza() == null || tableroModel.getCasilla(x-1,y+2).getPieza().esBlanca() != esBlanca())
					if(!quedoElReyEnJaque(x-1,y+2,tableroModel,miRey, tablero))
							return true;
		}
	
		if(y - 2 >=0){
			if(x + 1 <= 7)
				if(tableroModel.getCasilla(x+1,y-2).getPieza() == null || tableroModel.getCasilla(x+1,y-2).getPieza().esBlanca() != esBlanca())
					if(!quedoElReyEnJaque(x+1,y-2,tableroModel,miRey, tablero))
							return true;

			if(x - 1 <= 7)
				if(tableroModel.getCasilla(x-1,y-2).getPieza() == null || tableroModel.getCasilla(x-1,y-2).getPieza().esBlanca() != esBlanca())
					if(!quedoElReyEnJaque(x-1,y-2,tableroModel,miRey, tablero))
							return true;
		}

		
		return false;
	}
}
