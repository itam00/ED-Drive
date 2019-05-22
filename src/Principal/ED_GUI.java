package Principal;

import java.awt.BorderLayout;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import TDAArbol.Tree;
import TDALista.*;
public class ED_GUI extends JPanel implements ActionListener {
	private static String GENERATE_COMMAND = "Generar Gerarquia";
	private static String ADD_COMMAND = "A�adir archivo";
	private static String REMOVE_COMMAND = "Remover archivo";
	private static String MOVE_D_COMMAND = "Mover Directorio";
	private static String ADD_D_COMMAND = "A�adir Direcotrio";
	private static String REMOVE_D_COMMAND = "Remover Directorio";
	private static String LEVEL_COMMAND = "Listado por nivel";
	private static String EXTENSION_COMMAND = "Listado por extension";
	private static String DEPTH_COMMAND = "listaProfundidad";
	private static String TOTAL_COMMAND = "Cantidad de carpetas y archivos";
  
	private Toolkit toolkit;
	private JTextArea cadenaArbol;
	private final int cantBotones = 10;
	String[] nombres;
	private archivos_tester tester;
	JPanel panel,panelArbol;
	JButton botones[];

	public ED_GUI() {
	    super(new BorderLayout());
	    toolkit = Toolkit.getDefaultToolkit();
	    nombres = new String[cantBotones];
	    nombres[0] =GENERATE_COMMAND;
	    nombres[1] =ADD_COMMAND;
	    nombres[2] =REMOVE_COMMAND;
	    nombres[3] =REMOVE_D_COMMAND;
	    nombres[4] =ADD_D_COMMAND;
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
	
		}
	
	    botones[0].setEnabled(true);
	    panelArbol = new JPanel();
	    panel = new JPanel(new GridLayout(cantBotones, 0));
	    cadenaArbol = new JTextArea();
	    
	
	    cadenaArbol.setPreferredSize(new Dimension(720, 480));
	    panelArbol.add(cadenaArbol);
	    for(int i=0;i<nombres.length;i++) {
	    	panel.add(botones[i]);
	    }    
	    
	    add(panelArbol,BorderLayout.WEST);
	    add(panel, BorderLayout.EAST);
	    
  }



  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    
    //LUEGO ELIMINAR TODOS LOS TRY CATCH Y REEMPLAZARLOS SOLO X UNO, AHORA ESTA ASI PARA PROBAR LOS
    //METODOS INDIVIDUALMENTE
    
    if(GENERATE_COMMAND.equals(command)) {
    	String direccion = JOptionPane.showInputDialog("Ingrese la direccion del archivo");
    	try {
    		if(direccion!=null&&direccion.length()>0) {
    			tester = new archivos_tester(direccion);
				for(int i=0;i<botones.length;i++) {
					botones[i].setEnabled(true);		
				}
    		}
    		
		} catch (InvalidFileLocationException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			toolkit.beep();
		} catch (InvalidFileException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			toolkit.beep();
		}
    		
    	
    }
    if (ADD_COMMAND.equals(command)) {
    	
    	
    	try {
    		String nombre = JOptionPane.showInputDialog("Ingrese el nombre del archivo que desea a�adir al sistema");
    		if(nombre!=null&&nombre.length()>0) {
    			String direccion = JOptionPane.showInputDialog("Ingrese la direccion donde desea crearlo");			
    			if(direccion!=null&&nombre.length()>0)
    				tester.agregarArchivo(direccion,nombre);
    		}
		} catch (InvalidFileLocationException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (InvalidFileNameException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
		}
    	
    }
    if (REMOVE_COMMAND.equals(command)) {
    	String direccion = JOptionPane.showInputDialog("Ingrese la direccion del archivo a remover incluyendo el nombre del archivo");
    	try {
    		if(direccion!=null&&direccion.length()>0)
    			tester.eliminarArchivo(direccion);
		} catch (InvalidFileLocationException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
    } 
    if (ADD_D_COMMAND.equals(command)) {
    		
    	try {
    		String nombre = JOptionPane.showInputDialog("Ingrese el nombre del directorio que desea a�adir al sistema");
    		if(nombre!=null&&nombre.length()>0) { 
    			String direccion = JOptionPane.showInputDialog("Ingrese la direccion donde desea crearlo");
    			if(direccion!=null&&direccion.length()>0)
    				tester.agregarDirectorio(direccion,nombre);
			}
		} catch (InvalidFileLocationException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
    }
    if (REMOVE_D_COMMAND.equals(command)) {
    	String direccion = JOptionPane.showInputDialog("Ingrese la direccion del directorio a remover incluyendolo");
    	try {
    		if(direccion!=null&&direccion.length()>0)
				tester.eliminarDirectorio(direccion);
		} catch (InvalidFileLocationException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
    }
    if(MOVE_D_COMMAND.equals(command)) {
    	String destino;
    	String direccion = JOptionPane.showInputDialog("Ingrese la direccion del directorio a mover");
    	if(direccion!=null&&direccion.length()>0)
    		destino = JOptionPane.showInputDialog("Ingrese el destino del directorio");
    }
    if(TOTAL_COMMAND.equals(command)) {
    	Pair<Integer,Integer> p = tester.cantidadDeDirectoriosYArchivos();
    	int directorios=p.getKey(),archivos=p.getValue();
    	JOptionPane.showMessageDialog(null, "Cantidad de directorios: "+directorios + "\nCantidad de archivos: "+archivos);
    	
    }
    if(LEVEL_COMMAND.equals(command)) {

    	JOptionPane.showMessageDialog(null,tester.listadoPorNiveles());
    	
    }
    if(DEPTH_COMMAND.equals(command)) {
    	JOptionPane.showMessageDialog(null, tester.listadoProf());
    }
    if(EXTENSION_COMMAND.equals(command)) {
    	
    }
    
    //HASTA ACA LLEGARIA EL TRY CATCH
    if(tester!=null) {
    	cadenaArbol.setText(tester.generarArbolString());
    }
    else {
    	for(int i=0;i<botones.length;i++) {	//en caso de q halla fallado el constructor todos los botones se desactivan
			botones[i].setEnabled(false);		
		}
    	botones[0].setEnabled(true);// se activa el generar gerarquia
    }
  }


  private static void createAndShowGUI() {
    // Create and set up the window.
    JFrame frame = new JFrame("ED-Drive");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
