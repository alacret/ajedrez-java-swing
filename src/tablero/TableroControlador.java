package tablero;

import javax.swing.table.AbstractTableModel;

import cliente.Jugador;

import piezas.Alfil;
import piezas.Caballo;
import piezas.Peon;
import piezas.Pieza;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;

public class TableroControlador extends AbstractTableModel {
	/**
	 * @return
	 * @uml.property  name="reyNegro"
	 */
	public Rey getReyNegro() {
		return reyNegro;
	}

	/**
	 * @return
	 * @uml.property  name="reyBlanco"
	 */
	public Rey getReyBlanco() {
		return reyBlanco;
	}

	private static final long serialVersionUID = 1L;
	/**
	 * @uml.property  name="columnNames" multiplicity="(0 -1)" dimension="1"
	 */
	private String[] columnNames = { "A", "B", "C", "D", "E", "F", "G", "H" };
	/**
	 * @uml.property  name="casillas" multiplicity="(0 -1)" dimension="2"
	 */
	private Casilla[][] casillas;
	/**
	 * @uml.property  name="reyNegro"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Rey reyNegro;
	/**
	 * @uml.property  name="reyBlanco"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Rey reyBlanco;
	//private Posicion posicionDevolver;
	//private Pieza piezaDevolver;
	//private Casilla casillaDesdeLocal;
	/**
	 * @uml.property  name="casillaHastaLocal"
	 * @uml.associationEnd  
	 */
	private Casilla casillaHastaLocal;
	/**
	 * @uml.property  name="piezaDesdeLocal"
	 * @uml.associationEnd  
	 */
	private Pieza piezaDesdeLocal;
	/**
	 * @uml.property  name="piezaHastaLocal"
	 * @uml.associationEnd  
	 */
	private Pieza piezaHastaLocal;
	/**
	 * @uml.property  name="posicionDesdeLocal"
	 * @uml.associationEnd  
	 */
	private Posicion posicionDesdeLocal;
	/**
	 * @uml.property  name="posicionHastaLocal"
	 * @uml.associationEnd  
	 */
	private Posicion posicionHastaLocal;

	public TableroControlador() {
		casillas = new Casilla[8][8];
		int i = 0, j = 0;
		casillas[i][j] = new Casilla(new Torre(false, i, j++));
		casillas[i][j] = new Casilla(new Caballo(false, i, j++));
		casillas[i][j] = new Casilla(new Alfil(false, i, j++));
		casillas[i][j] = new Casilla(new Reina(false, i, j++));
		casillas[i][j] = new Casilla(new Rey(false, i, j++));
		casillas[i][j] = new Casilla(new Alfil(false, i, j++));
		casillas[i][j] = new Casilla(new Caballo(false, i, j++));
		casillas[i][j] = new Casilla(new Torre(false, i, j++));
		i++;
		j = 0;
		casillas[i][j] = new Casilla(new Peon(false, i, j++));
		casillas[i][j] = new Casilla(new Peon(false, i, j++));
		casillas[i][j] = new Casilla(new Peon(false, i, j++));
		casillas[i][j] = new Casilla(new Peon(false, i, j++));
		casillas[i][j] = new Casilla(new Peon(false, i, j++));
		casillas[i][j] = new Casilla(new Peon(false, i, j++));
		casillas[i][j] = new Casilla(new Peon(false, i, j++));
		casillas[i][j] = new Casilla(new Peon(false, i, j++));
		i++;
		j = 0;
		for (; i < 6; i++) {
			casillas[i][0] = new Casilla();
			casillas[i][1] = new Casilla();
			casillas[i][2] = new Casilla();
			casillas[i][3] = new Casilla();
			casillas[i][4] = new Casilla();
			casillas[i][5] = new Casilla();
			casillas[i][6] = new Casilla();
			casillas[i][7] = new Casilla();
		}
		casillas[i][j] = new Casilla(new Peon(true, i, j++));
		casillas[i][j] = new Casilla(new Peon(true, i, j++));
		casillas[i][j] = new Casilla(new Peon(true, i, j++));
		casillas[i][j] = new Casilla(new Peon(true, i, j++));
		casillas[i][j] = new Casilla(new Peon(true, i, j++));
		casillas[i][j] = new Casilla(new Peon(true, i, j++));
		casillas[i][j] = new Casilla(new Peon(true, i, j++));
		casillas[i][j] = new Casilla(new Peon(true, i, j++));
		i++;
		j = 0;
		casillas[i][j] = new Casilla(new Torre(true, i, j++));
		casillas[i][j] = new Casilla(new Caballo(true, i, j++));
		casillas[i][j] = new Casilla(new Alfil(true, i, j++));
		casillas[i][j] = new Casilla(new Reina(true, i, j++));
		casillas[i][j] = new Casilla(new Rey(true, i, j++));
		casillas[i][j] = new Casilla(new Alfil(true, i, j++));
		casillas[i][j] = new Casilla(new Caballo(true, i, j++));
		casillas[i][j] = new Casilla(new Torre(true, i, j++));

		reyNegro = (Rey) getCasilla(4, 0).getPieza();
		reyBlanco = (Rey) getCasilla(4, 7).getPieza();
		

//		for (int w = 0; w <= 7; w++)
//			for (int x = 0; x <= 7; x++) {
//				Casilla casilla = getCasilla(w, x);
//				System.out.println("vaciando: " + w  +"-"+ x );
//				casilla.vaciar();
//			}
		
		// READY TO GO 1
		
//		casillas[3][0] = new Casilla(new Torre(true, 3, 0));
//		casillas[1][1] = new Casilla(new Torre(true, 1, 0));
//		casillas[7][6] = new Casilla(new Rey(true, 7,6));
//		casillas[7][3] = new Casilla(new Reina(true, 7,3));
//		casillas[3][5] = new Casilla(new Peon(true, 3,5));
//		casillas[3][6] = new Casilla(new Peon(true, 3,6));
//		
//		casillas[2][4] = new Casilla(new Rey(false, 2,4));
//		casillas[1][5] = new Casilla(new Peon(false, 1,5));
//		
//		reyNegro = (Rey) getCasilla(4, 2).getPieza();
//		reyBlanco = (Rey) getCasilla(6, 7).getPieza();	
		

		// READY TO GO 2
//		casillas[7][2] = new Casilla(new Rey(true, 7,2));
//		casillas[4][2] = new Casilla(new Reina(true, 4,2));
//		casillas[4][5] = new Casilla(new Peon(true, 4,5));
//		
//		casillas[5][0] = new Casilla(new Rey(false, 5,0));
//		casillas[3][5] = new Casilla(new Peon(false, 3,5));
//		
//		reyNegro = (Rey) getCasilla(0, 5).getPieza();
//		reyBlanco = (Rey) getCasilla(2,7).getPieza();	
		
		
//		// READY TO GO 3
//		casillas[2][1] = new Casilla(new Rey(true, 2,1));
//		casillas[1][5] = new Casilla(new Reina(true, 1,5));
//		
//		casillas[0][1] = new Casilla(new Rey(false, 0,1));
//		
//		reyNegro = (Rey) getCasilla(1,0).getPieza();
//		reyBlanco = (Rey) getCasilla(1,2).getPieza();			
				
	}

