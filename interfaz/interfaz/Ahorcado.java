package interfaz;

import java.util.Scanner;

import dominio.Jugador;


public class Ahorcado {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		final String NOMBRE_JUGADOR_1, NOMBRE_JUGADOR_2;
		final char[] PALABRA_JUGADOR_1, PALABRA_JUGADOR_2;
		final int VIDA_INICIAL = 6;
		char[] incognitaPalabra1;
		char[] incognitaPalabra2;
		Jugador jugador1;
		Jugador jugador2;
		String nombres[] = ingresarNombres(teclado);
		
		NOMBRE_JUGADOR_1 = nombres[0];
		NOMBRE_JUGADOR_2 = nombres[1];
		System.out.println(NOMBRE_JUGADOR_1 + ", ingrese su palabra");
		PALABRA_JUGADOR_1 = ingresarPalabras(teclado);
		System.out.println(NOMBRE_JUGADOR_2 + ", ingrese su palabra");
		PALABRA_JUGADOR_2 = ingresarPalabras(teclado);
		incognitaPalabra1 = generarIncognita(PALABRA_JUGADOR_1);
		incognitaPalabra2 = generarIncognita(PALABRA_JUGADOR_2);
		jugador1 = new Jugador(NOMBRE_JUGADOR_1, PALABRA_JUGADOR_2, incognitaPalabra2, VIDA_INICIAL);
		jugador2 = new Jugador(NOMBRE_JUGADOR_2, PALABRA_JUGADOR_1, incognitaPalabra1, VIDA_INICIAL);
		
		comenzarJuego(teclado, jugador1, jugador2);
		
	}
	
	private static String[] ingresarNombres(Scanner teclado) {
		String[] nombres = new String[2];
		System.out.println("Ingrese el nombre del jugador 1");
		nombres[0]= teclado.next();
		System.out.println("Ingrese el nombre del jugador 2");
		nombres[1]= teclado.next();
		return nombres;
	}
	private static char[] ingresarPalabras(Scanner teclado) {
		String palabra;
		palabra = teclado.next();
		char[] array = new char [palabra.length()];
		for(int i=0; i<array.length; i++) {
			array[i] = palabra.charAt(i);
		}
		return array;
	}
	private static char[] generarIncognita(char[] palabra) {
		char[] incognita = new char[palabra.length];
		for(int i=0; i<incognita.length; i++) {
			incognita[i] = '_';
		}
		return incognita;
	}
	
	private static void ingresarLetra(Scanner teclado, Jugador jugadorActual) {
		char letra;
		boolean resultado = false;
		System.out.println("Ingrese una letra");
		letra = teclado.next().charAt(0);
		resultado = jugadorActual.ingresarLetra(letra);
		if(resultado) {
			System.out.println("Letra correcta");
		} else {
			System.out.println("Letra incorrecta, pierde una vida");
		}
	}
	
	private static void arriesgar(Scanner teclado,Jugador jugadorActual, Jugador otroJugador) {
		System.out.println("Arriesgue...");
		boolean resultado = false;
		char[] arrayPalabraArriesgada;
		arrayPalabraArriesgada = ingresarPalabras(teclado);
		resultado = jugadorActual.arriesgar(arrayPalabraArriesgada);
		if(resultado) {
			boolean empate = false;
			char[] arrayPalabraArriesgadaDelOtroJugador;
			System.out.println("Palabra acertada!!");
			System.out.println(otroJugador.getNombre() + " arriesgue para empatar");
			arrayPalabraArriesgadaDelOtroJugador = ingresarPalabras(teclado);
			empate = otroJugador.arriesgar(arrayPalabraArriesgadaDelOtroJugador);
			if(empate) {
				System.out.println(otroJugador.getNombre() + " también acierta!");
			}
		} else {
			System.out.println("Palabra errónea");
		}
	}
	
	private static void mostrarEstado(Jugador jugador1, Jugador jugador2) {
		System.out.println(jugador1);
		System.out.println(jugador2);
	}
	
	private static void comenzarJuego(Scanner teclado, Jugador jugador1, Jugador jugador2) {
		final int TURNO_JUGADOR_1 = 8, TURNO_JUGADOR_2 = 9;
		final char INGRESAR_LETRA = '1', ARRIESGAR = '2';
		int turno = 0;
		
		if((int)(Math.random() * 100 + 1)> 50) {
			System.out.println("Comienza el jugador 1");
			turno = TURNO_JUGADOR_1;
		} else {
			System.out.println("Comienza el jugador 2");
			turno = TURNO_JUGADOR_2;
		}
		
		do {
			mostrarEstado(jugador1, jugador2);
			char opcion = '\000';
			if(turno == TURNO_JUGADOR_1) {
				System.out.println("Turno de " + jugador1.getNombre());
			} else {
				System.out.println("Turno de " + jugador2.getNombre());
			}
			System.out.println("¿Qué desea hacer?\n\t1.Ingresar letra\n\t2.Arriesgar");
			opcion = teclado.next().charAt(0);
			switch(opcion) {
			case INGRESAR_LETRA:{
				switch(turno) {
				case TURNO_JUGADOR_1:
					ingresarLetra(teclado,jugador1);
					turno = TURNO_JUGADOR_2;
					break;
				case TURNO_JUGADOR_2:
					ingresarLetra(teclado,jugador2);
					turno = TURNO_JUGADOR_1;
					break;				
				}
				break;
			}
			case ARRIESGAR:{
				switch(turno) {
				case TURNO_JUGADOR_1:
					arriesgar(teclado, jugador1, jugador2);
					turno = TURNO_JUGADOR_2;
					break;
				case TURNO_JUGADOR_2:
					arriesgar(teclado, jugador2, jugador1);
					turno = TURNO_JUGADOR_1;		
					break;
				}
			}
			break;
			default:
			break;
			}
		} while(jugador1.getVidasRestantes()!=0 && jugador2.getVidasRestantes()!=0 &&
				!jugador1.compararPalabras() && !jugador2.compararPalabras());
		
		if(jugador1.compararPalabras() && jugador2.compararPalabras()) {
			System.out.println("Empate");
		} else if(jugador1.compararPalabras()) {
				System.out.println("Gana el jugador 1, " + jugador1.getNombre());
		} else if(jugador2.compararPalabras()) {
				System.out.println("Gana el jugador 2, " + jugador2.getNombre());
		} else if(jugador1.getVidasRestantes()==0) {
				System.out.println("Gana el jugador 2, " + jugador2.getNombre());
		} else if(jugador2.getVidasRestantes()==0) {
				System.out.println("Gana el jugador 1, " + jugador1.getNombre());
		}		
	}

}
