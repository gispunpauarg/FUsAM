package sensores;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class SensorConexion extends MySensor {

	protected static SensorConexion myInstancia;
	private ConnectivityManager connMgr;	
				
	public static SensorConexion getInstance(Context ctx) {		
		 if (myInstancia == null) { 
			myContext = ctx; 
	    	myInstancia = new SensorConexion();
		 }	
		 return myInstancia;
	}
	
	public SensorConexion() {		
		connMgr = (ConnectivityManager)myContext.getSystemService(Context.CONNECTIVITY_SERVICE);		
	}
	
	public String getDato() {
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo.isConnected())			
			return networkInfo.getTypeName();
		else
			return "sin Conexion";		
	}
	
}
