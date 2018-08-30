package usabilidad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.swing.JOptionPane;

/**
 * Genera e instala el archivo apk que contiene las aplicacion que se va a probar junto
 * con la prueba de usabilidad configurada. Los archivos intermedios generados se guardan
 * en la carpeta tmp del proyecto Usabilidad
 *  
**/

public class InstalarAppUsabilidad {
	
	public static void ejecutar() {
		
		String path = new File("").getAbsolutePath();
		String path_workspace = path.replace("FUsAM", "");
		String path_soporte = path+"\\soporte\\";				
		String cmd, line;
		Process p;
		BufferedReader in;	
		try {			
			// CARGAR ARCHIVO DE CONFIGURACION
			
			Properties propiedades = new Properties();
		    InputStream entrada = null;
		    try {
		        entrada = new FileInputStream("./configuracion.properties");
		        propiedades.load(entrada);		
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }		
			String path_project = propiedades.getProperty("path_project");			
			String path_dx = propiedades.getProperty("path_dx");
			String path_aapt = propiedades.getProperty("path_aapt");
			String path_jarsigner = propiedades.getProperty("path_jarsigner");
			String path_adb = propiedades.getProperty("path_adb");			
			
			System.out.println("path project --> "+propiedades.getProperty("path_project"));
		    System.out.println("path eclipse --> "+propiedades.getProperty("path_eclipse"));
		    System.out.println("path dx.bat --> "+propiedades.getProperty("path_dx"));
		    System.out.println("path aapt.exe --> "+propiedades.getProperty("path_aapt"));
		    System.out.println("path jarsigner.exe --> "+propiedades.getProperty("path_jarsigner"));
		    System.out.println("path adb.exe --> "+propiedades.getProperty("path_adb"));
		    
			// CONTROLAR QUE EXISTEN LOS ARCHIVOS
			String msj="";
		    File fichero = new File(path_project);
			if (!fichero.exists())
				msj=msj+"El proyecto NO existe! \n";			
			fichero = new File(path_dx+"\\dx.bat");
			if (!fichero.exists())
				msj=msj+"El archivo dx.bat NO existe! \n";
			fichero = new File(path_aapt+"\\aapt.exe");
			if (!fichero.exists())
				msj=msj+"El archivo aapt.exe NO existe! \n";
			fichero = new File(path_jarsigner+"\\jarsigner.exe");
			if (!fichero.exists())
				msj=msj+"El archivo jarsigner.exe NO existe! \n";			
			fichero = new File(path_adb+"\\adb.exe");
			if (!fichero.exists())
				msj=msj+"El archivo adb.exe NO existe! \n";
		    if (msj.isEmpty()) {
		    	// BORRAR ARCHIVOS TEMPORALES ANTERIORES
				System.out.println("--- Borrando archivos anteriores");			
				//cmd = "cmd.exe /C del "+path_workspace+"FUsAM\\tmp\\appUsabilidad.unsigned.apk";					
				cmd = "cmd.exe /C del /Q "+path_workspace+"FUsAM\\tmp\\*";
				System.out.println("0 ### "+cmd);
				p = Runtime.getRuntime().exec(cmd);  
		        in = new BufferedReader(new InputStreamReader(p.getInputStream()));  
		        line = null;  
		        while ((line = in.readLine()) != null) {  
		        	System.out.println(line);  
		        }        
		        
				// GENERAR DEX 
				System.out.println("--- Generando archivo DEX");			
				//cmd = path_dx+"dx.bat --dex --output "+path_workspace+"FUsAM\\classes.dex "+
				cmd = "\""+path_dx+"dx.bat\" --dex --verbose --output "+path_workspace+"FUsAM\\classes.dex "+
							path_workspace+"FUsAM\\bin\\ "+		
							path_soporte+"aspectjrt.jar";		
				System.out.println("1 ### "+cmd);
				p = Runtime.getRuntime().exec(cmd);  
		        in = new BufferedReader(new InputStreamReader(p.getInputStream()));  
		        line = null;  
		        while ((line = in.readLine()) != null) {  
		        	System.out.println(line);  
		        }		
		        	        
		        // GENERAR APK	        
		        System.out.println("--- Generando archivo APK");	     	
		     	cmd = "\""+path_aapt+"aapt\" package -M "+path_project+"AndroidManifest.xml "+
		     				 "-S "+path_project+"res "+
		     				 "-I "+path_soporte+"android.jar "+	     				 
		     				 "-F "+path_workspace+"FUsAM\\tmp\\app+pruebaFUsAM.unsigned.apk";
		     	System.out.println("2 ### "+cmd);
		     	p = Runtime.getRuntime().exec(cmd);     	
		        in = new BufferedReader(new InputStreamReader(p.getInputStream()));  
		        line = null;  
		        while ((line = in.readLine()) != null) {  
		          	System.out.println(line);  
		        }	
					        
				// ADD .DEX		
				System.out.println("--- Agregando archivo DEX");			
		        cmd = "\""+path_aapt+"aapt\" add -f "+path_workspace+"FUsAM\\tmp\\app+pruebaFUsAM.unsigned.apk "+"classes.dex";        
		        System.out.println("3 ### "+cmd);
		        p = Runtime.getRuntime().exec(cmd);     	
		        in = new BufferedReader(new InputStreamReader(p.getInputStream()));  
		        line = null;  
		        while ((line = in.readLine()) != null) {  
		          	System.out.println(line);
		        }        
		        	        
		        // SIGNAL APK
				System.out.println("--- signal APK");			
		        cmd = "\""+path_jarsigner+"jarsigner\" -verbose -keystore "+ 
		        		path_soporte+"debug.keystore -storepass android -keypass android -signedjar "+
		        		path_workspace+"FUsAM\\tmp\\app+pruebaFUsAM.signed.apk "+
		        		path_workspace+"FUsAM\\tmp\\app+pruebaFUsAM.unsigned.apk androiddebugkey";		        
		        System.out.println("4 ### "+cmd);
		        p = Runtime.getRuntime().exec(cmd);     	
		        in = new BufferedReader(new InputStreamReader(p.getInputStream()));  
		        line = null;  
		        while ((line = in.readLine()) != null) {  
		          	System.out.println(line);
		        }  
				
				// ZIP ALIGN APK FILE (opcional) optimiza codigo	
		        System.out.println("--- zipalign APK");
		        cmd = "\""+path_soporte+"zipalign\" -v -f 4 "+ 
		        		path_workspace+"FUsAM\\tmp\\app+pruebaFUsAM.signed.apk "+
		        		path_workspace+"FUsAM\\tmp\\app+pruebaFUsAM.apk";        
		        System.out.println("5 ### "+cmd);
		        p = Runtime.getRuntime().exec(cmd);     	
		        in = new BufferedReader(new InputStreamReader(p.getInputStream()));  
		        line = null;  
		        while ((line = in.readLine()) != null) {  
		          	System.out.println(line);
		        } 
				
				// INSTALAR APK
				System.out.println("--- instalando APK");	        
				cmd = path_adb+"adb devices";				
				p = Runtime.getRuntime().exec(cmd);	     	
		        in = new BufferedReader(new InputStreamReader(p.getInputStream()));	        
		        line = null;  	        
		        int cont=0;
		        while ((line = in.readLine()) != null) {  
		          	System.out.println(line);
		          	cont++;
		        }			
		        if (cont>0) {
		        	cmd = path_adb+"adb install "+path_workspace+"FUsAM\\tmp\\app+pruebaFUsAM.apk";		        	
		        	System.out.println("6 ### "+cmd);
		        	p = Runtime.getRuntime().exec(cmd);	     	
		        	in = new BufferedReader(new InputStreamReader(p.getInputStream()));	        
		        	String str="";
		        	line = null;		        	
		        	while ((line = in.readLine()) != null) {	        		
		        		System.out.println(line);
		        		str=str+line;
		        	}
		        	if (str.contains("Success"))		        	
		        		JOptionPane.showMessageDialog(null, "La App+pruebaFUsAM se instalo con éxito en el emulador/dispositivo!", "Mensaje", 1);
		        	else
		        		JOptionPane.showMessageDialog(null, "Error al instalar App+pruebaFUsAM en el emulador/dispositivo! \n"+str, "Mensaje", 1);        		
		        }	
		        else
		        	System.out.println("No hay emuladores activos o dispositivos conectados!");
		    }    
		    else
		    	JOptionPane.showMessageDialog(null, msj, "Atención", 1);
		} catch (IOException e) {  
		            e.printStackTrace();  
		}		
		System.out.println("------------ FIN");
	}	
}
