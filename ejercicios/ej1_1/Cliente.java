package ej1_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * =====================EJERCICIO 1.1==========================
 * Programa que se conecta a un servidor que gestiona cuentas
 * matemáticas simples. Se encarga de leer información del cliente,
 * enviarsela al servidor usando el protocolo TCP/IP y leer la
 * respuesta de vuelta del servidor.
 * 
 * @author Rubén
 *
 */

public class Cliente {

	String ip = "localhost";
	int port = 6549;
	
	char op;
	long n1, n2;
	
	Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		new Cliente().run();
	}
	
	private void run() {
		
		Socket sock = null;
		DataInputStream entrada = null;
		DataOutputStream salida = null;
		
		try {
			System.out.println("[INFO] Estableciendo conexión...");
			sock = new Socket(ip, port);	//Intentamos conectarnos al cliente.
			System.out.println("[INFO] Conexión establecida.");
			//Establecemos los streams de entrada y salida.
			entrada = new DataInputStream(sock.getInputStream());
			salida = new DataOutputStream(sock.getOutputStream());
		
			while (true) {
				System.out.println(" - CLIENTE OPERACIONES ARITMÉTICAS - ");
				System.out.print("Seleccione operación ó comando (+, -, *, /): ");
				op = teclado.next().toUpperCase().charAt(0);	//Leo del teclado la operación deseado.

				if (op == '+' || op == '-' || op == '*' || op == '/') {		//Construyo la instrucción para el servidor.
					construirInstruccion();	
				} else if (op == 'F') {			//El cliente quiere finalizar la aplicación.
					System.out.println("[INFO] Finalizando la conexión...");
					entrada.close();
					salida.close();
					sock.close();
					System.exit(0);
				} else if (op == 'A') {		//El cliente quiere abortar el servidor.
					op = 'A';
				} else {		//No se ha introducido nada válido.
					System.out.println("Sintáxis inválida.");
				}
				
				enviarInformacion(salida);
				
				leerInformacion(entrada);

			}
		} catch (IOException ioe) {
			System.out.println("[ERROR] Error de E/S.");
		}
		
		teclado.close();
		
	}
	
	/**
	 * Lee del teclado los números necesarios para la operación.
	 * 
	 */
	
	private void construirInstruccion() {
		System.out.print("Introduce el primer número: ");
		n1 = teclado.nextLong();
		System.out.print("Introduce el segundo número: ");
		n2 = teclado.nextLong();
	}
	
	/**
	 * Con la información obtenida la enviamos.
	 * 
	 * @param dos DataOutputStream para enviar información.
	 * @throws IOException Error de E/S.
	 */
	
	private void enviarInformacion(DataOutputStream dos) throws IOException {
		int ope = op;
		
		if((op == 'F') || (op == 'A')) {
			dos.writeChar(ope);
		} else {
			dos.writeChar(ope);
			dos.writeLong(n1);
			dos.writeLong(n2);
		}
		
	}
	
	/**
	 * Lee información del servidor.
	 * 
	 * @param dis InputStream que lee del servidor.
	 * @throws IOException Error de E/S.
	 */
	
	private void leerInformacion(DataInputStream dis) throws IOException {
		int cuentas = dis.readInt();	
		long sol = dis.readLong();
		int longStr = dis.readInt();
		byte[] resp = new byte[longStr];
		for (int i = 0; i < longStr; i++) {
			resp[i] = dis.readByte();
		}
		String cuenta = new String(resp);
		System.out.println("[RESPUESTA SERVIDOR] Operación " + cuentas + " | " + sol + " | " + cuenta);
	
	}

}
