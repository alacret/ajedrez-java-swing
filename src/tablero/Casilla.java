package tablero;

import java.awt.Color;

import img.FabricaImagenes;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import piezas.Pieza;

public class Casilla extends JLabel{
	/**
	 * @uml.property  name="pieza"
	 * @uml.associationEnd  
	 */
	private Pieza pieza;
	public Casilla(Pieza pza) {
		this.pieza = pza;
		setIcon(FabricaImagenes.getImageIcon(pieza.getRutaImagen()));
		setBorder(BorderFactory.createLineBorder(Color.lightGray));
	}
	public Casilla() {
		setBorder(BorderFactory.createLineBorder(Color.lightGray));
	}
	/**
	 * @return
	 * @uml.property  name="pieza"
	 */
	public Pieza getPieza(){
		return pieza;
	}
	private static final long serialVersionUID = 1L;
	public void setSeleccionada() {
		setBorder(BorderFactory.createLineBorder(Color.red));
	}
	/**
	 * @param pieza2
	 * @uml.property  name="pieza"
	 */
	public void setPieza(Pieza pieza2) {
		this.pieza = pieza2;	
		if(pieza2 != null)
			setIcon(FabricaImagenes.getImageIcon(pieza.getRutaImagen()));
		else
			setIcon(null);
	}
	public void deSeleccionar() {
		setBorder(BorderFactory.createLineBorder(Color.lightGray));
	}
	public void vaciar() {
		deSeleccionar();
		this.pieza = null;
		setIcon(null);
	}
}