package ej1_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Cliente {
	
	String ip = "localhost";
	int port = 6549;
	
	char op;
	long n1, n2;
	
	Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		new Cliente().run(args);
	}
	
	private void run(String[] args) {
		
		if (args != null) {
			ip = args[0];
		}
		
		Socket sock = null;
		DataInputStream entrada = null;
		DataOutputStream salida = null;
		
		try {
			System.out.println("[INFO] Estableciendo conexi�n...");
			sock = new Socket(ip, port);
			System.out.println("[INFO] Conexi�n establecida.");
			entrada = new DataInputStream(sock.getInputStream());
			salida = new DataOutputStream(sock.getOutputStream());
		} catch (SocketException ske) {
			System.err.println("[ERROR] Conexi�n perdida");
			System.exit(1);
		} catch (IOException ioe) {
			System.err.println("[ERROR] Error de entrada/salida.");
		}
		
		
		try {
			while (true) {
				
				System.out.println(" - CLIENTE OPERACIONES ARITM�TICAS - ");
				System.out.print("Seleccione operaci�n � comando (+, -, *, /): ");
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
						System.out.println("[INFO] Finalizando la conexi�n...");
						entrada.close();
						salida.close();
						sock.close();
						System.exit(0);
						break;
					case 'A':
						op = 'A';
						break;
					default:	
						System.out.println("Sint�xis inv�lida.");
						break;
				}
				
				enviarInformacion(salida);
				
				leerInformacion(entrada);

			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		teclado.close();
		
	}
	
	private void construirInstruccion() {
		System.out.print("Introduce el primer n�mero: ");
		n1 = teclado.nextLong();
		System.out.print("Introduce el segundo n�mero: ");
		n2 = teclado.nextLong();
	}
	
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
