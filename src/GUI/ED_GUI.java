package GUI;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Controlador.InvalidFileException;
import Controlador.InvalidFileLocationException;
import Controlador.InvalidFileNameException;
import Controlador.Logica;
import Controlador.Pair;
import TDADiccionario.Dictionary;
import TDALista.*;
import TDAMapeo.Entry;
import TDAMapeo.Map;

/**
 * Clase encargada de la interfaz grafica.
 * @author Mati, Alan y Nico
 */
public class ED_GUI extends JPanel implements ActionListener {
	private final String GENERATE_COMMAND = "Generar jerarquia";
	private final String ADD_COMMAND = "A�adir archivo";
	private final String REMOVE_COMMAND = "Remover archivo";
	private final String MOVE_D_COMMAND = "Mover directorio";
	private final String ADD_D_COMMAND = "A�adir directorio";
	private final String REMOVE_D_COMMAND = "Remover directorio";
	private final String LEVEL_COMMAND = "Listado por nivel";
	private final String EXTENSION_COMMAND = "Listado por extension";
	private final String DEPTH_COMMAND = "Listado por profundidad";
	private final String TOTAL_COMMAND = "Cantidad de carpetas y archivos";
  
	private Toolkit toolkit;
	private JTextArea cadenaArbol;
	private final int cantBotones = 10;
	private String[] nombres;
	private Logica tester;
	private JPanel panelBotones;
	private JButton botones[];
	private JScrollPane sc;
	private JLabel label;
	private JOptionPane panel;
	private JTextArea a;

