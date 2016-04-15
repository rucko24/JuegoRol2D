package grapicos;

public final class Sprite {

	private final int lado;
	private int x;
	private int y;

	public int pixeles[];
	private HojaSprites hoja;

	// coleccion de sprites

	// 2 contructores, 0 = a negro.
	public static final Sprite VACIO = new Sprite(32, 0);
	public static final Sprite ASFALTO = new Sprite(32, 0, 0, HojaSprites.desierto);

	// fin de la coleccion de sprites

	public Sprite(final int lado, final int columna, final int fila, final HojaSprites hoja) {
		this.lado = lado;

		pixeles = new int[lado * lado];

		// x para columnas
		// y para filas
		// tienen el valor de la fila y columna en concreto
		this.x = columna * lado;
		this.y = fila * lado;

		this.hoja = hoja;// para acceder a la hoja de Sprites

		// contando la vueltas del bucle
		// con la y primero, de izquierda a derecha
		// y de arriba a abajo
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				// de izquierda a derecha
				// se actualizar toda la fila y cuando se cumple
				// bajo y luego la x actualizar toda la columnas

				// cuanto estamos en x hacemos todo esto, hasta 320, terminal el
				// bucle
				// x, y suma 1, y de nuevo

				// 320 * 320 = 102400 actualizadas por segundo
				pixeles[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.obtenerAncho()];
			}
		}

	}

	// contructor de este color definido
	public Sprite(final int lado, final int color) {
		this.lado = lado;
		pixeles = new int[lado * lado];

		// dar tantas iteraciones como pixeles tenga el array
		for (int f = 0; f < pixeles.length; f++) {
			pixeles[f] = color;
		}
	}

	public int obtenerLado() {
		return lado;
	}
}
