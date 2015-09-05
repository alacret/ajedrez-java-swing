package tablero;

public class Posicion {
	/**
	 * @uml.property  name="x"
	 */
	private int x;
	/**
	 * @uml.property  name="y"
	 */
	private int y;
	public Posicion(int x, int y){
		this.x = x;
		this.y = y;
	}
	/**
	 * @return
	 * @uml.property  name="x"
	 */
	public int getX() {		
		return x;
	}

	/**
	 * @return
	 * @uml.property  name="y"
	 */
	public int getY() {		
		return y;
	}
}
