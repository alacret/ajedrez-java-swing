package tablero;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import piezas.Pieza;

import cliente.Jugador;

public class Tablero extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	/**
	 * @uml.property  name="tableroModel"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private TableroControlador tableroModel;
	/**
	 * @uml.property  name="tabla"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JTable tabla;
	/**
	 * @uml.property  name="jugadorBlanco"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Jugador jugadorBlanco;
	/**
	 * @uml.property  name="jugadorNegro"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Jugador jugadorNegro;
	/**
	 * @uml.property  name="jugadorActivo"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Jugador jugadorActivo;
	/**
	 * @uml.property  name="jugadorPasivo"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Jugador jugadorPasivo;
	/**
	 * @uml.property  name="jugadorLabelGlobal"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel jugadorLabelGlobal;
	/**
	 * @uml.property  name="casillaActiva"
	 * @uml.associationEnd  
	 */
	private Casilla casillaActiva;
	/**
	 * @uml.property  name="mensajeLabelGlobal"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel mensajeLabelGlobal;
	/**
	 * @uml.property  name="juegoFinalizado"
	 */
	private boolean juegoFinalizado = false;
	

	public Tablero() {
		
		tableroModel = new TableroControlador();
		
		jugadorBlanco = new Jugador(true);
		jugadorBlanco.setMiRey(tableroModel.getReyBlanco());
		
		jugadorNegro = new Jugador();
		jugadorNegro.setMiRey(tableroModel.getReyNegro());
		
		jugadorActivo = jugadorBlanco;
		jugadorPasivo = jugadorNegro;
		
		tabla = new JTable(tableroModel);
		tabla.setDefaultRenderer(Casilla.class, new CasillaRenderer());
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setCellSelectionEnabled(true);
		tabla.setRowHeight(48);
		tabla.addMouseListener(this);
		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(new BorderLayout());
		panel.add(tabla, BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS ));
		
		JLabel jugadorLabel = new JLabel("Jugador:");		
		JPanel jugadorPanel = new JPanel();
		jugadorPanel.add(jugadorLabel);
		jugadorLabelGlobal = new JLabel("");
		jugadorLabelGlobal.setFont(new Font("Arial", Font.PLAIN, 10));
		jugadorPanel.add(jugadorLabelGlobal);
		panel2.add(jugadorPanel);
		
		
		JLabel mensajeLabel = new JLabel("Mensaje:");
		mensajeLabelGlobal = new JLabel("");
		JPanel mensajePanel = new JPanel();
		mensajePanel.add(mensajeLabel);
		mensajePanel.add(mensajeLabelGlobal);
		panel2.add(mensajePanel);
		
		JPanel panel3 = new JPanel();
		panel3.setSize(300, 500);
		panel3.add(panel2);
		panel.add(panel3, BorderLayout.SOUTH);
		jugadorLabelGlobal.setText(jugadorActivo.toString());
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(juegoFinalizado){
			JOptionPane.showMessageDialog(this, "El juego ha finalizado...");
			return;
		}
		
		int y = tabla.getSelectionModel().getLeadSelectionIndex();
		int x = tabla.getColumnModel().getSelectionModel().getLeadSelectionIndex();
		
		Casilla casilla = tableroModel.getCasilla(x, y);
		Pieza pieza = casilla.getPieza();
		System.out.print(pieza == null ? "Casilla" : pieza.toString());
		System.out.println(	x + "," + y);		
		
		if(casillaActiva == null && pieza == null)//Casilla en blanco
			return;
		
		if(casillaActiva == null && pieza != null && pieza.esBlanca() == jugadorActivo.esBlanco()){ // Seleccione una pieza mia
			casilla.setSeleccionada();
			casillaActiva = casilla;
			tableroModel.fireTableDataChanged();
			mensaje("Puede mover");
			return;
		}
		
		if(casillaActiva != null){			
			boolean mover = tableroModel.intentarMover(casillaActiva,new Posicion(x, y), this);
			if(mover){
//				casillaActiva.vaciar();
				casillaActiva = null;
				intercambiarJugador();
				return;
			}
			casillaActiva.deSeleccionar();
			casillaActiva = null;
			tableroModel.fireTableDataChanged();
			return;
		}
	}
	private void intercambiarJugador() {
		if(jugadorActivo.equals(jugadorBlanco)){
			jugadorActivo = jugadorNegro;
			jugadorPasivo = jugadorBlanco;
		}
		else{
			jugadorActivo = jugadorBlanco;
			jugadorPasivo = jugadorNegro;
		}
		
		jugadorLabelGlobal.setText(jugadorActivo.toString());
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mensaje(String string) {
		mensajeLabelGlobal.setText(string);	
		
	}
	/**
	 * @return
	 * @uml.property  name="jugadorActivo"
	 */
	public Jugador getJugadorActivo() {
		return jugadorActivo;
	}
	/**
	 * @return
	 * @uml.property  name="jugadorPasivo"
	 */
	public Jugador getJugadorPasivo() {
		return jugadorPasivo;
	}
	public void finDelJuego() {
		this.juegoFinalizado = true;
		
	}
}
