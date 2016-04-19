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
			case 0x5a5a5a:
				cuadrosCatalogo[i] = Cuadro.ASFALTO;//
				// continue para saltar del switch case, y pasar
				// a la siguiente iteracion
				continue;
			case 0xffefb6:
				cuadrosCatalogo[i] = Cuadro.ARENA;//
				continue;
			case 0xa6a4a3:
				cuadrosCatalogo[i] = Cuadro.PIEDRA;
				continue;
			case 0x68505a:
				cuadrosCatalogo[i] = Cuadro.OXIDO;//
				continue;
			case 0x857e70:
				cuadrosCatalogo[i] = Cuadro.PIEDRA_ARENA_ASFALTO;// d2d2cb
				continue;
			case 0x2b2416:
				cuadrosCatalogo[i] = Cuadro.BORDE_ASFALTO;
				continue;
			case 0xd2d2cb:
				cuadrosCatalogo[i] = Cuadro.RALLA_ASFALTO;//
				continue;
			case 0xb3ac99:
				cuadrosCatalogo[i] = Cuadro.ESQUINA_ASFALTO;//
				continue;
			case 0x7c1541:
				cuadrosCatalogo[i] = Cuadro.PUERTA_CENTRAL_IZQUIERDA;//
				continue;
			case 0x850038:
				cuadrosCatalogo[i] = Cuadro.PUERTA_SUPERIOR;//
				continue;
			case 0xff006c:
				cuadrosCatalogo[i] = Cuadro.PUERTA_ESQUINA_SUPERIOR_IZUIERDA;//
				continue;
			case 0xffaa25:
				cuadrosCatalogo[i] = Cuadro.PIEDRA_ARENA;//
				continue;
			case 0x524736:
				cuadrosCatalogo[i] = Cuadro.PUERTA_CENTRAL;//
			default:
				cuadrosCatalogo[i] = Cuadro.VACIO; // cuadro negro
			}
		}
	}
}
