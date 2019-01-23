package ej1_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor {

	int port = 6549;
	
	final int TAMANIO_PAQ = Character.BYTES + (Long.BYTES * 2);
	
	InetAddress cliente = null;
	int cliPort;
	char op;
	long n1;
	long n2;
	
	public static void main(String[] args) {
		new Servidor().run();
	}

	private void run() {
		
		DatagramSocket sock = null;
		DatagramPacket paq = null;
		
		try {
			System.out.println("[INFO] Esperando conexión...");
			sock = new DatagramSocket(port);
		} catch (SocketException ske) {
			System.err.println("[ERROR] Hubo un problema con el socket. Terminando...");
			System.exit(1);
		} 
		
		try {
			
			int numOp = 1;
			
			while (true) {
				
				leerPaquete(sock, paq);
				
				if (op == 'A') {
					break;
				}
				
				long sol = calcula(op, n1, n2);
				
				enviar(op, n1, n2, sol, numOp, paq, sock, cliente, cliPort);
				
				numOp++;
			}
			
			sock.close();
			
		} catch (IOException ioe) {
			System.err.println("[ERROR] Hubo un problema de entrada/salida. Terminando...");
			System.exit(1);
		}
	}

	private void leerPaquete(DatagramSocket sock, DatagramPacket paq) throws IOException {
		byte[] bff = new byte[TAMANIO_PAQ];
		paq = new DatagramPacket(bff, TAMANIO_PAQ);
		System.out.println("[INFO] Esperando paquete...");
		sock.receive(paq);
		System.out.println("[INFO] Paquete recibido de " + paq.getAddress() + ". Obteniendo contenido...");
		cliente = paq.getAddress();
		cliPort = paq.getPort();
		
		op = UtilesArrayBytes.byteToChar(UtilesArrayBytes.splitArray(bff, 0, Character.BYTES));
		n1 = UtilesArrayBytes.byteToLong(UtilesArrayBytes.splitArray(bff, Character.BYTES, Long.BYTES));
		n2 = UtilesArrayBytes.byteToLong(UtilesArrayBytes.splitArray(bff, (Character.BYTES + Long.BYTES), Long.BYTES));
		
	}

	private void enviar(char op, long n1, long n2, long sol, int numOp, DatagramPacket paq, DatagramSocket sock, InetAddress cliente, int port) throws IOException {
	
		byte[] ope = UtilesArrayBytes.intToByte(numOp);
		byte[] solu = UtilesArrayBytes.longToByte(sol);
		String str = n1 + " " + op + " " + n2 + " = " + sol;
		byte[] strarr = str.getBytes();
		
		byte[] fullArray = UtilesArrayBytes.construyeArrayBytesFinal(ope, solu, strarr);
		
		paq = new DatagramPacket(fullArray, fullArray.length, cliente, port);
		
		sock.send(paq);
	}

	private long calcula(char op, long uno, long dos) {
		
		long resultado = 0;
		switch(op) {
			case '+':
				resultado = uno + dos;
				break;
			case '-':
				resultado = uno - dos;
				break;
			case '*':
				resultado = uno * dos;
				break;
			case '/':
				resultado = uno / dos;
				break;
		}
		
		return resultado;
		
	}
	
}
