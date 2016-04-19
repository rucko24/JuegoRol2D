package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import control.Teclado;
import grapicos.Pantalla;
import mapa.Mapa;
import mapa.MapaGenerado;

public class Juego extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private static final int ANCHO = 800;
	private static final int ALTO = 600;

	private static JFrame ventana;
	private static final String NOMBRE = "Juego";
	private static String CONTADOR_FPS = "";
	private static String CONTADOR_APS = "";

	private static int aps = 0;
	private static int fps = 0;

	private static int x = 0;
	private static int y = 0;

	private static Thread thread;
	private static Teclado teclado;
	private static Pantalla pantalla;
	private static Mapa mapa;

	private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
	// acceder a la imagen en forma de array de pixeles, nos devuelve un array
	// de ints,
	// getRaster() la remasterizacion de la img
	// getDataBuffered(), nos devuelve en formato que usar BufferedImage
	// getData() retorna los datos
	// (DataBufferedInt) casting a DataBuffered
	private static int pixeles[] = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();

	private static volatile boolean enFuncionamiento = false;

	private Juego() {
		setPreferredSize(new Dimension(ANCHO, ALTO));

		pantalla = new Pantalla(ANCHO, ALTO);
		// 128 * 128, no son pixeles sino, tails
		mapa = new MapaGenerado(128, 128);
		teclado = new Teclado();
		// que java detecte todas la teclas que se pulsan
		// dentro del canvas
		addKeyListener(teclado);

		ventana = new JFrame(NOMBRE);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setLayout(new BorderLayout());
		ventana.add(this, BorderLayout.CENTER);
		ventana.setUndecorated(true);
		ventana.pack();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);

	}

	public synchronized void iniciar() {
		enFuncionamiento = true;
		thread = new Thread(this, "Graficos");
		thread.start();
	}

	public synchronized void detener() {
		enFuncionamiento = false;

		try {

			thread.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void actualizar() {
		teclado.actualizar();

		// LO QUE SE MUEVE DEBAJO DEL USER ES EL MAPA
		// Direccion de controles
		// arriba y++ para que el mapa baje
		// o sea, funciona al reves todo
		/*
		 * cambio en los controles, todo al reves
		 */
		if (teclado.arriba) { // tecla W
			y--;// abajo
		} else if (teclado.abajo) {
			y++;// arriba
		} else if (teclado.izquierda) {
			x--; // derecha
		} else if (teclado.derecha) {
			x++;// izquierda
		} else if (teclado.salir) {
			System.exit(0);
		}

		aps++;
	}

	// chekear este metodo mostrar en video 11
	private void mostrar() {
		BufferStrategy estrategia = getBufferStrategy();
		// Buffer espacio de memoria que guarda cosas
		// y creara nuestros dibujos
		//
		if (estrategia == null) {
			// 3 para usar un triple buffer
			// 3 espacios en la memory
			// el cpu pintara 3 img en secuencia
			createBufferStrategy(3);
			return;
		}

		//
		pantalla.limpiar();
		mapa.mostrar(x, y, pantalla);

		// manera mas elegante de copiar los 2 arrays mas rapido
		// copiando los graficos de la pantalla al juego
		System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);

		// ahora los dibujamos
		// dibujara lo que esta dentro de estrategia
		Graphics g = estrategia.getDrawGraphics();

		// aqui argumentos para que dibuje la img
		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.white);// cuadrado blanco, para simular un personaje

		// para que salga en el centro de la pantalla
		g.fillRect(ANCHO / 2, ALTO / 2, 32, 32);

		/*
		 * para dibujar un String en pantalla de los aps_fps
		 */
		g.drawString(CONTADOR_APS, 10, 20);
		g.drawString(CONTADOR_FPS, 10, 35);

		// destruye la memoria que g tenia contruida
		g.dispose();

		// esto hace que la img se vea en la pantalla
		estrategia.show();

		fps++;
		// hola
	}

	@Override
	public void run() {

		// cuantos nano segundos hay en un segundo 9 ceros
		final int NS_POR_SEGUNDO = 1000000000;
		// cuantas actualizaciones por segundo, 60
		final byte APS_OBJETIVO = 60;
		// cuantos nano segundos tienen que transcurrir para que
		// sigamos el objetivo de actualizar 60 veces por segundo

		// cuantos nanos trancurren por update 1000millones / 60
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

		// se atribuye una cantidad en nano, en ese momento
		long referenciaActualizacion = System.nanoTime();
		//
		long referenciaContador = System.nanoTime();

		double tiempoTracurrido;
		double delta = 0;

		requestFocus();

		while (enFuncionamiento) {
			// los nanos asignados aqui son distintos a la
			// variable de arriba xq, ya paso el tiempo
			// de ejecucion entre ambas lineas
			// Es como iniciar el cronoMetro
			final long inicioBucle = System.nanoTime(); // tiempo final -
														// inicial

			// calculamos el tiempo transcurrido entre tiempo inicial - final
			// o sea, entre ambos cronometros
			tiempoTracurrido = inicioBucle - referenciaActualizacion;

			// luego a referenciaAc se le asina el tiempo cuando inicia el buble
			// ya que al girar el bucle, se restaran de nuevo las
			// diferencias entre cronometros, una y otra ves
			// midiendo la cantidad entre 2 puntos fijos
			referenciaActualizacion = inicioBucle;

			// suma cantidades de tiempo cada ves. hasta llegar a 1
			// para actualizar, y luego a 0
			delta += tiempoTracurrido / NS_POR_ACTUALIZACION;

			while (delta >= 1) {
				// actualizar se ejecuta mas o menos
				// cada 60 veces por segundos
				actualizar();

				delta--;
			}

			mostrar();

			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
				// para actualizar el contador cada segundo
				/*
				 * inicializando los String y se actualiza miestras se ejecuta
				 * el juego para luego a actualizarlo a 0, cada 1segundo
				 */
				CONTADOR_APS = "APS: " + aps;
				CONTADOR_FPS = "FPS: " + fps;
				print("Frames por segundo\n" + fps);
				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime();
			}
		}
	}

	public static void main(String[] GAME) {
		Juego juego = new Juego();
		juego.iniciar();
	}

	static void print(String s) {
		System.out.println(s);
	}
}
