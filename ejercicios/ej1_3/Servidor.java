package ej1_3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * =====================EJERCICIO 1.3==========================
 * Aplicación servidor encargada de gestionar peticiones de
 * cálculo de operaciones matemáticas utilizando el protocolo
 * TCP/IP. La aplicación lee información del cliente conectado,
 * realiza la operación pedida, y la devuelve. Esta variante
 * soporta varios clientes simultáneos utilizando hilos. 
 * 
 * @author Rubén
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
			 * Sabiendo que el servidor va a estar en ejecución
			 * permanentemente este bucle no debería acabar nunca.
			 */
			while (true) { 
				//Acepta un cliente
				Socket cliente = esperarCliente();
			
				//Se lanza un hilo que gestionará a ese cliente.
				Calculador cl = new Calculador(cliente);
				cl.start();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	
	}
	
	/**
	 * Espera a que un cliente establezca conexión y devuelve el Socket.
	 * 
	 * @return Socket del cliente conectado.
	 * @throws IOException Error de E/S.
	 */
	
	private Socket esperarCliente() throws IOException {
		Socket sk;
		
		System.out.println("[INFO] Esperando un cliente...");
		sk = svSock.accept();
		System.out.println("[INFO] Conexión aceptada - " + sk.toString());
		
		return sk;
	}
	
	
}
