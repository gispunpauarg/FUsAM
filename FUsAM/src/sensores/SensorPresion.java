package sensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

public class SensorPresion extends MySensor {

	private static SensorPresion myInstancia;
	private float dato;	
		
	public static SensorPresion getInstance(Context ctx) {		
		 if (myInstancia == null) { 
			myContext = ctx; 
	    	myInstancia = new SensorPresion();
		 }	
		 return myInstancia;
	}	 
			
	public SensorPresion() {		
		sm = (SensorManager) myContext.getSystemService(Context.SENSOR_SERVICE);		
		sensor = sm.getDefaultSensor(Sensor.TYPE_PRESSURE);		
	}
	
	public float getDato() {
		return dato;
	}
		
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		dato=event.values[0];        		
	}
	
}