	/**
	 * Contructor de la clase ED_GUI
	 */
	public ED_GUI() {
	    super(new BorderLayout()); 
	    
	    //cosas para el listener
	    toolkit = Toolkit.getDefaultToolkit();
	    panel = new JOptionPane();
	    panel.setFont(new Font("Century Gothic",Font.BOLD, 20) );
	    label = new JLabel();
	    label.setFont(new Font("Century Gothic",Font.BOLD, 12) );
	    a = new JTextArea();
    	a.setOpaque(false);
  		a.setFont(new Font("Century Gothic",Font.BOLD, 12) );
	    
	    nombres = new String[cantBotones]; //Botones a usar por el usuario
	    nombres[0] =GENERATE_COMMAND;
	    nombres[1] =ADD_COMMAND;
	    nombres[2] =REMOVE_COMMAND;
	    nombres[3] =ADD_D_COMMAND;
	    nombres[4] =REMOVE_D_COMMAND;
	    nombres[5] =MOVE_D_COMMAND;
	    nombres[6] =LEVEL_COMMAND;
	    nombres[7] =EXTENSION_COMMAND;
	    nombres[8] =DEPTH_COMMAND;
	    nombres[9] =TOTAL_COMMAND;
	    
	    
	    botones = new JButton[cantBotones];
	    
	    for (int i = 0; i < nombres.length; i++) { // Se asignan los actionListener a los botones y se los deshabilita
	    	botones[i] = new JButton(nombres[i]);
	        botones[i].setActionCommand(nombres[i]);
	        botones[i].addActionListener(this);
	        botones[i].setEnabled(false);
	        botones[i].setFont( new Font("Century Gothic",Font.BOLD, 16) );
	
		}
	    botones[3].setEnabled(true);
	
	    botones[0].setEnabled(true); // Se habilitan los botones para a�adir jerarquia y directorio
	    panelBotones = new JPanel(new GridLayout(cantBotones, 0));
	    cadenaArbol = new JTextArea();
	    cadenaArbol.setEditable(false);
	    cadenaArbol.setFont(new Font("Century Gothic",Font.BOLD, 20) );
	    
	    sc = new JScrollPane(cadenaArbol,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    
	    for(int i=0;i<nombres.length;i++) {
	    	panelBotones.add(botones[i]);
	    }    
	    
	    add(sc);
	    add(panelBotones, BorderLayout.EAST);
	    setPreferredSize(new Dimension(840,480));
  }



  @SuppressWarnings("static-access")
  /**
   * Metodo al cual se ejecutara al presionar alguno de los botones
   */
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    
    try {
	    if(GENERATE_COMMAND.equals(command)) { // Generar jerarquia
	    	String direccion = panel.showInputDialog("Ingrese la direccion del archivo");
	    	if(direccion!=null) { 
		    	String separador= Pattern.quote(".");
		    	String [] partes=direccion.split(separador);
		    	boolean cumple=false;
		    	if (partes.length>1)
		    		cumple = (partes[partes.length-2].charAt(partes[partes.length-1].length()-1)!='\\' &&partes[partes.length-1].equals("ed19"));
		    	if(direccion!=null && cumple && direccion.length()>0) {
			    		tester = new Logica(direccion);
						for(int i=0;i<botones.length;i++) {
							botones[i].setEnabled(true);		
						}
		    	}
		    	else
		    		throw new InvalidFileLocationException("La direccion no es valida");
		    	}
	    	}
	    
	    if (ADD_COMMAND.equals(command)) { // A�adir archivo
	    	String nombre = panel.showInputDialog("Ingrese el nombre del archivo que desea a�adir al sistema");
	    	if(nombre!=null&&nombre.length()>0) {
	    		tester.validarExtencion(nombre);	//se valida el nombre del archivo antes de preguntar por su direccion
	    		String direccion = panel.showInputDialog("Ingrese la direccion donde desea crearlo");			
	    		if(direccion!=null&&direccion.length()>0)	
	    			tester.agregarArchivo(direccion,nombre);
	    	}	
	    }
	    
	    if (REMOVE_COMMAND.equals(command)) { // Remover archivo
	    	String direccion = panel.showInputDialog("Ingrese la direccion del archivo a remover incluyendo el nombre del archivo");
	    	if(direccion!=null&&direccion.length()>0)
	    		tester.eliminarArchivo(direccion);
	    	
	    } 
	    if (ADD_D_COMMAND.equals(command)) { // A�adir directorio
	    	String nombre = panel.showInputDialog("Ingrese el nombre del directorio que desea a�adir al sistema");
	    	if(nombre!=null&&nombre.length()>0) { 
		    	if (tester==null ||tester.isEmptyTree()) {
		    		tester=new Logica();
		    		tester.agregarDirectorio("", nombre);
		    		for (int i = 0; i < nombres.length; i++)
		    			botones[i].setEnabled(true);
		    	}
		    	else {
		    		String direccion = panel.showInputDialog("Ingrese la direccion donde desea crearlo");
		    		if(direccion!=null)
		    			tester.agregarDirectorio(direccion,nombre);
				}
	    	}

	    }
	    if (REMOVE_D_COMMAND.equals(command)) { // Remover directorio
	    	String direccion = panel.showInputDialog("Ingrese la direccion del directorio a remover incluyendolo");
	    	if(direccion!=null&&direccion.length()>0)
				tester.eliminarDirectorio(direccion);
	    	if (tester.isEmptyTree()) {
	    		for (int i=0;i<nombres.length;i++)
	    			botones[i].setEnabled(false);
	    		botones[0].setEnabled(true);
	    		botones[3].setEnabled(true);
	    	}
	    }
	    if(MOVE_D_COMMAND.equals(command)) { // Mover
	    	String origen = panel.showInputDialog("Ingrese la direccion del directorio a mover");
	    	if(origen!=null&&origen.length()>0) {
	    		String destino = panel.showInputDialog("Ingrese el destino del directorio");
	    		if(destino!=null&&origen.length()>0)
	    			tester.moverDirectorio(origen, destino);
	    	}
	    }
  	} catch (InvalidFileLocationException|InvalidFileException|InvalidFileNameException e1) {
  		toolkit.beep();
  		label.setText(e1.getMessage());
  		
		panel.showMessageDialog(null, label,"Error",JOptionPane.ERROR_MESSAGE);
	} 
    catch(Exception w2) {
    	label.setText(w2.getMessage());
		panel.showMessageDialog(null, label,"Error",JOptionPane.ERROR_MESSAGE);
    }
   
    
    if(TOTAL_COMMAND.equals(command)) { // Cantidad de archivos y directorios
    	Pair<Integer,Integer> p = tester.cantidadDeDirectoriosYArchivos();
    	int directorios=p.getKey(),archivos=p.getValue();
    	JOptionPane.showMessageDialog(null, "Cantidad de directorios: "+directorios + "\nCantidad de archivos: "+archivos);
    	
    }
    if(LEVEL_COMMAND.equals(command)) { // Listado por nivel
  		a.setText(recorrerListadoPorNivel(tester.listadoPorNiveles()));
		JOptionPane.showMessageDialog(null, a,"Listado por Nivel",JOptionPane.INFORMATION_MESSAGE);
    	
    }
    if(DEPTH_COMMAND.equals(command)) { // Listado por profundidad
    	a.setText(recorrerListadoProfundidad(tester.listadoPorProfundidad()));
		JOptionPane.showMessageDialog(null, a,"Listado por Profundidad",JOptionPane.INFORMATION_MESSAGE);
    }
    if(EXTENSION_COMMAND.equals(command)) { // Listado por extencion
    	a.setText(recorrerListadoExtencion(tester.listadoPorExtencion()));
		JOptionPane.showMessageDialog(null, a,"Listado por Extension",JOptionPane.INFORMATION_MESSAGE);
    }
    
    if(tester!=null) {
    	cadenaArbol.setText(tester.generarArbolString());
    }
    else {
    	for(int i=0;i<botones.length;i++) {	//en caso de q halla fallado el constructor todos los botones se desactivan
			botones[i].setEnabled(false);	//no deberia ejecutarse esto pero por si las moscas
		}
    	botones[0].setEnabled(true);// se activa el generar gerarquia
    	botones[3].setEnabled(true);// se activa el generar gerarquia
    }
    
  }
  
