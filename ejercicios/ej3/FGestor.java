package ej3;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * ===================EJERCICIO 3==========================
 * Esta clase es el contenido del hilo. Encargada de gestionar
 * todas las operaciones que realiza con el cliente que atiende
 * cada hilo.
 * 
 * @author Rub�n
 *
 */

public class FGestor extends Thread {

	private Socket cliente = null;
	DataInputStream dis = null;
	DataOutputStream dos = null;
	
	public FGestor(Socket cliente) {
		this.cliente = cliente;	
	}
	
	@Override
	public void run() {
		try {
			atender();
			cliente.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Gestiona todas las actividades relacionadas con 
	 * atender las peticiones del cliente.
	 * 
	 * @throws IOException Error de E/S.
	 */
	private void atender() throws IOException {
		//Se declaran los streams de entrada y salida.
		dis = new DataInputStream(cliente.getInputStream());
		dos = new DataOutputStream(cliente.getOutputStream());
		
		while (true) {
			System.out.println("[INFO] Esperando petici�n de " + cliente.getInetAddress() + "...");
			int longFi = dis.readInt(); //Leo la longitud de la ruta escrita por el cliente
			if (longFi == 0) { //Si no ha escrito nada, el cliente se ha finalizado, terminamos el bucle.
				break;
			} else {
				File archivoDeseado = crearFileABuscar(dis, longFi);	//Creo objeto File con el objeto a transmitir.
				System.out.println("[INFO] Buscando el archivo " + archivoDeseado.getName() + "...");
				if (archivoDeseado.exists()) {	//Si existe, se transfiere.
					transferirArchivo(archivoDeseado, dos);
				} else {						//Si no, da un error.
					System.err.println("[ERROR] Archivo no encontrado.");
					dos.writeByte(0);
				}
			}
		}
		
		cerrar(dis, dos , cliente);	
	}
	
	/**
	 * Dados un DataInputStream y una longitud, devuelve un objeto File
	 * con la ruta le�da del cliente.
	 * 
	 * @param dis El InputStream en cuesti�n.
	 * @param longitud Longitud del archivo.
	 * @return El objeto File del archivo deseado.
	 * @throws IOException Si se produce un error de E/S.
	 */
	
	private File crearFileABuscar(DataInputStream dis, int longitud) throws IOException {
		byte[] fi = new byte[longitud];
		//Leo la ruta
		for (int i = 0; i < fi.length; i++) {
			fi[i] = dis.readByte();
		}
		String ruta = new String(fi);
		
		//Devuelvo el objeto File
		return new File(ruta);
	}
	
	/**
	 * Transfiere el archivo deseado si existe al cliente.
	 * 
	 * @param archivo El archivo que se desea enviar.
	 * @param dos El OutputStream que env�a 
	 * @throws IOException
	 */
	
	private void transferirArchivo(File archivo, DataOutputStream dos) throws IOException {
		System.out.println("[INFO] Archivo encontrado. Comenzando la transferencia...");
		dos.writeByte(1);		//Notifico al cliente que el archivo ha sido encontrado.
		dos.writeLong(archivo.length());	//Env�o al cliente la longitud del archivo.
		FileInputStream fis = new FileInputStream(archivo);
		BufferedInputStream bis = new BufferedInputStream(fis);
		byte[] buffer = new byte[1000];
		//Comienza la transferencia del archivo al cliente.
		System.out.println("[INFO] Transfiriendo...");
		int leido = bis.read(buffer, 0, buffer.length);
		int total = 0;
		while (total < archivo.length()) {
			dos.write(buffer, 0, leido);
			dos.writeByte(0);
			total = total + leido;
			bis.read(buffer, 0, buffer.length);
		}
		dos.write(buffer, 0, leido);
		dos.writeByte(1); 	//Notifico que el archivo ha sido completamente transferido.
		System.out.println("[INFO] Transferencia finalizada.");
		bis.close();
	}
	
	/**
	 * Cierra los sockets y los streams existentes.
	 * 
	 * @param dis DataInputStream a cerrar.
	 * @param dos DataOutputStream a cerrar.
	 * @param sv ServerSocket a cerrar.
	 * @param sk Socket a cerrar.
	 * @throws IOException
	 */
	
	private void cerrar(DataInputStream dis, DataOutputStream dos, Socket sk) throws IOException {
		System.out.println("[INFO] El cliente ha terminado la conexi�n. Finalizando...");
		dis.close();
		dos.close();
		sk.close();	
	}
	
}

