package dominio;

import java.util.Arrays;

public class Jugador {
	private final String NOMBRE;
	private final char[] PALABRA_A_ADIVINAR;
	private char[] incognita;
	private int vidasRestantes;
	
	public Jugador(String nombre, char[] palabraAAdivinar, char[] incognita, int vidas) {
		this.NOMBRE = nombre;
		this.PALABRA_A_ADIVINAR = palabraAAdivinar;
		this.incognita = incognita;
		this.vidasRestantes = vidas;
	}

	public boolean ingresarLetra(char letra) {
		boolean resultado = false;
		for(int i=0; i<PALABRA_A_ADIVINAR.length; i++) {
			if(PALABRA_A_ADIVINAR[i] == letra) {
				resultado = true;
				incognita[i] = letra;
			}
		}
		if(!resultado) {
			vidasRestantes--;
		}
		return resultado;
	}
	
	public boolean arriesgar(char[] palabra) {
		boolean resultado = false;
		if(Arrays.equals(PALABRA_A_ADIVINAR, palabra)) {
			resultado = true;
			incognita = PALABRA_A_ADIVINAR;
		} else {
			vidasRestantes = 0;
		}
		return resultado;
	}
	
	public String getNombre() {
		return NOMBRE;
	}

	public int getVidasRestantes() {
		return vidasRestantes;
	}
	
	public boolean compararPalabras() {
		return Arrays.equals(incognita, PALABRA_A_ADIVINAR);
	}
	@Override
	public String toString() {
		return "Jugador Nombre: " + NOMBRE + ", incógnita=" + Arrays.toString(incognita) + ", \tvidasRestantes: "
				+ vidasRestantes + "";
	}
	
	
}
