package usabilidad;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Carga el aspecto Tareas.aj a partir del archivo de defincion de tareas (definicionTareas.xml)
 *   
 **/

public class CargarAspectoTareas {

	public static void ejecutar() {		
		try {						
			// Recorro el archivo de definicion de tareas y por cada tarea definida genero el codigo 
			// correspondiente en el aspecto Tareas.
			String codigo="";
			File xmlFile = new File("definicionTareas.xml");			
			if (!xmlFile.exists()) {				
				throw new FileNotFoundException("El archivo definicionTareas.xml no existe !");
			}			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();		
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();		
			Document doc = dBuilder.parse(xmlFile);			
			File fileTareas = new File("src/Tareas.aj");			
			if (!fileTareas.exists()) {			
				throw new FileNotFoundException("El aspecto Tareas.aj no existe !");
			}	
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileTareas));
			PrintWriter wr = new PrintWriter(bw);			
			codigo= "import usabilidad.MyLog; \n"+
					"import android.app.Activity; \n"+
					"import android.view.MenuItem; \n"+
					"\n"+
					"public aspect Tareas { \n"+
					"\t // aspecto auto-generado por CargarAspectoTareas.java a partir del archivo definicionTareas.xml \n"+
					"\n"+
					"\t private Activity activity; \n"+
					"\t public Activity getActivity() { return activity;} \n"+
					"\t public void setActivity(Activity activity) { this.activity = activity;} \n"+
					"\n";						
			wr.append(codigo);			
			NodeList nList = doc.getElementsByTagName("tarea");				
			for (int i=0; i < nList.getLength(); i++) {			
				Node nNode = nList.item(i);	
				codigo="";
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element ele = (Element) nNode;
					String nombre=ele.getAttribute("nombre");
					String tipo=ele.getAttribute("tipo");					
					String pointcut=ele.getElementsByTagName("pointcut").item(0).getTextContent();					
					String menu="";					
					if (ele.getElementsByTagName("menu").item(0) !=  null)
						menu=ele.getElementsByTagName("menu").item(0).getTextContent();					
					codigo="\t // "+nombre+" -------------------------------------------------------------------- \n";
					if (!menu.equals("")) {
						codigo=codigo+"\t pointcut "+nombre+"(Activity act, MenuItem a): "+
							"execution(* "+pointcut+"(MenuItem)) && target(act) && args(a) && if(a.toString().equals(\""+menu+"\")); \n";
						if (tipo.equals("TI")) {
							codigo=codigo+"\t before(Activity act, MenuItem a):"+nombre+"(act, a) { \n"+
									"\t\t MyLog.getInstance().appendLog(\"T\", \"TI\", \""+nombre+"\", \"\"); \n"+
									"\t\t this.setActivity(act); \n\t } \n";							
						}
						else {
							codigo=codigo+"\t after(Activity act, MenuItem a):"+nombre+"(act, a) { \n"+
									"\t\t MyLog.getInstance().appendLog(\"T\", \"TF\", \""+nombre+"\", \"\"); \n"+
									"\t\t this.setActivity(act); \n\t } \n";							
						}
					}	
					else {
						codigo=codigo+"\t pointcut "+nombre+"(Activity act): "+
								"execution(* "+pointcut+"(..)) && target(act); \n";					
						if (tipo.equals("TI")) {
							codigo=codigo+"\t before(Activity act):"+nombre+"(act) { \n"+
									"\t\t MyLog.getInstance().appendLog(\"T\", \"TI\", \""+nombre+"\", \"\"); \n"+
									"\t\t this.setActivity(act); \n\t } \n";							
						}
						else {
							codigo=codigo+"\t after(Activity act):"+nombre+"(act) { \n"+
									"\t\t MyLog.getInstance().appendLog(\"T\", \"TF\", \""+nombre+"\", \"\"); \n"+
									"\t\t this.setActivity(act); \n\t } \n";								
						}				
					}								
					wr.append(codigo);
				}			
			}
			wr.append("}");
			wr.close();
			bw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		JOptionPane.showMessageDialog(null, "Las Tareas fueron cargadas con éxito!", "Mensaje", 1);
	}
}
