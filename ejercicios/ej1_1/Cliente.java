package ej1_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

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
			sock = new Socket(ip, port);
			System.out.println("[INFO] Conexión establecida.");
			entrada = new DataInputStream(sock.getInputStream());
			salida = new DataOutputStream(sock.getOutputStream());
		
			while (true) {
				
				System.out.println(" - CLIENTE OPERACIONES ARITMÉTICAS - ");
				System.out.print("Seleccione operación ó comando (+, -, *, /): ");
				op = teclado.next().toUpperCase().charAt(0);
				switch (op) {
					case '+':
						construirInstruccion();
						break;
					case '-':
						construirInstruccion();
						break;
					case '*':
						construirInstruccion();
						break;
					case '/':
						construirInstruccion();
						break;
					case 'F':
						System.out.println("[INFO] Finalizando la conexión...");
						entrada.close();
						salida.close();
						sock.close();
						System.exit(0);
						break;
					case 'A':
						op = 'A';
						break;
					default:	
						System.out.println("Sintáxis inválida.");
						break;
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
		System.out.println("Longitud del String: " + longStr);
		byte[] resp = new byte[longStr];
		for (int i = 0; i < longStr; i++) {
			resp[i] = dis.readByte();
		}
		String cuenta = new String(resp);
		System.out.println("[RESPUESTA SERVIDOR] Operación " + cuentas + " | " + sol + " | " + cuenta);
	
	}

}
