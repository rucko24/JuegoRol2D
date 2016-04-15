package mapa.cuadro;

import grapicos.Pantalla;
import grapicos.Sprite;

public abstract class Cuadro {

	public int x;
	public int y;
	// es bueno que cada cuadro tenga su Sprite
	public Sprite sprite;

	public static final Cuadro VACIO = new CuadroVacio(Sprite.VACIO);
	// Coleccion de cuadros
	public static final Cuadro ASFALTO = new CuadroAsfalto(Sprite.ASFALTO);
	//

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
