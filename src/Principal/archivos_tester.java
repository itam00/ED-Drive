package Principal;
import java.io.*;  

import java.util.Iterator;
import java.util.regex.Pattern;

import TDAArbol.*;
import TDACola.*;
import TDADiccionario.DiccionarioHashAbierto;
import TDADiccionario.Dictionary;
import TDALista.InvalidPositionException;
import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;
import TDAMapeo.*;


public class archivos_tester {
	private Tree<Pair<String,PositionList<String>>> arbol;
	
	public archivos_tester(String dir) throws InvalidFileLocationException,InvalidFileException {
		arbol= new Arbol<Pair<String,PositionList<String>>>();
		//dir="C:\\Users\\mati\\Downloads\\prueba.txt";
		Queue<String> q = readFile(dir);

		boolean val = valido(q);
		if (!val) {
			arbol=null;
			throw new InvalidFileException("El formato del archivo no es valido");
		}
	}
	
	/**
 	* Valida si el archivo leido es valido comprobando su sintaxis y comprobando que la cola donde estaban todos los 
 	* elementos haya quedado vacia. En caso de ser valido agrega al arbol todos los directorios con sus archivos
 	* correspondientes
 	* @param q cola con el archivo a validar
 	* @return Verdadero si es valido, falso en caso contrario
 	*/
	public boolean valido(Queue<String> q) {
		boolean cumple= false;
		try {
			Pair<String, PositionList<String>> primerPar = new Pair<String,PositionList<String>>("",new ListaDE<String>()); // es el directorio raíz del arbol
			arbol.createRoot(primerPar); // crea la raiz del arbol con "primerPar".
			cumple = esValido(q,arbol.root()) && q.isEmpty(); // Devuelve verdadero si el archivo tiene el formato valido y la pila queda vacía (si no queda vacía la pila significa que hay mas de 1 carpeta principal.
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
			throw new InvalidFileLocationException("La ubicacion del archivo no es valida");
		}
		finally {
			try {
				if(null!= fr)
					fr.close();
			}
			catch(Exception e2) {}
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
		catch(EmptyQueueException | InvalidPositionException e) {
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
	private boolean comprobar(String c,String resultado) {
		c=c.replaceAll("\t","");
		c=c.replaceAll("\\s","");
		return c.equals(resultado);
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
		c=c.replaceAll("\\s", "");
		c=c.replaceAll("\t", "");
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
	 * @param l lista donde se guardarán los nombres de los archivos del directorio donde se encuentran.
	 */
	
	public void validarArchivos(Queue<String> c, PositionList<String> l) {
		String aux="";
		boolean cumple=true;
		try {
			while (cumple) {
				if (!c.isEmpty())
					aux=c.front();
				else
					cumple=false;
				
				String abre="";
				String cierra="";
				String nombre="";
				int i=0;
				aux=aux.replaceAll("\\s", "");
				aux=aux.replaceAll("\t", "");
				while (i<aux.length() && aux.charAt(i) !='>') {
					abre=abre+aux.charAt(i);
					i++;
				}
				abre=abre+'>';
				i++;
				while ( i<aux.length() && aux.charAt(i)!='<') {
					nombre=nombre+aux.charAt(i);
					i++;
				}
			
				while (i<aux.length() && aux.charAt(i)!='>') {
					cierra=cierra+aux.charAt(i);
					i++;
				}
				cierra=cierra+'>';
				//System.out.println(abre.equals("<archivo>") && cierra.equals("</archivo>") && !nombre.equals("") && (aux.charAt(punto-1)!='>' && aux.charAt(punto+1)!='<'));
				try {
					validarExtencion(nombre);
				}
				catch(InvalidFileNameException e) {
					cumple = false;
				}
				cumple=abre.equals("<archivo>") && cierra.equals("</archivo>") && !nombre.equals("") &&cumple; 
				//System.out.println(validarExtencion(nombre));
				if (cumple) {
					c.dequeue();
					l.addLast(nombre);
				}
			}
		}
		catch(EmptyQueueException e) {//si no cumple saldra del ciclo terminando asi el metodo
		}
	}
	/**
	 * valida en nombre de un archivo comprobando que tenga extension
	 * @param nombre
	 * @return
	 */
	public static void validarExtencion(String nombre) throws InvalidFileNameException{
		boolean cumple= false;
		int i = 0;
		if(nombre.length() > 3 && nombre.charAt(0) != '.' && nombre.charAt(nombre.length()-1) != '.') {
			while (i < nombre.length() && !cumple) {
				if(nombre.charAt(i) == '.') {
					cumple=true;
				}
				i++;
			}
		}
		if(!cumple)
			throw new InvalidFileNameException("El nombre del archivo no es valido");
	}
	
	/**
	 * Agrega un directorio "nombreD2" dentro del directorio correspondiente a la direccion "direccionD1".
	 * @param direccionD1 Indica la direccion donde se encuentra el Directorio dentro del cual se agrega uno nuevo.
	 * @param nombreD2 Indica el nombre del nuevo directorio a agregar.
	 */
	
	public void agregarArchivo(String direccionD,String nombreA) throws InvalidFileLocationException,InvalidFileNameException{
		validarExtencion(nombreA);			//en caso de que el nombre no sea valido lanza una exception
		
		Position<Pair<String,PositionList<String>>> directorio=buscar(direccionD); //busca el directorio y se lo asigna a "directorio".
		directorio.element().getValue().addLast(nombreA); //agrega el archivo en el directorio.

	}
	
	public void eliminarArchivo(String direccionD) throws InvalidFileLocationException{
		String separador=Pattern.quote("\\");
		String[] partes=direccionD.split(separador); // agrega cada string separado por "/" en una componente distinta del arreglo.
		String nombreArchivo=partes[partes.length-1]; //guarda el nombre del archivo a eliminar.
		boolean removi=false;
		Position<String> pos;

		try {
			Position<Pair<String,PositionList<String>>> directorio=buscarArchivo(partes,0,arbol.root()); //busca el directorio y lo asigna a "directorio".
			Iterator<Position<String>> it= directorio.element().getValue().positions().iterator();
			while (it.hasNext() && !removi) { //busca la posicion de la lista de valores donde se encuentra el archivo a eliminar.
				pos=it.next();
				if (pos.element().equals(nombreArchivo)) {
					directorio.element().getValue().remove(pos); //elimina el archivo de la lista de valores.
					removi=true;
				}	
			}
		}
		catch (EmptyTreeException | InvalidPositionException e) {
			System.out.println (e.getMessage());
		}
		if (!removi)
			throw new InvalidFileLocationException("direccion invalida");
	}
	
	public void agregarDirectorio(String dir,String nombreD2) throws InvalidFileLocationException{
		String separador=Pattern.quote("\\");
		String[] parts= dir.split(separador); //agrega cada string separado por "/" en una componente distinta del arreglo
		Position<Pair<String, PositionList<String>>> agregardirectorio=null;
		
		if(arbol.isEmpty()) {
			if(parts.length!=1)
				throw new InvalidFileLocationException("La direccion no es valida en el sistema de archivos");
			else
				arbol.createRoot(new Pair<String,PositionList<String>>(nombreD2,new ListaDE<String>()));
				//si el arbol estaba vacio entonces se debe crear la raiz con un nuevo par
		}
		else {
			try {
				agregardirectorio=buscar(dir); //busca el directorio donde se debe agregar el nuevo.
				arbol.addLastChild(agregardirectorio, new Pair<String,PositionList<String>> (nombreD2, new ListaDE<String>())); //agrega un directorio "nombreD2" como nuevo hijo "buscarDirectorio"
			}
			catch (InvalidFileLocationException | InvalidPositionException e) {
				throw new InvalidFileLocationException("La direccion no es valida en el sistema de archivos");
			}
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
	
	public void eliminarDirectorio(String dir) throws InvalidFileLocationException {
		try {
			Position<Pair<String,PositionList<String>>> eliminar=buscar(dir);
			eliminarSucesores(eliminar);
			arbol.removeExternalNode(eliminar);
		}
		catch (InvalidPositionException e) {
			//NO DEBERIA PASAR ya que la posicion es del arbol														
		}
	}
	/**
	 * Mueve un directorio y otodo su contenido a una direccion pasada por parametro
	 * @param dir1 dirección donde se encuentra el directorio que va a ser movido.
	 * @param dir2 dirección donde se colocará el directorio a mover.
	 */
	@SuppressWarnings("unused")
	public void moverDirectorio(String dir1,String dir2) throws InvalidFileLocationException{
		String separador=Pattern.quote("\\");
		Position<Pair<String,PositionList<String>>> origen=buscar(dir1);
		Position<Pair<String,PositionList<String>>> destino=buscar(dir2);
		
		copiarDirectorio(origen,destino);
		eliminarDirectorio(dir1);
	}
	
	private void copiarDirectorio(Position<Pair<String,PositionList<String>>> origen,Position<Pair<String,PositionList<String>>> destino) {
		try {
			arbol.addFirstChild(destino, origen.element());
			for(Position<Pair<String,PositionList<String>>> p:arbol.children(origen)) {
				copiarDirectorio(p,destino);
			}
		} catch (InvalidPositionException e) {
			//no deberia pasar ya que es una posicion del mismo arbol, por lo tanto es valida
		}
	}
	/**
	 * recorre todo el arbol buscando cada directorio y archivo en él.
	 * @return Un par de enteros donde la primer componente representa la cantidad de directorios y la segunda la cantidad de archivos.
	 */
	@SuppressWarnings("unused")
	public Pair<Integer,Integer> cantidadDeDirectoriosYArchivos() {
		int cantDirectorios=0;
		int cantArchivos=0;
		for (Pair<String,PositionList<String>> p: arbol) {
			cantDirectorios++;
			for (String arch:p.getValue())
				cantArchivos++;
		}
		Pair<Integer,Integer> toreturn= new Pair<Integer,Integer>(cantDirectorios,cantArchivos);
		return toreturn;
	}
	
	/**
	 * Busca en el arbol el directorio pasado a traves del parametro position y lo retorna.
	 * @param partes Es un String donde se encuentran los nombres de los directorios antecesores al buscado.
	 * @param indice Entero que indice qué String dentro el arreglo "partes" se debe comparar con el directorio "position".
	 * @param position Directorio a comparar con el String en el subíndice "indice", si es el mismo la direccion del archivo es correcta.
	 * @return Una posicion que encapsula el directorio donde se buscará el que directorio que se está buscando.
	 * @throws InvalidFileLocationException En caso de que el directorio buscado no exista en el arbol (la direccion es incorrecta).
	 */
	
	public Position<Pair<String,PositionList<String>>> buscar(String dir)throws InvalidFileLocationException {
		Position<Pair<String,PositionList<String>>> toReturn;
		String separador=Pattern.quote("\\");
		String[] partes=dir.split(separador);
		try {
			toReturn = buscar(partes,0,arbol.root());
		}
		catch(EmptyTreeException e ) {
			throw new InvalidFileLocationException("La direccion no es valida en el sistema de archivos");
		}
		return toReturn;
	}
	private Position<Pair<String,PositionList<String>>> buscar(String[] partes,int indice, Position<Pair<String, PositionList<String>>> position) throws InvalidFileLocationException {
		Iterator<Position<Pair<String, PositionList<String>>>> it=null;
		try {
			it= arbol.children(position).iterator();
		}
		catch (InvalidPositionException e) {
			//no deberia pasar ya que es un nodo del mismo arbol
		}
		boolean encontrado=false;
		Position<Pair<String, PositionList<String>>> toreturn=null;
		Position<Pair<String, PositionList<String>>> hijo;
		if (partes[indice].equals(position.element().getKey()))
			if(partes.length==1) {
				toreturn=position;
				encontrado=true;
			}
			else{
				indice++;
				while (it.hasNext() && !encontrado) {
					hijo=it.next();
					if (hijo.element().getKey().equals(partes[indice])) {
						if (indice==partes.length-1 || partes.length==1)
							toreturn=hijo;
						else {
							toreturn= buscar(partes,indice,hijo);
						}
						encontrado=true;
					}
				}
			}
		if (!encontrado)
			throw new InvalidFileLocationException ("La direccion no es valida en el sistema de archivos");
		return toreturn;
	}

	/**
	 * Busca en el arbol el archivo ubicado dentro del directorio pasado a traves del parametro position y lo retorna.
	 * @param partes Es un String donde se encuentran los nombres de los directorios antecesores al buscado.
	 * @param indice Entero que indice qué String dentro el arreglo "partes" se debe comparar con el directorio "position".
	 * @param position Directorio a comparar con el String en el subíndice "indice", si es el mismo la direccion del archivo es correcta.
	 * @return Una posicion que encapsula el Directorio donde se buscará el archivo.
	 * @throws InvalidFileLocationException En caso de que el directorio buscado no exista en el arbol (la direccion es incorrecta).
	 */
	
	private Position<Pair<String,PositionList<String>>> buscarArchivo(String[] partes,int indice, Position<Pair<String, PositionList<String>>> position) throws InvalidFileLocationException {
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
		if (partes[indice].equals(position.element().getKey()))
			if(partes.length==1) {
				toreturn=position;
				encontrado=true;
			}
			else{
				indice++;
				while (it.hasNext() && !encontrado) {
					hijo=it.next();
					if (hijo.element().getKey().equals(partes[indice])) {
						if (indice==partes.length-2)
							toreturn=hijo;
						else {
							toreturn= buscarArchivo(partes,indice,hijo);
						}
						encontrado=true;
					}
				}
			}
		if (!encontrado)
			throw new InvalidFileLocationException ("La direccion no es valida en el sistema de archivos");
		return toreturn;
	}
	
	@SuppressWarnings("unused")
	public PositionList<String> listadoPorNiveles(){
		Queue<Position<Pair<String,PositionList<String>>>> aListar= new ColaConArregloCircular<Position<Pair<String,PositionList<String>>>>(30);
		Position<Pair<String,PositionList<String>>> directorio;
		PositionList<String> toreturn= new ListaDE<String>();
		PositionList<String> listaArchivos=null;
		Position<String> archivo;
		toreturn.addLast("<");
		try {
			aListar.enqueue(arbol.root());
			while(!aListar.isEmpty()){
				directorio=aListar.dequeue();
				if (arbol.isRoot(directorio))
					toreturn.addLast(directorio.element().getKey()+", /, ");
				else
					toreturn.addLast(", "+directorio.element().getKey()+", /, ");
				if (!directorio.element().getValue().isEmpty()) {
					listaArchivos=directorio.element().getValue();
				}
				for (Position<Pair<String,PositionList<String>>>pos: arbol.children(directorio))
					aListar.enqueue(pos);
			}
			toreturn.addLast(">");
		}
		catch (InvalidPositionException| EmptyTreeException | EmptyQueueException e) {
			System.out.println(e.getMessage());
		}
		return toreturn;
	}
	/** 
	 * crea un listado por profundidad de los directorios del arbol, guarda la direccion de los directorios como clave
	 * y la profundidad como valor en el mapeo
	 * @return mapeo donde se cuardaran las direccion es y la profundidad de dicha direccion.
	 */
	public Map<String,Integer> listadoPorProfundidad(){
		Map<String,Integer> toReturn=new MapeoHashAbierto<String,Integer>();
		try {
			if(!arbol.isEmpty())
				listadoProfundidad("",0,arbol.root(),toReturn);
		}catch(EmptyTreeException e) {}//no deberia pasar
		return toReturn;
	}
	/**
	 * metodo auxiliar de listadoPorProfundidad()
	 * @param dir direccion del directorio
	 * @param prof profunidad del directorio
	 * @param pos posicion sobre la que se encuentra 
	 * @param m	mapeo donde se añadiran los directorios y la profunidad de cada uno
	 */
	private void listadoProfundidad(String dir,int prof,Position<Pair<String,PositionList<String>>> pos,Map<String,Integer> m){
		dir+=pos.element().getKey()+" \\ ";
		try {
			m.put(dir, prof);
			prof++;
			for(Position<Pair<String,PositionList<String>>> e: arbol.children(pos)) {
				listadoProfundidad(dir,prof,e,m);
			}
		} catch (InvalidKeyException | InvalidPositionException e) {}
	}
	
	public String listadoProf() {
		String c="";
		for(Entry<String,Integer> e:listadoPorProfundidad().entries()) {
			c+="Direccion: "+e.getKey()+", Profundidad: "+e.getValue()+"\n";
		}
		return c;
	}
	
	/**
	 * Listado por extencion que retornar un Diccionario con las archivos
	 * @return Diccionario con extencion como clave y nombre como valor
	 */
	public Dictionary<String,String> listadoPorExtencion(){
		Dictionary<String,String> toReturn=new DiccionarioHashAbierto<String,String>();
		try {
			if(!arbol.isEmpty())
				listadoExtencion(arbol.root(),toReturn);
		}catch(EmptyTreeException e) {}//no deberia pasar
		return toReturn;
	}
	
	/**
	 * Metodo auxiliar recursivo para Listado por extencion
	 * @param pos Posicion con los hijos a insertar en el diccionario
	 * @param D Diccionario con los archivos a agregar
	 */
	private void listadoExtencion(Position<Pair<String, PositionList<String>>> pos, Dictionary<String,String> D) {
		try {
			for(String e : pos.element().getValue()){
				D.insert(obtenerExtencion(e), e);
			}
			for(Position<Pair<String,PositionList<String>>> e: arbol.children(pos)) {
				listadoExtencion(e,D);
			}
		} catch (InvalidPositionException | TDADiccionario.InvalidKeyException e) {}
	}
	
	/**
	 * Metodo auxiliar para obtener la extencion de un archivo
	 * @param c Archivo a obtener extencion
	 * @return Extencion del archivo
	 */
	private String obtenerExtencion(String c) {
		String toReturn = "";
		int i = c.length() - 1;
		while(c.charAt(i) != '.') {
			toReturn = c.charAt(i) + toReturn;
			i--;
		}
		return toReturn;
	}
	
	public String mostrarListadoExtencion() {
		Dictionary<String,String> D = listadoPorExtencion();
		String toReturn = "";
		String extencion = "";
		for(TDADiccionario.Entry<String, String> e : D.entries()) {
			if(!extencion.equals(e.getKey())) {
				extencion = e.getKey();
				toReturn += ""+extencion+":\n";
			}
			toReturn = toReturn+"   "+e.getValue()+"\n";
		}
		return toReturn;
	}
	
	/**
	 * Metodo que transforma los directorios y los archivos del arbol a una cadena de caracteres para
	 * poder mostrar su estado actual.
	 * @return	cadena de caracteres que permite visualizar el estado actual del arbol, si 
	 * el arbol esta vacio retorna nulo.
	 */
	public String generarArbolString() {
		String toReturn="";
		try {
			if(!arbol.isEmpty()) {
				toReturn =  generarString("",arbol.root());
				toReturn = toReturn.replaceFirst("├","┌");
			}
		}
		catch(EmptyTreeException e ) {}	
		return toReturn;	
	}
	/**
	 * metodo auxiliar del metodo generarArbolString()
	 * @param i	cadena que se encargara de agregar "────" a cada directorio y archivo.
	 * @param p	posicion del arbol a partir de la cual se agregaran las carpetas y archivos al string.
	 * @return	cadena con el estado actual del arbol.
	 */
	private String generarString(String i,Position<Pair<String,PositionList<String>>> p) {
		String c = "├"+i;
		c+="■ "+p.element().getKey()+"\n";
		for(String e:p.element().getValue()) {
			c+="├────"+i+"× "+e+"\n";
		}
		c+="┼";
		try {
			for(Position<Pair<String, PositionList<String>>> e: arbol.children(p)) {
				c+="\n"+generarString("────"+i,e);
			}
		}
		catch(InvalidPositionException e) {}
		return c;	
	}
}
