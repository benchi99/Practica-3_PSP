package ej1_2;

import java.nio.ByteBuffer;

/**
 * =====================EJERCICIO 1.2==========================
 * Clase que gestiona conversión de varios tipos primitivos a 
 * arrays de bytes, así como métodos utiles para manejar arrays
 * de bytes.
 * 
 * @author Rubén
 */

public class UtilesArrayBytes {
	
	static ByteBuffer buffer = null;
	
	/**
	 * Convierte un long a un array de bytes.
	 * 
	 * @param l El long a convertir.
	 * @return El array de bytes equivalente al long.
	 */
	
	public static byte[] longToByte(long l) {
		buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.putLong(l);
		return buffer.array();
	}
	
	/**
	 * Convierte un array de bytes a un long.
	 * 
	 * @param array Array a convertir.
	 * @return El valor equivalente en long.
	 */
	
	public static long byteToLong(byte[] array) {
		buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.put(array);
		buffer.flip();
		return buffer.getLong();
	}
	
	/**
	 * Convierte un char a un array de bytes.
	 * 
	 * @param c El char a convertir.
	 * @return Array de bytes equivalente al char.
	 */
	
	public static byte[] charToByte(char c) {
		buffer = ByteBuffer.allocate(Character.BYTES);
		buffer.putChar(c);
		return buffer.array();
	}
	
	/**
	 * Convierte un array de bytes a un char.
	 * 
	 * @param array Array a convertir.
	 * @return El valor equivalente en char.
	 */
	
	public static char byteToChar(byte[] array) {
		buffer = ByteBuffer.allocate(Character.BYTES);
		buffer.put(array);
		buffer.flip();
		return buffer.getChar();
	}
	
	/**
	 * Convierte un int a un array de bytes.
	 * 
	 * @param i El int a convertir.
	 * @return Array de bytes equivalente al int.
	 */
	
	public static byte[] intToByte(int i) {
		buffer = ByteBuffer.allocate(Integer.BYTES);
		buffer.putInt(i);
		return buffer.array();
	}

	/**
	 * Convierte un array de bytes a int.
	 * 
	 * @param array Array a convertir.
	 * @return Valor equivalente en int.
	 */
	
	public static int byteToInt(byte[] array) {
		buffer = ByteBuffer.allocate(Integer.BYTES);
		buffer.put(array);
		buffer.flip();
		return buffer.getInt();
	}
	
	/**
	 *  Construye un array de bytes sumando de seguido un número 
	 *  indeterminado de arrays, es decir:
	 * 		
	 * ARRAY COMPLETO = ARRAY 1 + ARRAY 2 + ... + ARRAY N
	 * 
	 * @param part N partes que compondrán el array final.
	 * @return Array completo.
	 */
	
	public static byte[] construyeArrayBytesFinal(byte[]... part) {
		//Obtengo la longitud completa del array final.
		int longitud = 0;
		for (int i = 0; i < part.length; i++) {
			longitud = longitud + part[i].length;
		}
		byte[] completo = new byte[longitud];
		
		//Construye el array.
		int recorreCompleto = 0;
		for(int i = 0; i < part.length; i++) {
			for(int j = 0; j < part[i].length; j++) {
				completo[recorreCompleto] = part[i][j];
				recorreCompleto++;
			}
		}
		
		return completo;
	}
	
	/**
	 * Devuelve un trozo de array en función de un margen y una longitud.
	 * 
	 * @param buff El array completo.
	 * @param margen Margen por el que comenzar a dividir.
	 * @param longitud Longitud del trozo
	 * @return El trozo deseado del array.
	 */
	
	public static byte[] splitArray(byte[] buff, int margen, int longitud) {
		//Construyo el array final.
		byte[] resul = new byte[longitud];
		
		/*De parámetros, leo la longitud del array 
		 * obtenido con el margen especificado.
		 */
		for (int i = 0; i < longitud; i++) {
			resul[i] = buff[margen++];
		}
		
		return resul;
	}
	
}
