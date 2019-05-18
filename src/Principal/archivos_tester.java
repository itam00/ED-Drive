package Principal;
import java.io.*;

import TDACola.*;


public class archivos_tester {
	public static void main(String[] args) {
		String dir="C:\\Users\\mati\\Downloads\\prueba.txt";
		Queue<String> q = readFile(dir);
		/*try {
			while(!q.isEmpty())
				System.out.println(q.dequeue());
		}
		catch(EmptyQueueException e) {}*/
		System.out.println(esValido(q));

	}
	/**
	 * Lee un archivo si es posible y retorna una cola con todos los elementos
	 * separados por lineas y espacios que habia en un archivo 
	 * @param dir direccion del archivo que se va a leer
	 * @return cola con los elementos del archivo separados por espacios y saltos de linea
	 */
	private static Queue<String> readFile(String dir) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String c = "",line;
		Queue<String> q = new ColaConArregloCircular<String>(20);
		try {
			archivo = new File(dir);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			line = br.readLine();
			while(line!=null) {
				c+=line + " ";
				line = br.readLine();
			}
			String[] etiquetas = c.split(" ");
			agregarEtiquetas(etiquetas,q);	//agrega las etiquetas a la cola
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if(null!= fr)
					fr.close();
			}
			catch(Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return q;
		
	}
	/**
	 * agrega el contenido de un arreglo de string a una cola 
	 * @param c arreglo del strings
	 * @param q cola en la que se colocaran las componentes del arreglo
	 * @return cola con las componenetes del arreglo
	 */
	private static Queue<String> agregarEtiquetas(String[] c,Queue<String> q){
		boolean agregar = false;
		for(int i=0;i<c.length;i++) {
			agregar = false;
			for(int e=0;e<c[i].length()&& !agregar;e++) {
				agregar = c[i].charAt(e) != ' ';
			}
			if(agregar)
				q.enqueue(c[i]);
		}
		return q;
	}
	/**
	 * metodo que valida si el formato de los elementos de una cola cumple
	 * con la sintaxis
	 * @param q cola con elementos a ser comprobados
	 * @return  true si el archivo es valido false en caso contrario
	 */
	
	private static boolean esValido(Queue<String> q ) {
		Boolean cumple = true;
		try {
			cumple=comprobar(q.dequeue(),"<carpeta>");
			if(esNombreValido(q.dequeue()) && cumple)
				validarArchivos(q);
			else 
				cumple = false;
			if(comprobar(q.front(),"<lista_sub_carpetas>") &&cumple) {
				q.dequeue();
				do {
					cumple = esValido(q);
				}
				while(!comprobar(q.front(),"</lista_sub_carpetas>"));
				q.dequeue();
			}
			
			cumple=  comprobar(q.dequeue(),"</carpeta>") && cumple;
				
		}
		catch(EmptyQueueException e) {
			return false;
		}
		return cumple;
	}
	/**
	 * comprueba que los string pasados por parametro son iguales sin importar
	 * las tabulaciones y espacios que esten delante
	 * @param c cadena que puede tener tabulaciones y espacios
	 * @param resultado	cadena a comparar
	 * @return si las cadenas son iguales verdadero caso contrario falso
	 */
	private static boolean comprobar(String c,String resultado) {
		int i=0;
		String cadena="";
		while(i<c.length() && (c.charAt(i) == '\t' || c.charAt(i)== ' '))
			i++;
		while(i<c.length()) {
			cadena+=c.charAt(i);
			i++;
		}
		return cadena.equals(resultado);
	}
	/**
	 * verifica si la sintaxis del nombre de una carpeta es correcta
	 * @param c nombre de la carpeta (puede contener o no tabulaciones)
	 * @return verdadero si la sintaxis es correcta falso en caso contrario
	 */
	private static boolean esNombreValido(String c) {
		String abre="";
		String cierra="";
		String nombre="";
		int i=0;
		while(i<c.length() && (c.charAt(i)=='\t' ||c.charAt(i)== ' '))
				i++;
		while (i<c.length() && c.charAt(i) !='>') {
			abre=abre+c.charAt(i);
			i++;
		}
		abre=abre+'>';
		i++;
		while ( i<c.length() && c.charAt(i)!='<') {
			nombre=nombre+c.charAt(i);
			i++;
		}
	
		while (i<c.length() && c.charAt(i)!='>') {
			cierra=cierra+c.charAt(i);
			i++;
		}
	
		cierra=cierra+'>';
		
		return abre.equals("<nombre>") && cierra.equals("</nombre>") && !nombre.equals("");
	}
	/**
	 * verifica si los elementos de la cola q respetan la sintaxis de los archivos
	 * desencola un archivo si y solo si este verifica la sintaxis
	 * @param c cola en la que se verifica la sintaxis de sus elementos
	 */
	public static void validarArchivos(Queue<String> c) {
		String aux = "";
		boolean toReturn = true;
		try {
			while(toReturn) {
				if (!c.isEmpty())
					aux = c.front();
				else
					toReturn = false;
				String abre = "<archivo>";
				String cierra = "</archivo>";
				String nombre = "";
				boolean encontre = false;
				int k = 0;
				int i = 0;
				int j = 0;
				while(i < aux.length() && !encontre) {
					if(aux.charAt(i) == '<')
						encontre = true;
					else
							i++;
				}
				if(!encontre)
					toReturn = false;
				while(toReturn && k < 9) {
						if(aux.charAt(i) == abre.charAt(k) && i < aux.length()) {
							i++;
							k++;
						}
						else
							toReturn = false;
					}
					while(toReturn && i < aux.length() && aux.charAt(i) != '<') {
					nombre += aux.charAt(i);
					i++;
				}
				if(nombre == "")
					toReturn = false;
				while(toReturn && j < 10) {
					if(aux.charAt(i) == cierra.charAt(j) && i < aux.length()) {
						i++;
						j++;
						}
					else
						toReturn = false;	
				}
				if(toReturn)
					c.dequeue();
			}
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
	}
}
