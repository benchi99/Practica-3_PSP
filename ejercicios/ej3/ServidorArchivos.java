package ej3;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorArchivos {

	int port = 6549;
	
	public static void main(String[] args) {
		new ServidorArchivos().run();
	}
	
	private void run() {
		ServerSocket server = null;
		Socket cliente = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		
		try {
			server = new ServerSocket(port);
			System.out.println("[INFO] Esperando la conexión...");
			cliente = server.accept();
			dis = new DataInputStream(cliente.getInputStream());
			dos = new DataOutputStream(cliente.getOutputStream());
			
			while (true) {
				System.out.println("[INFO] Esperando petición de " + cliente.getInetAddress() + "...");
				int longFi = dis.readInt();
				if (longFi == 0) {
					break;
				} else {
					File archivoDeseado = crearFileABuscar(dis, longFi);
					System.out.println("[INFO] Buscando el archivo " + archivoDeseado.getName() + "...");
					if (archivoDeseado.exists()) {
						transferirArchivo(archivoDeseado, dos);
					} else {
						System.err.println("[ERROR] Archivo no encontrado.");
						dos.writeByte(0);
					}
				}
			}
			
			cerrar(dis, dos ,server, cliente);
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Dados un DataInputStream y una longitud, devuelve un objeto File
	 * con la ruta leída del cliente.
	 * 
	 * @param dis El InputStream en cuestión.
	 * @param longitud Longitud del archivo.
	 * @return El objeto File del archivo deseado.
	 * @throws IOException Si se produce un error de E/S.
	 */
	
	private File crearFileABuscar(DataInputStream dis, int longitud) throws IOException {
		byte[] fi = new byte[longitud];
		for (int i = 0; i < fi.length; i++) {
			fi[i] = dis.readByte();
		}
		String ruta = new String(fi);
		
		return new File(ruta);
	}
	
	/**
	 * Transfiere el archivo deseado si existe al cliente.
	 * 
	 * @param archivo El archivo que se desea enviar.
	 * @param dos El OutputStream que envía 
	 * @throws IOException
	 */
	
	private void transferirArchivo(File archivo, DataOutputStream dos) throws IOException {
		System.out.println("[INFO] Archivo encontrado. Comenzando la transferencia...");
		dos.writeByte(1);
		dos.writeLong(archivo.length());
		FileInputStream fis = new FileInputStream(archivo);
		BufferedInputStream bis = new BufferedInputStream(fis);
		byte[] buffer = new byte[1000];
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
		dos.writeByte(1);
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
	
	private void cerrar(DataInputStream dis, DataOutputStream dos, ServerSocket sv, Socket sk) throws IOException {
		System.out.println("[INFO] El cliente ha terminado la conexión. Finalizando...");
		dis.close();
		dos.close();
		sk.close();
		sv.close();
	
	}
}