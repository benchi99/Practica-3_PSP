package ej1_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * =====================EJERCICIO 1.3==========================
 * Esta clase es el contenido del hilo. Se encarga de gestionar
 * todas las operaciones que realiza con el cliente que atiende
 * cada hilo.
 * 
 * @author Rub�n
 *
 */

public class Calculador extends Thread {

	private Socket cliente;
	private boolean fin = false;
	private int cuentas = 0;
	
	public Calculador(Socket cliente) {
		this.cliente = cliente;
	}
	
	private void acabar() {
		this.fin = true;
	}
	
	@Override
	public void run() {
		try {
			
			procesar(); //Se procesar�n todas las peticiones del cliente
			cliente.close();	//Finalmente se cierra la conexi�n.
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Procesa toda la l�gica del hilo.
	 * 
	 * @throws IOException Error de E/S.
	 */
	
	private void procesar() throws IOException {
		//Declaro mis objetos con los streams de entrada y salida del cliente.
		DataInputStream dis = new DataInputStream(this.cliente.getInputStream());
		DataOutputStream dos = new DataOutputStream(this.cliente.getOutputStream());
		
		while (!fin) {	//Mientras que el cliente no aborte el servidor (en este caso solo aborta el hilo).
			cuentas++;
			//Leo del cliente.
			char op = dis.readChar();
			long n1 = dis.readLong();
			long n2 = dis.readLong();
			
			if (op == 'A') {	//El cliente desea abortar el servidor.
				acabar();
			} else {
				long sol = calcula(op, n1, n2);
				enviar(dos, op, cuentas, sol, n1, n2);
			}
		}
		
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
		
		String operacion = uno + " " + op + " " + two + " = " + solucion;
		System.out.println(operacion);
		
		dos.writeInt(cuenta);
		dos.writeLong(solucion);
		dos.writeInt(operacion.length());
		System.out.println("Longitud del texto: " + operacion.length());
		dos.writeBytes(operacion);
		
		
	}
	
}
