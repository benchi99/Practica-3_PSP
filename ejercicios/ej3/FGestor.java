package ej3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class FGestor extends Thread {

	private Socket cliente = null;
	DataInputStream dis = null;
	DataOutputStream dos = null;
	private boolean fin = false;
	
	private void acabar() {
		fin = true;
	}
	
	public FGestor(Socket cliente) {
		this.cliente = cliente;	
	}
	
	@Override
	public void run() {
		try {
			dis = new DataInputStream(cliente.getInputStream());
			dos = new DataOutputStream(cliente.getOutputStream());
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
}

