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

			// 0 compensacion
			// ancho el tamaño de la linea horizontal de la hoja
			// que queremos escannear
			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void generarMapa() {

		for (int i = 0; i < pixeles.length; i++) {
			switch (pixeles[i]) {
			// cada color corresponde a un sprite
			case 0xff5a5a5a:
				cuadrosCatalogo[i] = Cuadro.ASFALTO;//
				// continue para saltar del switch case, y pasar
				// a la siguiente iteracion
				// colocar ff canal Alfa
				continue;
			case 0xffffefb6:
				cuadrosCatalogo[i] = Cuadro.ARENA;//
				continue;
			case 0xffa6a4a3:
				cuadrosCatalogo[i] = Cuadro.PIEDRA;
				continue;
			case 0xff68505a:
				cuadrosCatalogo[i] = Cuadro.OXIDO;// puerta + asfalto
				continue;
			case 0xff857e70:
				cuadrosCatalogo[i] = Cuadro.PIEDRA_ARENA_ASFALTO;// 938a79
				continue;
			case 0xff2b2416:
				cuadrosCatalogo[i] = Cuadro.BORDE_ASFALTO;
				continue;
			case 0xffd2d2cb:
				cuadrosCatalogo[i] = Cuadro.RALLA_ASFALTO;//
				continue;
			case 0xffcdcfa7:
				cuadrosCatalogo[i] = Cuadro.ESQUINA_ASFALTO;// cdcfa7 b3ac99
				continue;
			case 0xff553833:
				cuadrosCatalogo[i] = Cuadro.PUERTA_CENTRAL_IZQUIERDA;//
				continue;
			case 0xff850038:
				cuadrosCatalogo[i] = Cuadro.PUERTA_SUPERIOR;//
				continue;
			case 0xffff006c:
				cuadrosCatalogo[i] = Cuadro.PUERTA_ESQUINA_SUPERIOR_IZUIERDA;//
				continue;
			case 0xffffaa25:
				cuadrosCatalogo[i] = Cuadro.PIEDRA_ARENA;//
				continue;
			case 0xff524736:
				cuadrosCatalogo[i] = Cuadro.PUERTA_CENTRAL;//
				continue;
			default:
				cuadrosCatalogo[i] = Cuadro.VACIO; // cuadro negro
			}
		}
	}
}
