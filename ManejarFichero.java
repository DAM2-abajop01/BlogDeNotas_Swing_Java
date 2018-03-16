import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ManejarFichero {

	File rutaFichero;
	File ficheroReciente;
	String rutaFicheroReciente= "src"+File.separator+"recientes"+File.separator+"recientes.txt";

	public ManejarFichero() {
		rutaFichero = new File(System.getProperty("user.home"));// este propert coje la ruta del user
		ficheroReciente = new File(rutaFicheroReciente);

	}

	public File getRutaFichero() {
		return rutaFichero;
	}

	public void setRutaFichero(File rutaFichero) {
		this.rutaFichero = rutaFichero;
	}

	/*
	 * Devuelve el fichero completo en forma de cadena
	 * 
	 * @return una cadena que contiene el fichero apunando por la rutadelficheo
	 */
	public String cargarFicher() throws IOException {
		String archivo = "";

		BufferedReader br = new BufferedReader(new FileReader(rutaFichero));
		String linea;
		while ((linea = br.readLine()) != null) {
			archivo += linea + "\n";
		}

		br.close();

		return archivo;
	}

	/**
	 * Guardar fichero apuntado en {@link #rutaFichero}
	 * 
	 * @param contenido
	 * @throws IOException
	 */
	public void guardarFichero(String contenido) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFichero));
		bw.write(contenido);
		bw.close();

	}
	
	/**
	 * Guardar fichero de archivos abiertos recientemente
	 * 
	 * @param contenido
	 * @throws IOException
	 */
	public void guardarFicheroRecientes(String contenido) throws IOException {
		ArrayList<String> rutas = new ArrayList<>();		
		rutas = obtenerLineasDelFichero();
		BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroReciente));
		for (int i = 0; i < rutas.size(); i++) {
			bw.write(rutas.get(i)+"\n");
		}
		bw.write(contenido+"\n");
		bw.close();
	}
	
	public ArrayList<String> obtenerLineasDelFichero() throws IOException{
		ArrayList<String> rutas = new ArrayList<>();		
			if(ficheroReciente.exists()) {
			BufferedReader br = new BufferedReader(new FileReader(ficheroReciente));
			String linea=  br.readLine();
			while ((linea) != null) {
				rutas.add(linea);
				linea=  br.readLine();
			}
			br.close();	
			}						
		return rutas;
	}

}
