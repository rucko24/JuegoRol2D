package grapicos;

import mapa.cuadro.Cuadro;

public final class Pantalla {

	private final int ancho;
	private final int alto;

	private int diferenciaX;
	private int diferenciaY;

	public final int pixeles[];

	public Pantalla(final int ancho, final int alto) {
		this.alto = alto;
		this.ancho = ancho;
		pixeles = new int[ancho * alto];
	}

	/*
	 * cada ves que redibujemos la pantalla de limpiar lo que habia antes y
	 * colorear de negro y luego volver a dibujar encima en cada update de la
	 * screen, para evitar estelas o sea, recorre el arraya, y los pinta de
	 * negro
	 */
	public void limpiar() {

		for (int f = 0; f < pixeles.length; f++) {
			pixeles[f] = 0; // es el negro absoluto
							// a todos los int
		}
	}

	/*
	 * 
	 */
	public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro) {
		// usar potencias de 2 para los tails
		// verificar en que punto de la pantalla estamos
		// dibujando
		//

		// en que medida se ha desplazo el mapa con respecto
		// a la pantalla
		// para dibujar los sprites que correspondan
		// resta la compensacion x la diferenciaX
		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;

		for (int y = 0; y < cuadro.sprite.obtenerLado(); y++) {
			int posicionY = y + compensacionY;

			for (int x = 0; x < cuadro.sprite.obtenerLado(); x++) {
				int posicionX = x + compensacionX;

				// controlar para no dibujar fuera de la
				// pantalla
				// añadido el >= ancho y >= alto
				if (posicionX < -cuadro.sprite.obtenerLado() || posicionX >= ancho || posicionY < 0
						|| posicionY >= alto) {
					break;

				}
				if (posicionX < 0) {
					posicionX = 0;
				}
				// asignacion de arrays para llenarlo de un cuadro con su
				// respectivo sprite
				pixeles[posicionX + posicionY * ancho] = cuadro.sprite.pixeles[x + y * cuadro.sprite.obtenerLado()];
			}
		}
	}

	public void setDirefencia(final int diferenciaX, final int diferenciaY) {
		this.diferenciaX = diferenciaX;
		this.diferenciaY = diferenciaY;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

}
