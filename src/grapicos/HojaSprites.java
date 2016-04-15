package grapicos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HojaSprites {

	private final int ancho;
	private final int alto;
	public final int pixeles[];

	// coleccion de hojas de sprites
	public static HojaSprites desierto = new HojaSprites("/texturas/desierto.png", 320, 320);

	// final que el valor no cambiara durante el contructor
	// no variaran los valores

	public HojaSprites(final String ruta, final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;
		// el array tendra el mismo tamaña con pixeles tenga la img
		// 10 * 10 = 100, = 100 pixeles diferentes
		pixeles = new int[ancho * alto];

		// cada int, tendra atribuido el color de pixeles
		//

		BufferedImage imagen;

		try {

			imagen = ImageIO.read(HojaSprites.class.getResource(ruta));
			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int obtenerAncho() {
		return ancho;
	}

}