  /**
   * Recorre el listado por nivel retornando un String a mostrar por pantalla
   * @param l Lista a recorrer
   * @return String a mostrar en pantalla
   */
  private String recorrerListadoPorNivel(PositionList<String> l) {
	  int i=0;
	  String c="Nivel "+i+": ";
	  i++;
	  for(String e: l) {
		  if(e.equals("\\")) {
				  c+="\nNivel "+i+": ";
				  i++;
		  }
		  else
			  c+=e;
	  }
	  return c;
  }
  
 /**
  * Recorre un diccionario donde guarda las excepciones como key y los nombres como valor y los retorna para poder mostrar por pantalla
  * @param D Diccionario a recorrer
  * @return String a mostrar en pantalla
  */
  private String recorrerListadoExtencion(Dictionary<String,String> D) {
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
   * Recorre un mapero donde guarda la direccion como key y la profundidad como valor
   * @param m Mapeo a recorrer
   * @return String a mostrar en pantalla
   */
	private String recorrerListadoProfundidad(Map<String,Integer> m) {
		String c="";
		for(Entry<String,Integer> e: m.entries()) {
			c+="Direccion: "+e.getKey()+", Profundidad: "+e.getValue()+"\n";
		}
		return c;
	}

	/**
	 * Crea y muestra la gui
	 */
  private static void createAndShowGUI() {
    
    JFrame frame = new JFrame("ED-Drive");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    ED_GUI panelPrincipal = new ED_GUI();
    panelPrincipal.setOpaque(true);
    frame.setContentPane(panelPrincipal);

    frame.pack();
    frame.setVisible(true);
  }
  public static void main(String[] args) {
        createAndShowGUI();
  }
}
