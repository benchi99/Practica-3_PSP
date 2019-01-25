package ej1_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * =====================EJERCICIO 1.3==========================
 * Programa que se conecta a un servidor que gestiona cuentas
 * matem�ticas simples. Se encarga de leer informaci�n del cliente,
 * enviarsela al servidor usando el protocolo TCP/IP y leer la
 * respuesta de vuelta del servidor.
 * 
 * @author Rub�n
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
			System.out.println("[INFO] Estableciendo conexi�n...");
			sock = new Socket(ip, port);	//Intentamos conectarnos al cliente.
			System.out.println("[INFO] Conexi�n establecida.");
			//Establecemos los streams de entrada y salida.
			entrada = new DataInputStream(sock.getInputStream());
			salida = new DataOutputStream(sock.getOutputStream());
		
			while (true) {
				System.out.println(" - CLIENTE OPERACIONES ARITM�TICAS - ");
				System.out.print("Seleccione operaci�n � comando (+, -, *, /): ");
				op = teclado.next().toUpperCase().charAt(0);	//Leo del teclado la operaci�n deseado.

				if (op == '+' || op == '-' || op == '*' || op == '/') {		//Construyo la instrucci�n para el servidor.
					construirInstruccion();	
				} else if (op == 'F') {			//El cliente quiere finalizar la aplicaci�n.
					System.out.println("[INFO] Finalizando la conexi�n...");
					entrada.close();
					salida.close();
					sock.close();
					System.exit(0);
				} else if (op == 'A') {		//El cliente quiere abortar el servidor.
					op = 'A';
				} else {		//No se ha introducido nada v�lido.
					System.out.println("Sint�xis inv�lida.");
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
	 * Lee del teclado los n�meros necesarios para la operaci�n.
	 * 
	 */
	
	private void construirInstruccion() {
		System.out.print("Introduce el primer n�mero: ");
		n1 = teclado.nextLong();
		System.out.print("Introduce el segundo n�mero: ");
		n2 = teclado.nextLong();
	}
	
	/**
	 * Con la informaci�n obtenida la enviamos.
	 * 
	 * @param dos DataOutputStream para enviar informaci�n.
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
	 * Lee informaci�n del servidor.
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
		System.out.println("[RESPUESTA SERVIDOR] Operaci�n " + cuentas + " | " + sol + " | " + cuenta);
	
	}

}
