package ej1_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
			
			procesar();
			cliente.close();
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void procesar() throws IOException {
		DataInputStream dis = new DataInputStream(this.cliente.getInputStream());
		DataOutputStream dos = new DataOutputStream(this.cliente.getOutputStream());
		
		while (!fin) {
			cuentas++;
			char op = dis.readChar();
			long n1 = dis.readLong();
			long n2 = dis.readLong();
			
			if (op == 'A') {
				acabar();
			} else {
				long sol = calcula(op, n1, n2);
				enviar(dos, op, cuentas, sol, n1, n2);
			}
		}
		
	}
	
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
