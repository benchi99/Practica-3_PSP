package ej1_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	String ip = "localhost";
	int port = 6549;
	long n1 = 0, n2 = 0;
	
	Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		new Cliente().run();
	}

	private void run() {
		InetAddress host = null;
		DatagramSocket sock = null;
		DatagramPacket paqt = null;
		
		char op;
		
		try {
			host = InetAddress.getByName(ip);
			sock = new DatagramSocket();
		} catch (UnknownHostException uhe) {
			System.err.println("[ERROR] No se encuentra el host. Terminando...");
			System.exit(1);
		} catch (SocketException ske) {
			System.err.println("[ERROR] Hubo un problema con el socket. Terminando...");
			System.exit(1);
		}
		
		try {
			
			while (true) {
				
				System.out.println(" - CLIENTE OPERACIONES MATEMÁTICAS - ");
				System.out.print("Seleccione operador ó comando(+, -, *, /, F, A): ");
				op = teclado.next().toUpperCase().charAt(0);

				switch (op) {
					case '+':
						construirInstruccion(n1, n2);
						break;
					case '-':
						construirInstruccion(n1, n2);
						break;
					case '*':
						construirInstruccion(n1, n2);
						break;
					case '/':
						construirInstruccion(n1, n2);
						break;
					case 'F':
						System.out.println("[INFO] Finalizando la conexión...");
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
				
				enviarPaquete(paqt, sock, op, n1, n2, host);
				
				leerPaquete(sock, paqt);
				
			}
			
		} catch (IOException ioe) {
			System.err.println("[ERROR] Error de lectura/escritura.");
		}
	}

	private void leerPaquete(DatagramSocket sock, DatagramPacket paqt) throws IOException {
		
		final int LONG_PAQUETE = 64;
		
		byte[] bff = new byte[LONG_PAQUETE];
		
		paqt = new DatagramPacket(bff, bff.length);
		sock.receive(paqt);
		
		int numOp = UtilesArrayBytes.byteToInt(UtilesArrayBytes.splitArray(bff, 0, Integer.BYTES));
		long sol = UtilesArrayBytes.byteToLong(UtilesArrayBytes.splitArray(bff, Integer.BYTES, Long.BYTES));
		byte[] str = UtilesArrayBytes.splitArray(bff, (Integer.BYTES + Long.BYTES), (bff.length - (Integer.BYTES + Long.BYTES)));
		
		String msg = new String(str).trim();
		
		System.out.println("[RESPUESTA] Operación " + numOp + " | " + sol + " | " + msg);
		
	}

	private void enviarPaquete(DatagramPacket paqt, DatagramSocket sock, char op, long uno, long dos, InetAddress host) throws IOException {
		
		byte[] operando = UtilesArrayBytes.charToByte(op);
		byte[] numeroUno = UtilesArrayBytes.longToByte(uno);
		byte[] numeroDos = UtilesArrayBytes.longToByte(dos);
		
		byte[] arrayCompleto = UtilesArrayBytes.construyeArrayBytesFinal(operando, numeroUno, numeroDos);
		
		paqt = new DatagramPacket(arrayCompleto, arrayCompleto.length, host, port);
		sock.send(paqt);
	}

	private void construirInstruccion(long num1, long num2) {
		System.out.print("Introduce el primer número: ");
		n1 = teclado.nextLong();
		System.out.print("Introduce el segundo número: ");
		n2 = teclado.nextLong();
	}
	
}
