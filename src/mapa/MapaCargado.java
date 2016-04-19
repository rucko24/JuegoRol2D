package mapa;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mapa.cuadro.Cuadro;

public class MapaCargado extends Mapa {

	// crear un array de pixles para almacenar el color de ellos
	// para traducirlos luego los sprites
	private int pixeles[];

	/*
	 * usaremos un archivo
	 */
	public MapaCargado(String ruta) {
		super(ruta);

	}

	// flecha verde indica que sobreEscribimos
	protected void cargarMapa(String ruta) {
		// leer la imagen de nuestro mapa con este metodo
		try {
			BufferedImage imagen = ImageIO.read(MapaCargado.class.getResource(ruta));

			ancho = imagen.getWidth();
			alto = imagen.getHeight();
			// Con esto inicializamos el ancho y alto del mapa

			cuadrosCatalogo = new Cuadro[ancho * alto];// array de cuadros
			pixeles = new int[ancho * alto]; // array de int

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
