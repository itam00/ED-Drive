package Principal;

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

import TDALista.*;

@SuppressWarnings("serial")
public class ED_GUI extends JPanel implements ActionListener {
	private static String GENERATE_COMMAND = "Generar jerarquia";
	private static String ADD_COMMAND = "A�adir archivo";
	private static String REMOVE_COMMAND = "Remover archivo";
	private static String MOVE_D_COMMAND = "Mover directorio";
	private static String ADD_D_COMMAND = "A�adir directorio";
	private static String REMOVE_D_COMMAND = "Remover directorio";
	private static String LEVEL_COMMAND = "Listado por nivel";
	private static String EXTENSION_COMMAND = "Listado por extension";
	private static String DEPTH_COMMAND = "Listado por profundidad";
	private static String TOTAL_COMMAND = "Cantidad de carpetas y archivos";
  
	private Toolkit toolkit;
	private JTextArea cadenaArbol;
	private final int cantBotones = 10;
	private String[] nombres;
	private archivos_tester tester;
	private JPanel panelBotones;
	private JButton botones[];
	private JScrollPane sc;
	private JLabel label;

	public ED_GUI() {
	    super(new BorderLayout());
	    toolkit = Toolkit.getDefaultToolkit();
	    nombres = new String[cantBotones];
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
	    
	    for (int i = 0; i < nombres.length; i++) {
	    	botones[i] = new JButton(nombres[i]);
	        botones[i].setActionCommand(nombres[i]);
	        botones[i].addActionListener(this);
	        botones[i].setEnabled(false);
	        botones[i].setFont( new Font("Century Gothic",Font.BOLD, 16) );
	
		}
	
	    botones[0].setEnabled(true);
	    panelBotones = new JPanel(new GridLayout(cantBotones, 0));
	    cadenaArbol = new JTextArea();
	    cadenaArbol.setEditable(false);
	    cadenaArbol.setFont( new Font("Century Gothic",Font.BOLD, 20) );
	    
	    sc = new JScrollPane(cadenaArbol,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    
	    for(int i=0;i<nombres.length;i++) {
	    	panelBotones.add(botones[i]);
	    }    
	    
	    add(sc);
	    add(panelBotones, BorderLayout.EAST);
	    setPreferredSize(new Dimension(840,480));
  }



  @SuppressWarnings("static-access")
public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    
    try {
	    if(GENERATE_COMMAND.equals(command)) {
	    	String direccion = JOptionPane.showInputDialog("Ingrese la direccion del archivo");
	    	if(direccion!=null) {
		    	String separador= Pattern.quote(".");
		    	String [] partes=direccion.split(separador);
		    	boolean cumple=false;
		    	if (partes.length>1)
		    		cumple = (partes[partes.length-2].charAt(partes[partes.length-1].length()-1)!='\\' &&partes[partes.length-1].equals("ed19"));
		    	if(direccion!=null && cumple && direccion.length()>0) {
			    		tester = new archivos_tester(direccion);
						for(int i=0;i<botones.length;i++) {
							botones[i].setEnabled(true);		
						}
		    	}
		    	else
		    		throw new InvalidFileLocationException("La direccion no es valida");
		    	}
	    	}
	    
	    if (ADD_COMMAND.equals(command)) {
	    	String nombre = JOptionPane.showInputDialog("Ingrese el nombre del archivo que desea a�adir al sistema");
	    	if(nombre!=null&&nombre.length()>0) {
	    		tester.validarExtencion(nombre);	//se valida el nombre del archivo antes de preguntar por su direccion
	    		String direccion = JOptionPane.showInputDialog("Ingrese la direccion donde desea crearlo");			
	    		if(direccion!=null&&direccion.length()>0)	
	    			tester.agregarArchivo(direccion,nombre);
	    	}	
	    }
	    
	    if (REMOVE_COMMAND.equals(command)) {
	    	String direccion = JOptionPane.showInputDialog("Ingrese la direccion del archivo a remover incluyendo el nombre del archivo");
	    	if(direccion!=null&&direccion.length()>0)
	    		tester.eliminarArchivo(direccion);
	    } 
	    if (ADD_D_COMMAND.equals(command)) {
	    	String nombre = JOptionPane.showInputDialog("Ingrese el nombre del directorio que desea a�adir al sistema");
	    	if(nombre!=null&&nombre.length()>0) { 
	    		String direccion = JOptionPane.showInputDialog("Ingrese la direccion donde desea crearlo");
	    		if(direccion!=null)
	    			tester.agregarDirectorio(direccion,nombre);
			}

	    }
	    if (REMOVE_D_COMMAND.equals(command)) {
	    	String direccion = JOptionPane.showInputDialog("Ingrese la direccion del directorio a remover incluyendolo");
	    	if(direccion!=null&&direccion.length()>0)
				tester.eliminarDirectorio(direccion);
	    }
	    if(MOVE_D_COMMAND.equals(command)) {
	    	String origen = JOptionPane.showInputDialog("Ingrese la direccion del directorio a mover");
	    	if(origen!=null&&origen.length()>0) {
	    		String destino = JOptionPane.showInputDialog("Ingrese el destino del directorio");
	    		if(destino!=null&&origen.length()>0)
	    			tester.moverDirectorio(origen, destino);
	    	}
	    }
  	} catch (InvalidFileLocationException|InvalidFileException|InvalidFileNameException e1) {
  		toolkit.beep();
  		label = new JLabel(e1.getMessage());
  		label.setFont(new Font("Century Gothic",Font.BOLD, 12) );
		JOptionPane.showMessageDialog(null, label,"Error",JOptionPane.ERROR_MESSAGE);
	} 
    catch(Exception w2) {
    	label = new JLabel(w2.getMessage());
  		label.setFont(new Font("Century Gothic",Font.BOLD, 12) );
		JOptionPane.showMessageDialog(null, label,"Error",JOptionPane.ERROR_MESSAGE);
    }
   
    //ESTO NO TIRA EXCECIONES PERO DEBERIA AARREGLAR
    
    if(TOTAL_COMMAND.equals(command)) {
    	Pair<Integer,Integer> p = tester.cantidadDeDirectoriosYArchivos();
    	int directorios=p.getKey(),archivos=p.getValue();
    	JOptionPane.showMessageDialog(null, "Cantidad de directorios: "+directorios + "\nCantidad de archivos: "+archivos);
    	
    }
    if(LEVEL_COMMAND.equals(command)) {
    	String listado="";
    	for (String nombre:tester.listadoPorNiveles()) {
    		listado+= nombre;
    	}
    	JTextArea a = new JTextArea(listado);
    	a.setOpaque(false);
  		a.setFont(new Font("Century Gothic",Font.BOLD, 12) );
		JOptionPane.showMessageDialog(null, a,"Listado por Nivel",JOptionPane.INFORMATION_MESSAGE);
    	
    }
    if(DEPTH_COMMAND.equals(command)) {
    	JTextArea a = new JTextArea(tester.listadoProf());
    	a.setOpaque(false);
  		a.setFont(new Font("Century Gothic",Font.BOLD, 12) );
		JOptionPane.showMessageDialog(null, a,"Listado por Profundidad",JOptionPane.INFORMATION_MESSAGE);
    }
    if(EXTENSION_COMMAND.equals(command)) {
    	JTextArea a = new JTextArea(tester.mostrarListadoExtencion());
    	a.setOpaque(false);
  		a.setFont(new Font("Century Gothic",Font.BOLD, 12) );
		JOptionPane.showMessageDialog(null, a,"Listado por Extencion",JOptionPane.INFORMATION_MESSAGE);
    }
    
    if(tester!=null) {
    	cadenaArbol.setText(tester.generarArbolString());
    }
    else {
    	for(int i=0;i<botones.length;i++) {	//en caso de q halla fallado el constructor todos los botones se desactivan
			botones[i].setEnabled(false);	//no deberia ejecutarse esto pero por si las moscas
		}
    	botones[0].setEnabled(true);// se activa el generar gerarquia
    }
  }

  private static void createAndShowGUI() {
    // Create and set up the window.
    JFrame frame = new JFrame("ED-Drive");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   //frame.setIconImage(new ImageIcon(getClass().getResource("../icon.png")).getImage());

    // Create and set up the content pane.
    ED_GUI panelPrincipal = new ED_GUI();
    panelPrincipal.setOpaque(true);
    frame.setContentPane(panelPrincipal);

    // Display the window.
    frame.pack();
    frame.setVisible(true);
  }
  public static void main(String[] args) {
        createAndShowGUI();
  }
}