	public int getColumnCount() {
		return 8;
	}

	public int getRowCount() {
		return 8;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return casillas[row][col];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int c) {
		return Casilla.class;
	}

	public Casilla getCasilla(int x, int y) {
		return (Casilla) getValueAt(y, x);

	}

	public boolean intentarMover(Casilla casillaDesde, Posicion posicionHasta, Tablero tablero) {
		Pieza pieza = casillaDesde.getPieza();
		
		if (!pieza.esMovimientoValido(posicionHasta, this, tablero)) {
			tablero.mensaje("Movimiento invalido...");
			return false;
		}

		Casilla casillaHasta = getCasilla(posicionHasta.getX(),
				posicionHasta.getY());
//		Pieza pieza2 = casillaHasta.getPieza();
//
//		if (pieza2 != null) {
//			if (pieza.esBlanca() == pieza2.esBlanca()) {
//				tablero.mensaje("Pieza de tu mismo color...");
//				return false;
//			}
//		}

		mover(casillaDesde, posicionHasta, casillaHasta);

		Jugador jugadorActivo = tablero.getJugadorActivo();
		Rey miRey = jugadorActivo.esBlanco() ? getReyBlanco()
				: getReyNegro();

		if(miRey.estoyEnJaque(this, tablero)){
			tablero.mensaje("No puedes dejar a tu rey en jaque...");
			devolverJugada();
			fireTableDataChanged();
			return false;
		}

		Rey reyOponente = jugadorActivo.esBlanco() ? getReyNegro()
				: getReyBlanco();

		Jugador jugadorPasivo = tablero.getJugadorPasivo();
		boolean estaJugadorOponenteAhogado = jugadorPasivo.estoyAhogado(this, tablero);
		
		System.out.println("Jugador contrario: " + estaJugadorOponenteAhogado);
			
		if(reyOponente.estoyEnJaque(this, tablero)){
			if(estaJugadorOponenteAhogado){
				String ganador = jugadorActivo.esBlanco() ? "Jugador Blanco" : "Jugador Negro";
				tablero.mensaje("JAQUE MATE!!! - Ganador: " + ganador );
				tablero.finDelJuego();
			}
			tablero.mensaje("estas en Jaque...");
			reyOponente.setEstaEnJaque(true);
		}else{
			if(estaJugadorOponenteAhogado){
				String ganador = jugadorActivo.esBlanco() ? "Jugador Negro" : "Jugador Blanco";
				tablero.mensaje( ganador  + "  AHOGADO!!!   " +" juego empatado");
				tablero.finDelJuego();
			}
			reyOponente.setEstaEnJaque(false);
		}
		
		casillaDesde.vaciar();
		fireTableDataChanged();
		return true;
	}

	public void devolverJugada() {
		casillaHastaLocal.setPieza(piezaHastaLocal);		
		piezaDesdeLocal.setPosicion(posicionDesdeLocal);
	}

	public void mover(Casilla casillaDesde, Posicion posicionHasta, Casilla casillaHasta) {
		casillaHastaLocal = casillaHasta;
		piezaDesdeLocal = casillaDesde.getPieza();
		piezaHastaLocal = casillaHasta.getPieza();
		posicionDesdeLocal = casillaDesde.getPieza().getPosicion();
		posicionHastaLocal = posicionHasta;
		
		piezaDesdeLocal.setPosicion(posicionHastaLocal);
		casillaHastaLocal.setPieza(piezaDesdeLocal);
	}
}
