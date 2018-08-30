package usabilidad;

import java.io.File;
import java.io.IOException;

public class Prueba {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static String valor;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		buscar("dx.bat", new File("C:\\Documents and Settings\\Administrador\\android-sdks\\build-tools"));  
		System.out.println("--> "+valor);
	}
	
	public static void buscar(String argFichero, File argFile) {		
        File[] lista = argFile.listFiles();
        if (lista != null) {
            for (File elemento : lista) {            
                if (elemento.isDirectory()) {
                    buscar(argFichero, elemento);
                } else if (argFichero.equalsIgnoreCase(elemento.getName())) {
                	Prueba.valor=elemento.getParentFile().getAbsolutePath();
               
                }                
            }            
        }
        
    }

}
