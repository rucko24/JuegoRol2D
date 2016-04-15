package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Teclado implements KeyListener {

	private final static int numeroTeclas = 120;
	private final boolean teclas[] = new boolean[numeroTeclas];

	// sin getter and setters, para evitar retrasos en
	// el procesamiento
	public boolean arriba, abajo, izquierda, derecha;

	public void actualizar() {
		// fijando teclas para jugar control moderno
		// que valor arriba tenga un valor booleano
		arriba = teclas[KeyEvent.VK_W];
		abajo = teclas[KeyEvent.VK_S];
		izquierda = teclas[KeyEvent.VK_A];
		derecha = teclas[KeyEvent.VK_D];

	}

	@Override // soltar y pulsar
	public void keyTyped(KeyEvent e) {

	}

	@Override // tecla presionada
	public void keyPressed(KeyEvent e) {
		// si pulsamos W, se obtiene el codigo de la tecla
		// y se convierte ese arraty de booleanos en true
		// y todos los demas a false en ese momento
		teclas[e.getKeyCode()] = true;
	}

	@Override // cuando soltamos la teclas
	public void keyReleased(KeyEvent e) {
		teclas[e.getKeyCode()] = false;

	}

}
