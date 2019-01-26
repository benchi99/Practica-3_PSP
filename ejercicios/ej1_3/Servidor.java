package ej1_3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * =====================EJERCICIO 1.3==========================
 * Aplicaci�n servidor encargada de gestionar peticiones de
 * c�lculo de operaciones matem�ticas utilizando el protocolo
 * TCP/IP. La aplicaci�n lee informaci�n del cliente conectado,
 * realiza la operaci�n pedida, y la devuelve. Esta variante
 * soporta varios clientes simult�neos utilizando hilos. 
 * 
 * @author Rub�n
 *
 */

public class Servidor {

	final int PORT = 6549;
	
	ServerSocket svSock;
	
	public static void main(String[] args) {
		new Servidor().run();
	}
	
	private void run() {
		
		try {
			svSock = new ServerSocket(PORT);
			/*
			 * Sabiendo que el servidor va a estar en ejecuci�n
			 * permanentemente este bucle no deber�a acabar nunca.
			 */
			while (true) { 
				//Acepta un cliente
				Socket cliente = esperarCliente();
			
				//Se lanza un hilo que gestionar� a ese cliente.
				Calculador cl = new Calculador(cliente);
				cl.start();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	
	}
	
	/**
	 * Espera a que un cliente establezca conexi�n y devuelve el Socket.
	 * 
	 * @return Socket del cliente conectado.
	 * @throws IOException Error de E/S.
	 */
	
	private Socket esperarCliente() throws IOException {
		Socket sk;
		
		System.out.println("[INFO] Esperando un cliente...");
		sk = svSock.accept();
		System.out.println("[INFO] Conexi�n aceptada - " + sk.getInetAddress());
		
		return sk;
	}
	
	
}
