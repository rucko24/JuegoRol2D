package mapa;

import java.util.Random;

public class MapaGenerado extends Mapa {

	// Radom static, para tener solo uno de esta clase
	// para no tener un crash
	private static final Random aleatorio = new Random();

	public MapaGenerado(int ancho, int alto) {
		super(ancho, alto);
	}

	// mapas al azar
	@Override
	protected void generarMapa() {
		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {
				// esto nos ayuda a navegar al cuadrado
				// en concreto
				cuadros[x + y * ancho] = aleatorio.nextInt(13);
			}
		}
	}
}
