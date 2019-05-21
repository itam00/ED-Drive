package Principal;
import java.io.*;  
import java.util.Iterator;

import TDAArbol.*;
import TDACola.*;
import TDALista.InvalidPositionException;
import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;


public class archivos_tester {
	private Tree<Pair<String,PositionList<String>>> arbol;
	
	public archivos_tester(String dir) {
		arbol= new Arbol<Pair<String,PositionList<String>>>();
		dir="C:\\Users\\Alan\\Desktop\\je.txt";
		try {
			Queue<String> q = readFile(dir);
			arbol= new Arbol<Pair<String,PositionList<String>>>();
			if (valido(q)) {
				Iterator<Pair<String,PositionList<String>>> it= arbol.iterator();
				Pair<String,PositionList<String>> par;
				while (it.hasNext()) {
					par=it.next();
					System.out.print ("("+par.getKey()+",");
					for (String s:par.getValue())
						System.out.print("/"+s);
					System.out.println(")");
				}
				AgregarDirectorio("e/e2","creado");
				eliminarDirectorio("e/e4");
				agregarArchivo("e/e2","hola7");
				System.out.println ("--------------------------");
				System.out.println ("habiendo creado el directorio 'creado' y eliminado el directorio 'e4' (y e5 por ser su hijo)");
				Iterator<Pair<String,PositionList<String>>> it2= arbol.iterator();
				Pair<String,PositionList<String>> par2;
				while (it2.hasNext()) {
					par2=it2.next();
					System.out.print ("("+par2.getKey()+",");
					for (String s:par2.getValue())
						System.out.print("/"+s);
					System.out.println(")");
				}
			}
			else
				arbol=null;
		}
		catch (Exception e) {}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		archivos_tester p= new archivos_tester("C:\\Users\\Alan\\Desktop\\je.txt");
		/**try {
			String dir="C:\\Users\\Alan\\Desktop\\je.txt";
			Queue<String> q = readFile(dir);
			Queue<String> nombres= new ColaConArregloCircular<String>(20);
			boolean crear;
			Tree<Pair<String,ListaDE<String>>>arbol= new Arbol<Pair<String,ListaDE<String>>>();
			if (valido(q,arbol)) {
				Iterator<Pair<String,ListaDE<String>>> it= arbol.iterator();
				Pair<String,ListaDE<String>> par;
				while (it.hasNext()) {
					par=it.next();
					System.out.print ("("+par.getKey()+",");
					for (String s:par.getValue())
						System.out.print("/"+s);
					System.out.println(")");
				}
				AgregarDirectorio(arbol, "e/e2");
			}
			else
				arbol=null;
		}

		catch(Exception e) {
			System.out.println("algo salio mal");
		}
		*/

	}
	
