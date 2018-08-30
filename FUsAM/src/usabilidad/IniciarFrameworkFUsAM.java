package usabilidad;

import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class IniciarFrameworkFUsAM extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private TextField tfProject, tfEclipse, tfAndroid, tfJava;    
	private JFileChooser jfc;
	private String valor="";	
	
	/**
	 * Esta clase es la que inicia el framework de Usabilidad, permite por medio de
	 * una guia paso a paso integrar una aplicacion con una prueba de Usabilidad.
	 * La clase muestra una ventana con los pasos necesarios para integrar 
	 * a una aplicacion desarrollada en Android una prueba de usabilidad, segun
	 * la configuracion seleccionada para la prueba. 
	 * Al finalizar se generera un archivo apk que contiene tanto la aplicacion a probar 
	 * como la prueba de usabilidad (app+usabilidad). Este apk se puede instalar en un emulador 
	 * como en un dispositvo movil, una vez que el usuario empieza a utilizar la aplicacion
	 * se realiza tambien la recoleccion de informacion de usabilidad. Esta informacion 
	 * es seleccionada previamente. 
	 */
	
	/* 	Permisos que tiene que tener la aplicación Android a probar.
     *	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
     *	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
	 *	<uses-permission android:name="android.permission.CAMERA"/>
	*/
	
	public IniciarFrameworkFUsAM() {
		Image icono, newimg;        
		Icon icon;	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(540,640); //w,h        
        this.setLocationRelativeTo(null);        
        this.setLayout(null);
        //this.setResizable(false);
        this.setTitle("FUsAM - Framework de Usabilidad para Aplicaciones Móviles");
        ImageIcon img = new ImageIcon("./imagenes/logo.gif");        
        this.setIconImage(img.getImage());
        
        jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
               
        // PASO 0 - ingresar paths necesarios para el framework **********************************
        JLabel lProject = new JLabel();        
        lProject.setText("<html>Ingrese el path del proyecto a probar:</html>");
        tfProject = new TextField();
        tfProject.setText("C:\\DATOS\\workspaceMovil\\Note");
        //tfProject.setText("D:\\workspaceMovil\\TurtlePlayer");
        JButton bProject = new JButton();
        bProject.setText("seleccionar");
        bProject.setActionCommand("project");        
        bProject.addActionListener(this);
        
        JLabel lEclipse = new JLabel();        
        lEclipse.setText("<html>Ingrese el path del IDE Eclipse actualmente en ejecución:</html>");
        tfEclipse = new TextField();
        tfEclipse.setText("C:\\Eclipse Juno");
        JButton bEclipse = new JButton();
        bEclipse.setText("seleccionar");
        bEclipse.setActionCommand("eclipse");        
        bEclipse.addActionListener(this);
        
        JLabel lAndroid = new JLabel();        
        lAndroid.setText("<html>Ingrese el path del SDK de Android:</html>");
        tfAndroid = new TextField();
        tfAndroid.setText("C:\\Android-sdk");
        //tfAndroid.setText("C:\\Archivos de programa\\Android\\android-sdk");
        JButton bAndroid = new JButton();
        bAndroid.setText("seleccionar");
        bAndroid.setActionCommand("android");        
        bAndroid.addActionListener(this);
        
        JLabel lJava = new JLabel();        
        lJava.setText("<html>Ingrese el path del JDK de Java:</html>");
        tfJava = new TextField();        
        tfJava.setText("C:\\Program Files (x86)\\Java\\jdk1.7.0_80");
        JButton bJava = new JButton();
        bJava.setText("seleccionar");
        bJava.setActionCommand("java");        
        bJava.addActionListener(this);
                
        JButton bIngresar = new JButton();
        bIngresar.setText(">>  Ingresar Paths  <<");
        bIngresar.setActionCommand("ingresar");        
        bIngresar.addActionListener(this);       
        
        // PASO 1 - especificar las tareas a monitorear ***********************************************
        JLabel lDefinirTareas = new JLabel();        
        lDefinirTareas.setText("<html>&larr; Paso 1:<br>&nbsp;Especificar las tareas a monitorear en el archivo definicionTareas.xml.<html>");        					
        icono = new ImageIcon("./imagenes/definir.png").getImage();  
        newimg = icono.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);  
        icon = new ImageIcon(newimg);
        JButton bDefinirTareas = new JButton();
        bDefinirTareas.setIcon(icon);
        bDefinirTareas.setActionCommand("definir");        
        bDefinirTareas.addActionListener(this);
               
        // PASO 2 - cargar las tareas especificadas ***************************************************
        JLabel lCargarTareas = new JLabel();        
        lCargarTareas.setText("<html>&larr; Paso 2:<br>&nbsp;Cargar las tareas definidas en el archivo definicionTareas.xml.<br>"
        					+"&nbsp;Cada vez que modifique el archivo debe volver a realizar la carga.</html>");       
        icono = new ImageIcon("./imagenes/cargar.png").getImage();  
        newimg = icono.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);  
        icon = new ImageIcon(newimg);                
        JButton bCargarTareas = new JButton();
        bCargarTareas.setIcon(icon);        
        bCargarTareas.setActionCommand("cargar");        
        bCargarTareas.addActionListener(this);
        
        // PASO 3 - seleccionar las características de la prueba y grabar *******************************
        JLabel lSeleccionarFeature = new JLabel();        
        lSeleccionarFeature.setText("<html>&larr; Paso 3:<br>&nbsp;Abrir el archivo default.config y seleccionar las caracteristicas requeridas<br>"
        						+"&nbsp;para la prueba (Guardar).</hmtl>");
        icono = new ImageIcon("./imagenes/seleccionar.png").getImage();  
        newimg = icono.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);  
        icon = new ImageIcon(newimg);                
        JButton bSeleccionarFeature = new JButton();
        bSeleccionarFeature.setIcon(icon);        
        bSeleccionarFeature.setActionCommand("seleccionar");        
        bSeleccionarFeature.addActionListener(this);
                
        // PASO 4 - compilar el proyecto usabilidad ****************************************************
        JLabel lCompilar = new JLabel();        
        lCompilar.setText("<html>&#8212; Paso 4:<br>&nbsp;Compilar el proyecto FUsAM.<br>"
        			+"&nbsp;[FUsAM] &#8594; [Build Project]</html>");
        icono = new ImageIcon("./imagenes/compilar.png").getImage();  
        newimg = icono.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);  
        icon = new ImageIcon(newimg);        
        
        // PASO 5 - generar e instalar la aplicación mas la prueba *************************************       
        JLabel lInstalarAPK = new JLabel();        
        lInstalarAPK.setText("<html>&larr; Paso 5:<br>&nbsp;Generar e Instalar la aplicación integrada con la prueba de usabiliad.</html>");
        icono = new ImageIcon("./imagenes/generar.png").getImage();  
        newimg = icono.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);  
        icon = new ImageIcon(newimg);                
        JButton bInstalarAPK = new JButton();
        bInstalarAPK.setIcon(icon);                
        bInstalarAPK.setActionCommand("instalar");                
        bInstalarAPK.addActionListener(this);
        
        // setBounds(x,y,w,h)
        int x=20, y=10;
        
        lProject.setBounds(x, y, 500, 20);        
        tfProject.setBounds(x, y+20, 370, 20);
        bProject.setBounds(x+380, y+20, 110, 20);
        
        lEclipse.setBounds(x, y+50, 500, 20);
        tfEclipse.setBounds(x, y+70, 370, 20);        
        bEclipse.setBounds(x+380, y+70, 110, 20);
                
        lAndroid.setBounds(x, y+100, 500, 20);
        tfAndroid.setBounds(x, y+120, 370, 20);        
        bAndroid.setBounds(x+380, y+120, 110, 20);
                
        lJava.setBounds(x, y+150, 500, 20);
        tfJava.setBounds(x, y+170, 370, 20);        
        bJava.setBounds(x+380, y+170, 110, 20);
        
        bIngresar.setBounds(x, y+210, 170, 20);
        
        // paso 1
        bDefinirTareas.setBounds(x,y+250,50,50);
        lDefinirTareas.setBounds(x+60,y+260,450,30);
        
        // paso 2
        bCargarTareas.setBounds(x,y+320,50,50);
        lCargarTareas.setBounds(x+60,y+320,400,50);        
        
        // paso 3
        bSeleccionarFeature.setBounds(x,y+390,50,50);
        lSeleccionarFeature.setBounds(x+60,y+390,450,50);        
        
        // paso 4        
        lCompilar.setBounds(x+60,y+460,450,50);
        
        // paso 5
        bInstalarAPK.setBounds(x,y+530,50,50);
        lInstalarAPK.setBounds(x+60,y+535,450,40);        
                
        add(lProject);
        add(tfProject);
        add(bProject);
        
        add(lEclipse);
        add(tfEclipse);
        add(bEclipse);
        
        add(lAndroid);
        add(tfAndroid);
        add(bAndroid);
        
        add(lJava);
        add(tfJava);
        add(bJava);
        
        add(bIngresar);
        
        add(bDefinirTareas);
        add(lDefinirTareas);
        
        add(bCargarTareas);
        add(lCargarTareas);
        
        add(bSeleccionarFeature); 
        add(lSeleccionarFeature);        
        add(lCompilar);
        add(bInstalarAPK);
        add(lInstalarAPK);        
        
        this.setVisible(true);  
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		String cmd;
		int op;
		Properties propiedades = new Properties();;
		if (e.getActionCommand().equals("ingresar")) {	
			try {		        
		        propiedades.setProperty("path_project", tfProject.getText()+"\\");
		        propiedades.setProperty("path_eclipse", tfEclipse.getText()+"\\");
		        valor="";
		        //buscar("dx.bat", new File(tfAndroid.getText()));		        
			    //propiedades.setProperty("path_dx", valor+"\\");
		        propiedades.setProperty("path_dx", "C:\\Android-sdk\\build-tools\\23.0.3\\");
			    valor="";
			    //buscar("aapt.exe", new File(tfAndroid.getText()));
			    //propiedades.setProperty("path_aapt", valor+"\\");
			    propiedades.setProperty("path_aapt", "C:\\Android-sdk\\build-tools\\23.0.3\\");
			    valor="";
			    //buscar("jarsigner.exe", new File(tfJava.getText()));
			    //propiedades.setProperty("path_jarsigner", valor+"\\");
			    propiedades.setProperty("path_jarsigner", "C:\\Program Files (x86)\\Java\\jdk1.7.0_80\\bin\\");
			    valor="";
			    //buscar("adb.exe", new File(tfAndroid.getText()));
			    //propiedades.setProperty("path_adb", valor+"\\");
			    propiedades.setProperty("path_adb", "C:\\Android-sdk\\platform-tools\\");
			    File f = new File("configuracion.properties");
			    OutputStream out = new FileOutputStream(f);
			    propiedades.store(out, "Paths de archivos necesarios");
			    JOptionPane.showMessageDialog(null, "Paths ingresados", "Mensaje", 1);			    
			} catch (Exception er ) {
		        er.printStackTrace();
		    }			
		} else {			
		    InputStream entrada = null;
		    try {
		        entrada = new FileInputStream("./configuracion.properties");
		        propiedades.load(entrada);		
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }    			
		}			
		if (e.getActionCommand().equals("project")) {			
			op = jfc.showOpenDialog(this);
			if (op==JFileChooser.APPROVE_OPTION) {
				File fichero = jfc.getSelectedFile();
				tfProject.setText(fichero.getAbsolutePath());
			}						
		}	
		else if (e.getActionCommand().equals("eclipse")) { 
			op = jfc.showOpenDialog(this);
			if (op==JFileChooser.APPROVE_OPTION) {
				File fichero = jfc.getSelectedFile();
				tfEclipse.setText(fichero.getAbsolutePath());
			}						
		}
		else if (e.getActionCommand().equals("android")) { 
			op = jfc.showOpenDialog(this);
			if (op==JFileChooser.APPROVE_OPTION) {
				File fichero = jfc.getSelectedFile();
				tfAndroid.setText(fichero.getAbsolutePath());
			}						
		}
		else if (e.getActionCommand().equals("java")) { 
			op = jfc.showOpenDialog(this);
			if (op==JFileChooser.APPROVE_OPTION) {
				File fichero = jfc.getSelectedFile();
				tfJava.setText(fichero.getAbsolutePath());
			}						
		}		
		else if (e.getActionCommand().equals("definir")) {
			String f = new String("definicionTareas.xml");			
			cmd = "cmd /c \""+propiedades.getProperty("path_eclipse")+"eclipse.exe\" "+f;
			System.out.println(cmd);
			try{			
				Runtime.getRuntime().exec(cmd);			    
			}catch(IOException error){
				error.printStackTrace();
			}			
		}
		else if (e.getActionCommand().equals("cargar"))
			CargarAspectoTareas.ejecutar();
		else if (e.getActionCommand().equals("seleccionar")) {
			String f = new String("./configs/default.config");			
			cmd = "cmd /c \""+propiedades.getProperty("path_eclipse")+"eclipse.exe\" "+f;			
			System.out.println(cmd);
			try{			
				Runtime.getRuntime().exec(cmd);			    
			}catch(IOException error){
				error.printStackTrace();
			}				
		}		
		else if (e.getActionCommand().equals("instalar")) {
			InstalarAppUsabilidad.ejecutar();	
		}	
	}
		
	public static void main(String[] args) {
		JFrame jf = new IniciarFrameworkFUsAM();	
		jf.setVisible(true);	
	}
	
    public String buscar(String argFichero, File argFile) {    	
        File[] lista = argFile.listFiles();
        if (lista != null) {
            for (File elemento : lista) {            
                if (elemento.isDirectory()) {
                    buscar(argFichero, elemento);
                } else if (argFichero.equalsIgnoreCase(elemento.getName())) {
                	valor = elemento.getParentFile().getAbsolutePath();
                	return null;
                }                
            }            
        }        
        return null;
    }

}
