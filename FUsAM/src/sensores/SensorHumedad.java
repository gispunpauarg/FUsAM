package sensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

public class SensorHumedad extends MySensor {

	private static SensorHumedad myInstancia;
	private float dato;	
		
	public static SensorHumedad getInstance(Context ctx) {		
		 if (myInstancia == null) { 
			myContext = ctx; 
	    	myInstancia = new SensorHumedad();
		 }	
		 return myInstancia;
	}	 
			
	public SensorHumedad() {		
		sm = (SensorManager) myContext.getSystemService(Context.SENSOR_SERVICE);		
		sensor = sm.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);		
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
