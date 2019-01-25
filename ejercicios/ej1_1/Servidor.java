package ej1_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * =====================EJERCICIO 1.1==========================
 * Aplicaci�n servidor encargada de gestionar peticiones de
 * c�lculo de operaciones matem�ticas utilizando el protocolo
 * TCP/IP. La aplicaci�n lee informaci�n del cliente conectado,
 * realiza la operaci�n pedida, y la devuelve.
 * 
 * @author Rub�n
 *
 */

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
			//Abre una conexi�n y espera a que un cliente se conecte.
			svSock = new ServerSocket(PORT);
			cliente = svSock.accept();	//Acepta la conexi�n.
			System.out.println("[INFO] Conexi�n aceptada: " + cliente.getInetAddress());
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
					enviar(salida, op, cuentas, solucion, n1, n2);	//Se env�a la informaci�n de vuelta.
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	/**
	 * Lee del cliente la informaci�n necesaria para las cuentas.
	 * 
	 * @param e DataInputStream que lee del cliente.
	 * @return Char que determina la operaci�n.
	 * @throws IOException Error de E/S.
	 */
	
	private char leer(DataInputStream e) throws IOException {
		char op = e.readChar();
		n1 = e.readLong();
		n2 = e.readLong();
		
		return op;
	}
	
	/**
	 * Dados dos valores y un operando devuelve la soluci�n
	 * 
	 * @param op Operando.
	 * @param uno Primer valor.
	 * @param dos Segundo valor.
	 * @return Soluci�n de la cuenta.
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
	 * Env�a la informaci�n al cliente.
	 * 
	 * @param dos DataOuputStream que env�a la informaci�n.
	 * @param op Operaci�n de la cuenta
	 * @param cuenta El n�mero de cuenta correspondiente.
	 * @param solucion Soluci�n de la cuenta.
	 * @param uno Primer valor.
	 * @param two Segundo valor.
	 * @throws IOException Error de E/S.
	 */
	
	private void enviar(DataOutputStream dos, char op, int cuenta, long solucion, long uno, long two) throws IOException {
		//Hago la operaci�n.
		String operacion = uno + " " + op + " " + two + " = " + solucion;
		
		//Envio la informaci�n de vuelta al cliente.
		dos.writeInt(cuenta);
		dos.writeLong(solucion);
		dos.writeInt(operacion.length());
		dos.writeBytes(operacion);
	}
}
