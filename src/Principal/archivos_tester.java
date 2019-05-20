package Principal;
import java.io.*;

import TDAArbol.*;
import TDACola.*;
import TDALista.InvalidPositionException;
import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;


public class archivos_tester {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			String dir="C:\\Users\\mati\\Desktop\\je.txt";
			Queue<String> q = readFile(dir);
			Queue<String> nombres= new ColaConArregloCircular<String>(20);
			boolean crear;
	
			/*crear=valido(q,nombres);
			System.out.println(crear);
			if (crear) { // En esta parte deberiamos crear cada directorio y ponerlo en el arbol.
				Tree<Pair<String,PositionList<String>>> arbol= new Arbol<Pair<String,PositionList<String>>>();
				Pair<String,PositionList<String>> directorio=null;
				Position<Pair<String,PositionList<String>>> adentroDe=null;
			}
			else
				nombres=null;*/
		}
		catch(Exception e) {
			System.out.println("algo salio mal");
		}

	}
	
	/**
 	* Valida si el archivo leido es valido comprobando su sintaxis y comprobando que la cola donde estaban todos los 
 	* elementos haya quedado vacia. En caso de ser valido agrega al arbol todos los directorios con sus archivos
 	* correspondientes
 	* @param q cola con el archivo a validar
 	* @param arbol Arbol al cual se le agregaran los directorios
 	* @return Verdadero si es valido, falso en caso contrario
 	*/
	public static boolean valido(Queue<String> q, Tree<Pair<String,ListaDE<String>>> arbol) {
		boolean cumple= false;
		try {
			Pair<String, ListaDE<String>> primerPar = new Pair<String,ListaDE<String>>("",new ListaDE<String>());
			arbol.createRoot(primerPar);
			cumple = esValido(q,arbol.root(),arbol) && q.isEmpty();
		}
		catch(InvalidOperationException | EmptyTreeException e) {
			System.out.println("arreglar esto pls: " + e.getMessage());	//ESTO HAY Q SACARLO DPS !!!!!!!!!!!!!!!!!!!!
		}
		
		if(!cumple)
			arbol = new Arbol<Pair<String,ListaDE<String>>>();
		
		return cumple;			
	}
	
	
	/**
	 * Lee un archivo si es posible y retorna una cola con todos los elementos
	 * separados por lineas y espacios que habia en un archivo 
	 * @param dir direccion del archivo que se va a leer
	 * @return cola con los elementos del archivo separados por espacios y saltos de linea
	 */
	private static Queue<String> readFile(String dir) throws InvalidFileLocation{
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
				throw new InvalidFileLocation("La unicacion del archivo no es valida");
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
	 * metodo que auxiliar quevalida si el formato de los elementos de una cola cumple
	 * con la sintaxis. Ademas agrega el nombre del directorio y los nombres los
	 * archivos a la lista del par a Par contenido en el TNodo. 
	 * @param q cola con elementos a ser comprobados
	 * @param nodo Nodo al cual se le agregara el nombre del directorio y los "archivos" al Par que este contiene
	 * @return  true si el archivo es valido false en caso contrario
	 */
	
	private static boolean esValido(Queue<String> q, Position<Pair<String,ListaDE<String>>> pos, Tree<Pair<String,ListaDE<String>>> arbol) {
		Boolean cumple = true;
		Pair<String,ListaDE<String>> nuevoPar;
		Position<Pair<String,ListaDE<String>>> hijo;
		try {
			cumple=comprobar(q.dequeue(),"<carpeta>");
			if(esNombreValido(q.dequeue(),pos.element()) && cumple) {	//se valida el nombre y se agrega el nombre al par
				validarArchivos(q,pos.element().getValue());	//se obtiene la lista del nodo 
				
			}
			else 
				cumple = false;
			if(comprobar(q.front(),"<lista_sub_carpetas>") &&cumple) {				
				q.dequeue();			
				do {
					nuevoPar = new Pair<String,ListaDE<String>>("",new ListaDE<String>());
					hijo = arbol.addLastChild(pos, nuevoPar);									//y se le añade como padre nodo y un hijo
					cumple = esValido(q,hijo,arbol);
				}
				while(!comprobar(q.front(),"</lista_sub_carpetas>") && cumple);
				q.dequeue();
			}
			
			cumple=  comprobar(q.dequeue(),"</carpeta>") && cumple;
				
		}
		catch(EmptyQueueException e) {
			return false;
		}
		catch(InvalidPositionException e) {
			System.out.println("algo salio mal");		//ESTO HAY QUE SACARLO DPS ES SOLO PARA SABER SI AL CREAR EL ARBOL NO SALTA NADA
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
	 * y agrega el nombre en la cola "nom" con una N al principio.
	 * @param c nombre de la carpeta (puede contener o no tabulaciones)
	 * @return verdadero si la sintaxis es correcta falso en caso contrario
	 */
	private static boolean esNombreValido(String c, Pair<String,ListaDE<String>> par) {
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
		par.setKey(nombre);
		return abre.equals("<nombre>") && cierra.equals("</nombre>") && !nombre.equals("");
	}
	/**
	 * verifica si los elementos de la cola q respetan la sintaxis de los archivos
	 * y agrega el nombre del archivo en la cola "nom" con una A al principio.
	 * desencola un archivo si y solo si este verifica la sintaxis
	 * @param c cola en la que se verifica la sintaxis de sus elementos
	 */
	public static void validarArchivos(Queue<String> c, PositionList<String> l) {
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
				if(toReturn) {
					c.dequeue();
					l.addLast(nombre);
				}
			}
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
	}
	
}
