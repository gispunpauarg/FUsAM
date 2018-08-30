package usabilidad;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import android.os.Environment;
import android.util.Log;

public class MyLog {
	
	private static MyLog instance;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static MyLog getInstance() {
		if (instance == null)
			instance = new MyLog();
		return instance;		
	}

	public void appendLog(String tag, String tipo, String text, String valor)
	{  // storage/sdcard/usabilidad.xml 	
	   File logFile = new File(Environment.getExternalStorageDirectory()+"/usabilidad.xml");	   
	   Log.d("USAB", "usabilidad.xml -> "+Environment.getExternalStorageDirectory());
	   System.out.println("usabilidad.xml -> "+Environment.getExternalStorageDirectory());	  
	   if (!logFile.exists()) {
	      try {
	         logFile.createNewFile();
	      } 
	      catch (IOException e) {	         
	         e.printStackTrace();
	      }
	   }
	   long date = System.currentTimeMillis(); 
	   String fecha = sdf.format(date);
	   // T(tarea nombre) C(contexto) M(metrica) S(satisfaccion)
	   // TI(tarea inicial) TF(tarea final)
	   try {	      
		   if (tag.equals("T")) { 
			   if (tipo.equals("TI")) 
				   text="<Tarea nombre="+text+" fecha="+fecha+">";
			   else
				   text="</Tarea nombre="+text+" fecha="+fecha+">";
	   	   }			   
		   else {
			   	if (tipo.equals("TI"))
			   		text="\t <I nombre="+text+">"+valor+"</I>";
			   	else
			   		text="\t <F nombre="+text+">"+valor+"</F>";
		   }		  		
		   		   
		   //BufferedWriter for performance, true to set append to file flag
		   BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true)); 
		   buf.append(text);
		   buf.newLine();
		   buf.close();
	   }
	   catch (IOException e) {	      
	      e.printStackTrace();
	   }
	}	
}
