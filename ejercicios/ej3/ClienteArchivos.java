package ej3;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * 
 * ===================EJERCICIO 3==========================
 * 
 * Programa que se conecta a un servidor del que descargará
 * un archivo especificado por el usuario.
 * 
 * @author Rubén
 *
 */

public class ClienteArchivos {

	String ip = "localhost";
	int port = 6549;
	Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Lee de argumentos si existe una IP.
		new ClienteArchivos().run(args);
	}

	private void run(String[] args) {
		//Establece la IP por el valor de args[0]. Para pruebas exportando el programa.
		if (args.length == 1) {
			ip = args[0];
		}
		
		Socket socket = null;
		DataInputStream entradaNet = null;
		DataOutputStream salidaNet = null;
		
		try {
			//Inicializo objetos.
			socket = new Socket(ip, port);
			entradaNet = new DataInputStream(socket.getInputStream());
			salidaNet = new DataOutputStream(socket.getOutputStream());
		
			while (true) {
				System.out.print(" -- Programa de transferencia de archivos --\nEscriba el nombre o ruta del archivo en el servidor que desea descargar: ");
				String ruta = teclado.nextLine();		//Lee del teclado la ruta del archivo a descargar.
				
				if (ruta.toLowerCase().equals("")) {	//Si no hay nada, sale del bucle y cierra el programa.
					salidaNet.writeInt(0);				
					break;
				} else {
					
					enviarRuta(ruta, salidaNet);		//Envía la ruta al servidor.
	
					byte resultado = entradaNet.readByte();	//Leo de vuelta el resultado del servidor
					
					if (resultado == 1) {				//El archivo existe.
						System.out.println("[INFO] Archivo encontrado!");
						System.out.print("[?] Escriba ruta de destino: ");
						File destino = new File(teclado.nextLine());	//Leo una ruta de destino para el archivo.
						descarga(destino, entradaNet);	//Descargo el archivo.
						
					} else if (resultado == 0) {		//No existe el archivo.
						System.err.println("[ERROR] El archivo no existe en el servidor.");
					} else {
						System.err.println("[ERROR] Hubo un error inesperado.");	//Error inesperado.
					}
				}
			}
			
			//Se ha salido del bucle, por lo que se desea cerrar la aplicación.
			System.out.println("[INFO] Terminando aplicación...");
			entradaNet.close();
			salidaNet.close();
			socket.close();
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}

	/**
	 * Dados un archivo de destino y un InputStream, guardará la información que este reciba en un archivo.
	 * 
	 * @param destino El destino del archivo.
	 * @param entradaNet El InputStream en concreto.
	 * @throws IOException Error de E/S.
	 */
	
	private void descarga(File destino, DataInputStream entradaNet) throws IOException {
		System.out.println("[INFO] Comenzando descarga...");
		BufferedOutputStream bufferSalidaArchivo = new BufferedOutputStream(new FileOutputStream(destino));
		long longitudArchivo = entradaNet.readLong();	//Leo la longitud del archivo.
		System.out.println("[INFO] Se van a descargar " + longitudArchivo + " bytes.");
		byte[] buffer = new byte[1000];
		//Comienza la descarga.
		System.out.println("[INFO] Descargando...");
		int leidos = entradaNet.read(buffer, 0, 1000);
		while (true) {
			bufferSalidaArchivo.write(buffer, 0, leidos);
			byte status = entradaNet.readByte();
			if (status == 1) {
				break;
			}
			leidos = entradaNet.read(buffer, 0, 1000);
			
		}
		System.out.println("Descarga del archivo finalizada.");
		bufferSalidaArchivo.close();
	}

	/**
	 * Dado una ruta y un OutputStream, envía al servidor la ruta que debe buscar.
	 * 
	 * @param ruta Ruta en concreto.
	 * @param salidaNet OutputStream en cuestión.
	 * @throws IOException Error de E/S.
	 */
	
	private void enviarRuta(String ruta, DataOutputStream salidaNet) throws IOException {
		salidaNet.writeInt(ruta.length());
		salidaNet.writeBytes(ruta);	
	}
	
}
