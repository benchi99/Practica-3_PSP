package ej1_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	final int PORT = 6549;
	long n1, n2;
	
	public static void main(String[] args) {
		new Servidor().run();
	}
	
	private void run() {
		//Se declaran los objetos necesarios.
		ServerSocket svSock = null;
		Socket cliente = null;
		DataInputStream entrada = null;
		DataOutputStream salida = null;
		int cuentas = 0;
		
		try {
			//Abre una conexión y espera a que un cliente se conecte.
			svSock = new ServerSocket(PORT);
			cliente = svSock.accept();	//Acepta la conexión.
			System.out.println("[INFO] Conexión aceptada: " + cliente.getInetAddress());
			//Se crean los streams de entrada y salida.
			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());
		
			while (true) {
				cuentas++;
				char op = leer(entrada);	//Lee del cliente
				if (op == 'A') {
					break;					//El cliente ha pedido abortar el servidor.
				} else {
					long solucion = calcula(op, n1, n2);	//Se hace el calculo deseado
					enviar(salida, op, cuentas, solucion, n1, n2);	//Se envía la información de vuelta.
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	/**
	 * Lee del cliente la información necesaria para las cuentas.
	 * 
	 * @param e DataInputStream que lee del cliente.
	 * @return Char que determina la operación.
	 * @throws IOException Error de E/S.
	 */
	
	private char leer(DataInputStream e) throws IOException {
		char op = e.readChar();
		n1 = e.readLong();
		n2 = e.readLong();
		
		return op;
	}
	
	/**
	 * Dados dos valores y un operando devuelve la solución
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
	
	/**
	 * Envía la información al cliente.
	 * 
	 * @param dos DataOuputStream que envía la información.
	 * @param op Operación de la cuenta
	 * @param cuenta El número de cuenta correspondiente.
	 * @param solucion Solución de la cuenta.
	 * @param uno Primer valor.
	 * @param two Segundo valor.
	 * @throws IOException Error de E/S.
	 */
	
	private void enviar(DataOutputStream dos, char op, int cuenta, long solucion, long uno, long two) throws IOException {
		String operacion = uno + " " + op + " " + two + " = " + solucion;
		System.out.println(operacion);
		
		dos.writeInt(cuenta);
		dos.writeLong(solucion);
		dos.writeInt(operacion.length());
		System.out.println("Longitud del texto: " + operacion.length());
		dos.writeBytes(operacion);
	}
}
