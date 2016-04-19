package mapa.cuadro;

import grapicos.Pantalla;
import grapicos.Sprite;

public class Cuadro {

	public int x;
	public int y;
	// es bueno que cada cuadro tenga su Sprite
	public Sprite sprite;
	public static final int LADO = 32;
	// Coleccion de cuadros
	public static final Cuadro VACIO = new Cuadro(Sprite.VACIO);

	public static final Cuadro ASFALTO = new Cuadro(Sprite.ASFALTO);

	public static final Cuadro ARENA = new Cuadro(Sprite.ARENA);

	public static final Cuadro BORDE_ASFALTO = new Cuadro(Sprite.BORDE_ASFALTO);

	public static final Cuadro RALLA_ASFALTO = new Cuadro(Sprite.RALLA_ASFALTO);

	public static final Cuadro PIEDRA = new Cuadro(Sprite.PIEDRA);

	public static final Cuadro ESQUINA_ASFALTO = new Cuadro(Sprite.ESQUINA_ASFALTO);

	public static final Cuadro PIEDRA_ARENA = new Cuadro(Sprite.PIEDRA_ARENA);

	public static final Cuadro PUERTA_ESQUINA_SUPERIOR_IZUIERDA = new Cuadro(Sprite.PUERTA_ESQUINA_SUPERIOR_IZUIERDA);

	public static final Cuadro PUERTA_SUPERIOR = new Cuadro(Sprite.PUERTA_SUPERIOR);

	public static final Cuadro PUERTA_CENTRAL_IZQUIERDA = new Cuadro(Sprite.PUERTA_CENTRAL_IZQUIERDA);

	public static final Cuadro PUERTA_CENTRAL = new Cuadro(Sprite.PUERTA_CENTRAL);

	public static final Cuadro OXIDO = new Cuadro(Sprite.OXIDO);

	public static final Cuadro PIEDRA_ARENA_ASFALTO = new Cuadro(Sprite.PIEDRA_ARENA_ASFALTO);
	// FIN DE COLECCION DE CUADROS

	public Cuadro(Sprite sprite) {
		this.sprite = sprite;
	}

	// cada elemento individual se actualize y se dibueje
	// asi misma, para luego pasarlo a la clase pantalla
	// para que hago lo suyo,

	public void mostrar(int x, int y, Pantalla pantalla) {
		pantalla.mostrarCuadro(x << 5, y << 5, this);
	}

	// tails, es para objetos colisinables,
	// o sea, figurar que podemos pasar sobre ellas
	public boolean solido() {
		return false;
	}
}