	/**
 	* Valida si el archivo leido es valido comprobando su sintaxis y comprobando que la cola donde estaban todos los 
 	* elementos haya quedado vacia. En caso de ser valido agrega al arbol todos los directorios con sus archivos
 	* correspondientes
 	* @param q cola con el archivo a validar
 	* @param arbol Arbol al cual se le agregaran los directorios
 	* @return Verdadero si es valido, falso en caso contrario
 	*/
	public boolean valido(Queue<String> q) {
		boolean cumple= false;
		try {
			Pair<String, PositionList<String>> primerPar = new Pair<String,PositionList<String>>("",new ListaDE<String>());
			arbol.createRoot(primerPar);
			cumple = esValido(q,arbol.root()) && q.isEmpty();
		}
		catch(InvalidOperationException | EmptyTreeException e) {
			System.out.println("arreglar esto pls: " + e.getMessage());	//ESTO HAY Q SACARLO DPS !!!!!!!!!!!!!!!!!!!!
		}
		
		return cumple;			
	}
	
	
	/**
	 * Lee un archivo si es posible y retorna una cola con todos los elementos
	 * separados por lineas y espacios que habia en un archivo 
	 * @param dir direccion del archivo que se va a leer
	 * @return cola con los elementos del archivo separados por espacios y saltos de linea
	 */
	private Queue<String> readFile(String dir) throws InvalidFileLocationException{
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
				throw new InvalidFileLocationException("La unificacion del archivo no es valida");
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
	private Queue<String> agregarEtiquetas(String[] c,Queue<String> q){
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
	
	private boolean esValido(Queue<String> q, Position<Pair<String, PositionList<String>>> position) {
		Boolean cumple = true;
		Pair<String,PositionList<String>> nuevoPar;
		Position<Pair<String,PositionList<String>>> hijo;
		try {
			cumple=comprobar(q.dequeue(),"<carpeta>");
			if(esNombreValido(q.dequeue(),position.element()) && cumple) {	//se valida el nombre y se agrega el nombre al par
				validarArchivos(q,position.element().getValue());	//se obtiene la lista del nodo 
				
			}
			else 
				cumple = false;
			if(comprobar(q.front(),"<lista_sub_carpetas>") &&cumple) {				
				q.dequeue();			
				do {
					nuevoPar = new Pair<String,PositionList<String>>("",new ListaDE<String>());
					hijo = arbol.addLastChild(position, nuevoPar);									//y se le añade como padre nodo y un hijo
					cumple = esValido(q,hijo);
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
	private boolean comprobar(String c,String resultado) {
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
	private boolean esNombreValido(String c, Pair<String, PositionList<String>> pair) {
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
		pair.setKey(nombre);
		return abre.equals("<nombre>") && cierra.equals("</nombre>") && !nombre.equals("");
	}
	/**
	 * verifica si los elementos de la cola q respetan la sintaxis de los archivos
	 * y agrega el nombre del archivo en la cola "nom" con una A al principio.
	 * desencola un archivo si y solo si este verifica la sintaxis
	 * @param c cola en la que se verifica la sintaxis de sus elementos
	 */
	public void validarArchivos(Queue<String> c, PositionList<String> l) {
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
	
	/**
	 * Agrega un directorio "nombreD2" dentro del directorio correspondiente a la direccion "direccionD1".
	 * @param direccionD1 Indica la direccion donde se encuentra el Directorio dentro del cual se agrega uno nuevo.
	 * @param nombreD2 Indica el nombre del nuevo directorio a agregar.
	 */
	
	private void agregarArchivo(String direccionD,String nombreA) {
		String separador="/";
		String[] partes= direccionD.split(separador);
		try {
			Position<Pair<String,PositionList<String>>> directorio=buscar(partes,0,arbol.root());
			directorio.element().getValue().addLast(nombreA);
		}
		catch (EmptyTreeException | InvalidFileLocationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void AgregarDirectorio(String direccionD1,String nombreD2) {
		String separador="/";
		String[] parts= direccionD1.split(separador);
		Position<Pair<String, PositionList<String>>> agregardirectorio=null;
		try {
			agregardirectorio=buscar(parts,0,arbol.root());
			arbol.addLastChild(agregardirectorio, new Pair<String,PositionList<String>> (nombreD2, new ListaDE<String>()));
		}
		catch (EmptyTreeException | InvalidFileLocationException | InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Elimina los sucesores del directorio "padre" pasado como padre.
	 * @param padre Es el directorio del cual se quieren eliminar sus hijos y los sucesores de estos.
	 * @throws InvalidPositionException En caso de que no sea valido el directorio pasado por parametro.
	 */
	
	private void eliminarSucesores(Position<Pair<String,PositionList<String>>> padre) throws InvalidPositionException {
		for (Position<Pair<String,PositionList<String>>> p:arbol.children(padre)) {
			eliminarSucesores(p);
			arbol.removeExternalNode(p);
		}
	}
	
	/**
	 * Elimina del arbol el Directorio correspondiente a la direccionD1
	 * @param direccionD1 String que indica la dirección donde se encuentra el Directorio que se quiere eliminar.
	 */
	
	private void eliminarDirectorio(String direccionD1) {
		String separador="/";
		String[] partes= direccionD1.split(separador);
		try {
			Position<Pair<String,PositionList<String>>> eliminar=buscar(partes,0,arbol.root());
			eliminarSucesores(eliminar);
			arbol.removeExternalNode(eliminar);
		}
		catch (EmptyTreeException |InvalidPositionException | InvalidFileLocationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Busca en el arbol el directorio pasado a traves del parametro position y lo retorna.
	 * @param partes Es un String donde se encuentran los nombres de los directorios antecesores al buscado.
	 * @param indice Entero que indice qué String dentro el arreglo "partes" se debe comparar con el directorio "position".
	 * @param position Directorio a comparar con el String en el subíndice "indice", si es el mismo la direccion del archivo es correcta.
	 * @return Una posicion que encapsula el Directorio buscado.
	 * @throws InvalidFileLocationException En caso de que el directorio buscado no exista en el arbol (la direccion es incorrecta).
	 */
	
	private Position<Pair<String,PositionList<String>>> buscar(String[] partes,int indice, Position<Pair<String, PositionList<String>>> position) throws InvalidFileLocationException {
		Iterator<Position<Pair<String, PositionList<String>>>> it=null;
		try {
			it= arbol.children(position).iterator();
		}
		catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
		boolean encontrado=false;
		Position<Pair<String, PositionList<String>>> toreturn=null;
		Position<Pair<String, PositionList<String>>> hijo;
		if (partes[indice].equals(position.element().getKey())) {
			indice++;
			while (it.hasNext() && !encontrado) {
				hijo=it.next();
				if (hijo.element().getKey().equals(partes[indice])) {
					if (indice==partes.length-1)
						toreturn=hijo;
					else {
						toreturn= buscar(partes,indice,hijo);
					}
					encontrado=true;
				}
		}
		}
		if (!encontrado)
			throw new InvalidFileLocationException ("direccion no valida");
		return toreturn;
	}

	/**
	private static void preOrden(Tree<Pair<String,ListaDE<String>>> arbol, Position<Pair<String,ListaDE<String>>> visitado,ListaDE<Pair<String,ListaDE<String>>> toreturn) {
		toreturn.addLast(visitado.element());
		System.out.print(visitado.element().getKey()+"//");
		try {
			for (Position<Pair<String,ListaDE<String>>> h:arbol.children(visitado))
				preOrden(arbol,h,toreturn);
		}
		catch (InvalidPositionException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static ListaDE<Pair<String,ListaDE<String>>> listadoPreOrden(Tree<Pair<String,ListaDE<String>>> arbol){
		ListaDE<Pair<String,ListaDE<String>>> toreturn= new ListaDE<Pair<String,ListaDE<String>>>();
		if(!arbol.isEmpty())
			try {
				preOrden(arbol,arbol.root(),toreturn);
			}
		catch (EmptyTreeException e) {
			System.out.println (e.getMessage());
		}
		return toreturn;
	}
	*/
}
