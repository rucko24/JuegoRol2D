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
	public static final Sprite ASFALTO = new Sprite(32, 0, 0, 0, HojaSprites.desierto);
	public static final Sprite ARENA = new Sprite(32, 1, 0, 0, HojaSprites.desierto);
	public static final Sprite BORDE_ASFALTO = new Sprite(32, 2, 0, 0, HojaSprites.desierto);
	public static final Sprite RALLA_ASFALTO = new Sprite(32, 3, 0, 0, HojaSprites.desierto);
	public static final Sprite PIEDRA = new Sprite(32, 4, 0, 0, HojaSprites.desierto);
	public static final Sprite ESQUINA_ASFALTO = new Sprite(32, 5, 0, 0, HojaSprites.desierto);
	public static final Sprite PIEDRA_ARENA = new Sprite(32, 6, 0, 0, HojaSprites.desierto);
	public static final Sprite PUERTA_ESQUINA_SUPERIOR_IZUIERDA = new Sprite(32, 6, 1, 0, HojaSprites.desierto);
	public static final Sprite PUERTA_SUPERIOR = new Sprite(32, 7, 1, 0, HojaSprites.desierto);
	public static final Sprite PUERTA_ESQUINA_SUPERIOR_DERECHA = new Sprite(32, 8, 1, 0, HojaSprites.desierto);
	public static final Sprite PUERTA_CENTRAL_IZQUIERDA = new Sprite(32, 6, 2, 0, HojaSprites.desierto);
	public static final Sprite PUERTA_CENTRAL = new Sprite(32, 7, 0, 0, HojaSprites.desierto);
	public static final Sprite PUERTA_CENTRAL_DERECHA = new Sprite(32, 8, 2, 0, HojaSprites.desierto);
	public static final Sprite OXIDO = new Sprite(32, 3, 3, 0, HojaSprites.desierto);
	public static final Sprite PIEDRA_ARENA_ASFALTO = new Sprite(32, 4, 3, 0, HojaSprites.desierto);
	// fin de la coleccion de sprites

	public Sprite(final int lado, final int columna, final int fila, final int version, final HojaSprites hoja) {
		this.lado = lado;

		pixeles = new int[lado * lado];

		// x para columnas
		// y para filas
		// tienen el valor de la fila y columna en concreto
		this.x = columna * lado;
		this.y = fila * lado;

		this.hoja = hoja;// para acceder a la hoja de Sprites

		// version numero comprendido entre el 1 y el 7, para rotar los sprites
		//
		cargarSprite(version);
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

	private void cargarSprite(int version) {
		if (version == 0) {
			cargaNormal();
		} else {
			cargaManipulada(version);
		}
	}

	private void cargaNormal() {
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

	private void cargaManipulada(int version) {
		int pixelesTmp[] = iniciarPixelesTemporales();

		switch (version) {
		case 1:

		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:

		default:

		}
	}

	private int[] iniciarPixelesTemporales() {

		int pixelesTmp[] = new int[lado * lado];

		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				// almacenaremos el sprite original
				// en pxTmp para manipular la copia
				pixelesTmp[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.obtenerAncho()];
			}
		}

		return pixelesTmp;
	}

	// estos metodos nos daran las 7 versiones de los Sprites
	private void invertirX(int pixelesTemporales[]) {

	}

	private void invertirY(int pixelesTemporales[]) {

	}

	private void invertirXY(int pixelesTemporales[]) {

	}

	private void rotar90Izq(int pixelesTemporales[]) {

	}

	private void rotar90Dere(int pixelesTemporales[]) {

	}

	private void rotar90IzqInvertido(int pixelesTemporales[]) {

	}

	private void rotar90DereInvertido(int pixelesTemporales[]) {

	}
}
