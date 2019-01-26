package ej1_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * =====================EJERCICIO 1.2==========================
 * Programa que se conecta a un servidor que gestiona cuentas
 * matemáticas simples. Se encarga de leer información del cliente,
 * enviarsela al servidor usando el protocolo UDP y leer la
 * respuesta de vuelta del servidor.
 * 
 * @author Rubén
 *
 */

public class Cliente {

	String ip = "localhost";
	int port = 6549;
	long n1 = 0, n2 = 0;
	
	Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		new Cliente().run(args);
	}

	private void run(String[] args) {
		InetAddress host = null;
		DatagramSocket sock = null;
		DatagramPacket paqt = null;
		
		char op;
		
		try {
			if (args.length == 1) {
				host = InetAddress.getByName(args[0]);
			} else {
				host = InetAddress.getByName(ip);
			}
			sock = new DatagramSocket();
		
			while (true) {
				
				System.out.println(" - CLIENTE OPERACIONES MATEMÁTICAS - ");
				System.out.print("Seleccione operador ó comando(+, -, *, /, F, A): ");
				op = teclado.next().toUpperCase().charAt(0);

				if (op == '+' || op == '-' || op == '*' || op == '/') {		//Construyo la instrucción para el servidor.
					construirInstruccion(n1, n2);	
				} else if (op == 'F') {			//El cliente quiere finalizar la aplicación.
					System.out.println("[INFO] Finalizando la conexión...");
					sock.close();
					System.exit(0);
				} else if (op == 'A') {		//El cliente quiere abortar el servidor.
					op = 'A';
				} else {		//No se ha introducido nada válido.
					System.out.println("Sintáxis inválida.");
				}
			
				enviarPaquete(paqt, sock, op, n1, n2, host);
				
				leerPaquete(sock, paqt);
				
			}
			
		} catch (IOException ioe) {
			System.err.println("[ERROR] Error de lectura/escritura.");
		}
	}

	/**
	 * Lee del servidor un paquete con el contenido necesario de la solución.
	 * 
	 * @param sock DatagramSocket.
	 * @param paqt Paquete.
	 * @throws IOException Error de E/S.
	 */
	
	private void leerPaquete(DatagramSocket sock, DatagramPacket paqt) throws IOException {
		//Establezco un buffer.
		final int LONG_PAQUETE = 64;
		byte[] bff = new byte[LONG_PAQUETE];
		
		//Recibo el paquete.
		paqt = new DatagramPacket(bff, bff.length);
		sock.receive(paqt);
		
		//Divido el array de bytes y lo convierto a lo requerido.
		int numOp = UtilesArrayBytes.byteToInt(UtilesArrayBytes.splitArray(bff, 0, Integer.BYTES));
		long sol = UtilesArrayBytes.byteToLong(UtilesArrayBytes.splitArray(bff, Integer.BYTES, Long.BYTES));
		byte[] str = UtilesArrayBytes.splitArray(bff, (Integer.BYTES + Long.BYTES), (bff.length - (Integer.BYTES + Long.BYTES)));
		
		String msg = new String(str).trim();
		
		//Imprimo la respuesta.
		System.out.println("[RESPUESTA] Operación " + numOp + " | " + sol + " | " + msg);
		
	}

	/**
	 * Envía al servidor el paquete con la información necesaria para contar.
	 * 
	 * @param paqt Paquete a enviar.
	 * @param sock Socket por el que enviar.
	 * @param op Operación a realizar.
	 * @param uno Primer valor.
	 * @param dos Segundo valor.
	 * @param host IP del servidor.
	 * @throws IOException Error de E/S.
	 */
	
	private void enviarPaquete(DatagramPacket paqt, DatagramSocket sock, char op, long uno, long dos, InetAddress host) throws IOException {
		
		//Convierte componentes del la operación a arrays de bytes.
		byte[] operando = UtilesArrayBytes.charToByte(op);
		byte[] numeroUno = UtilesArrayBytes.longToByte(uno);
		byte[] numeroDos = UtilesArrayBytes.longToByte(dos);
		
		//Une todos arrays de bytes en uno.
		byte[] arrayCompleto = UtilesArrayBytes.construyeArrayBytesFinal(operando, numeroUno, numeroDos);
		
		//Envía el paquete.
		paqt = new DatagramPacket(arrayCompleto, arrayCompleto.length, host, port);
		sock.send(paqt);
	}

	/**
	 * Construye la instrucción para montar el paquete.
	 * 
	 * @param num1 El primer número.
	 * @param num2 El segundo número.
	 */
	
	private void construirInstruccion(long num1, long num2) {
		System.out.print("Introduce el primer número: ");
		n1 = teclado.nextLong();
		System.out.print("Introduce el segundo número: ");
		n2 = teclado.nextLong();
	}
	
}
