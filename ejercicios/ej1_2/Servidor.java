package ej1_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * =====================EJERCICIO 1.2==========================
 * Aplicación servidor encargada de gestionar peticiones de
 * cálculo de operaciones matemáticas utilizando el protocolo
 * UDP. La aplicación lee información del cliente conectado,
 * realiza la operación pedida, y la devuelve.
 * 
 * @author Rubén
 *
 */

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
			sock = new DatagramSocket(port);	//Crea el socket.
		} catch (SocketException ske) {
			System.err.println("[ERROR] Hubo un problema con el socket. Terminando...");
			System.exit(1);
		} 
		
		try {
			
			//Mantiene la cuenta de las operaciones que se realizan.
			int numOp = 1;
			
			while (true) {
				
				leerPaquete(sock, paq);
				
				if (op == 'A') {	//Si el cliente ha enviado una A, el servidor se aborta.
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

	/**
	 * Lee paquetes recibidos por el DatagramSocket.
	 * 
	 * @param sock DatagramSocket en cuestión.
	 * @param paq Paquete a leer.
	 * @throws IOException Error de E/S.
	 */
	
	private void leerPaquete(DatagramSocket sock, DatagramPacket paq) throws IOException {
		//Definimos el array de bytes.
		byte[] bff = new byte[TAMANIO_PAQ];
		paq = new DatagramPacket(bff, TAMANIO_PAQ);
		System.out.println("[INFO] Esperando paquete...");
		//Recibimos el paquete así como la IP y Puerto del Cliente.
		sock.receive(paq);
		System.out.println("[INFO] Paquete recibido de " + paq.getAddress() + ". Obteniendo contenido...");
		cliente = paq.getAddress();
		cliPort = paq.getPort();
		
		//Convierto el array a los tipos correspondientes para poder operar.
		op = UtilesArrayBytes.byteToChar(UtilesArrayBytes.splitArray(bff, 0, Character.BYTES));
		n1 = UtilesArrayBytes.byteToLong(UtilesArrayBytes.splitArray(bff, Character.BYTES, Long.BYTES));
		n2 = UtilesArrayBytes.byteToLong(UtilesArrayBytes.splitArray(bff, (Character.BYTES + Long.BYTES), Long.BYTES));
		
	}

	/**
	 * Envía la solución al cliente.
	 * 
	 * @param op Operando.
	 * @param n1 Número 1.
	 * @param n2 Número 2.
	 * @param sol Solución
	 * @param numOp Número de operación.
	 * @param paq Paquete.
	 * @param sock Socket.
	 * @param cliente IP del cliente.
	 * @param port Puerto.
	 * @throws IOException Error de E/S.
	 */
	private void enviar(char op, long n1, long n2, long sol, int numOp, DatagramPacket paq, DatagramSocket sock, InetAddress cliente, int port) throws IOException {
	
		//Convierto lo necesario a arrays de bytes.
		byte[] ope = UtilesArrayBytes.intToByte(numOp);
		byte[] solu = UtilesArrayBytes.longToByte(sol);
		String str = n1 + " " + op + " " + n2 + " = " + sol;
		byte[] strarr = str.getBytes();
		
		//Construyo el array de bytes completo.
		byte[] fullArray = UtilesArrayBytes.construyeArrayBytesFinal(ope, solu, strarr);
		
		//Lo devuelvo al cliente.
		paq = new DatagramPacket(fullArray, fullArray.length, cliente, port);
		sock.send(paq);
	}

	/**
	 * Dados dos valores y un operando devuelve la solución.
	 * 
	 * @param op Operando.
	 * @param uno Primer valor.
	 * @param dos Segundo valor.
	 * @return Solución de la cuenta.
	 */
	
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
