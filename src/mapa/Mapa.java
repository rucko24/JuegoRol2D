package mapa;

import grapicos.Pantalla;
import mapa.cuadro.Cuadro;

public abstract class Mapa {

	protected int ancho;
	protected int alto;
	// array que recoje cuadros de 32 px
	protected int cuadros[];
	protected Cuadro cuadrosCatalogo[];

	public Mapa(int ancho, int alto) {
		this.alto = alto;
		this.ancho = ancho;

		cuadros = new int[alto * ancho];

		generarMapa();
	}

	public Mapa(String ruta) {
		cargarMapa(ruta);
		// asignar al array de tail de cuadros el valor correspondiente
		// segun el pixel que haiga en el mapa
		generarMapa();
	}

	protected void generarMapa() {

	}

	protected void cargarMapa(String ruta) {

	}

	public void actualizar() {

	}

	/*
	 * Identificar de alguna manera los lados de la pantalla arriba, abajo,
	 * derecha, izquierda
	 */
	public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla) {

		pantalla.setDirefencia(compensacionX, compensacionY);
		// direcciones cardinales
		// derecha este

		// buscar presicion en pixels, la unidad minima que debe de andar
		// por eso dividimos entre 32, a nivel de px

		// el Bitshiftting >> 5, dividir entre 32
		int oeste = compensacionX >> 5; // 32/32 = 1
		int este = ((compensacionX + pantalla.getAncho() + Cuadro.LADO) >> 5);
		int norte = (compensacionY >> 5);
		int sur = ((compensacionY + pantalla.getAlto() + Cuadro.LADO) >> 5);

		/*
		 * !n !o e! !s decidir que cuadro esta en cada sitio
		 */
		for (int y = norte; y < sur; y++) {
			for (int x = oeste; x < este; x++) {

				// getCuadro(x, y).mostrar(x, y, pantalla);
				if (x < 0 || y < 0 || x >= ancho || y >= alto) {
					// si esta condicion se cumple es que estamos
					// fuera del mapa
					// si salimos, pintamos un cuadro negro
					Cuadro.VACIO.mostrar(x, y, pantalla);
				} else {
					// si estamos dentro del mapa
					// dibujamos el cuadro que corresponda
					cuadrosCatalogo[x + y * ancho].mostrar(x, y, pantalla);
					;
				}

			}
		}
	}

	public Cuadro getCuadro(final int x, final int y) {
		/*
		 * >= muy importante, xq x puede ser mayor que el ancho pero saltamos la
		 * condicion de igual. e igual con y
		 */
		if (x < 0 || y < 0 || x >= ancho || y >= alto) {
			return Cuadro.VACIO;
		}
		// identificar cada punto del array
		// 1 arena
		// 0 asfalto
		// if no switch si, mas eficiente

		switch (cuadros[x + y * ancho]) {
		case 0:
			return Cuadro.ASFALTO;
		case 1:
			return Cuadro.ARENA;
		case 2:
			return Cuadro.BORDE_ASFALTO;
		case 3:
			return Cuadro.RALLA_ASFALTO;
		case 4:
			return Cuadro.PIEDRA;
		case 5:
			return Cuadro.ESQUINA_ASFALTO;
		case 6:
			return Cuadro.PIEDRA_ARENA;
		case 7:
			return Cuadro.PUERTA_ESQUINA_SUPERIOR_IZUIERDA;
		case 8:
			return Cuadro.PUERTA_SUPERIOR;
		case 9:
			return Cuadro.PUERTA_CENTRAL_IZQUIERDA;
		case 10:
			return Cuadro.PUERTA_CENTRAL;
		case 11:
			return Cuadro.OXIDO;
		case 12:
			return Cuadro.PIEDRA_ARENA_ASFALTO;
		default:
			return Cuadro.VACIO;
		}

	}
}
