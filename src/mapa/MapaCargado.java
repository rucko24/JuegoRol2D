package mapa;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

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

		try {
			BufferedImage imagen = ImageIO.read(MapaCargado.class.getResource(ruta));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
