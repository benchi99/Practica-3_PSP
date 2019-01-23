package ej1_3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	final int PORT = 6549;
	
	ServerSocket svSock;
	
	public static void main(String[] args) {
		new Servidor().run();
	}
	
	private void run() {
		
		try {
			svSock = new ServerSocket(PORT);
			while (true) {
				Socket cliente = esperarCliente();
			
				Calculador cl = new Calculador(cliente);
				cl.start();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	
	}
	
	private Socket esperarCliente() throws IOException {
		Socket sk;
		
		System.out.println("[INFO] Esperando un cliente...");
		sk = svSock.accept();
		System.out.println("[INFO] Conexión aceptada - " + sk.toString());
		
		return sk;
	}
	
	
}
