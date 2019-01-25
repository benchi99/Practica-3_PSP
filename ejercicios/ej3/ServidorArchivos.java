package ej3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ===================EJERCICIO 3==========================
 * 
 * Programa que se dedica a aceptar clientes y asignarles un
 * hilo que gestionará todas las funciones y peticiones de los
 * clientes.
 * 
 * @author Rubén
 *
 */

public class ServidorArchivos {

	int port = 6549;
	
	ServerSocket server = null;
	
	public static void main(String[] args) {
		new ServidorArchivos().run();
	}
	
	private void run() {
		
		Socket cliente = null;
		
		try {
			server = new ServerSocket(port);
			/*
			 * Sabiendo que el servidor va a estar en ejecución
			 * permanentemente este bucle no debería acabar nunca.
			 */
			while(true) {
				//Acepta un cliente
				cliente = esperaCliente();
				
				//Se lanza un hilo que gestionará a ese cliente
				FGestor gest = new FGestor(cliente);
				gest.start();
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
	
	
	private Socket esperaCliente() throws IOException {
		Socket sk;
		
		System.out.println("[INFO] Esperando un cliente...");
		sk = server.accept();
		System.out.println("[INFO] Conexión aceptada - " + sk.toString());
		
		return sk;
		
	}
	
	
}