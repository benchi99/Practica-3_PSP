package ej2;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteArchivos {

	String ip = "localhost";
	int port = 6549;
	Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		new ClienteArchivos().run(args);
	}

	private void run(String[] args) {
		
		if (args.length == 1) {
			ip = args[0];
		}
		
		Socket socket = null;
		DataInputStream entradaNet = null;
		DataOutputStream salidaNet = null;
		BufferedOutputStream bufferSalidaArchivo = null;
		
		try {
			socket = new Socket(ip, port);
			entradaNet = new DataInputStream(socket.getInputStream());
			salidaNet = new DataOutputStream(socket.getOutputStream());
		
			while (true) {
				System.out.print(" -- Programa de transferencia de archivos --\nEscriba el nombre o ruta del archivo en el servidor que desea descargar: ");
				String ruta = teclado.next();
				
				if (ruta.toLowerCase().equals("salir")) {
					salidaNet.writeInt(0);
					break;
				} else {
					salidaNet.writeInt(ruta.length());
					salidaNet.writeBytes(ruta);				
	
					byte resultado = entradaNet.readByte();
					
					if (resultado == 1) {
						System.out.println("[INFO] Archivo encontrado!");
						System.out.println("[INFO] Comenzando descarga...");
						File rutaArchivo = new File(ruta);
						bufferSalidaArchivo = new BufferedOutputStream(new FileOutputStream(new File(rutaArchivo.getName())));
						long longitudArchivo = entradaNet.readLong();
						System.out.println("[INFO] Se van a descargar " + longitudArchivo + " bytes.");
						byte[] buffer = new byte[1000];
						System.out.println("[INFO] Descargando...");
						int leidos = entradaNet.read(buffer, 0, 1000);
						long total = leidos;
						while (true) {
							System.out.println(porcentajeCopia(longitudArchivo, total)+"% descargado.");
							System.out.println("[DEBUG] Escribiendo a archivo...");
							bufferSalidaArchivo.write(buffer, 0, leidos);
							System.out.println("[DEBUG] Leyendo del servidor...");
							leidos = entradaNet.read(buffer, 0, 1000);
							total = total + leidos;
							System.out.println("[DEBUG] Leyendo status de copia...");
							byte status = entradaNet.readByte();
							System.out.println("[DEBUG] Comprobando estado de copia...");
							if (status == 1) {
								break;
							}
						}
						System.out.println("Descarga del archivo finalizada.");
						bufferSalidaArchivo.close();
					} else if (resultado == 0) {
						System.err.println("[ERROR] El archivo no existe en el servidor.");
					} else {
						System.err.println("[ERROR] Hubo un error inesperado.");
					}
				}
			}
			
			System.out.println("[INFO] Terminando aplicación...");
			entradaNet.close();
			salidaNet.close();
			bufferSalidaArchivo.close();
			socket.close();
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	private long porcentajeCopia(long longitudTotal, long longitudLeido) {
		return (100*longitudLeido)/longitudTotal;
	}
	
}
