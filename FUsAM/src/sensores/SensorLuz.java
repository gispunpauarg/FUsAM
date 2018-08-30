package sensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

public class SensorLuz extends MySensor {

	protected static SensorLuz myInstancia;
	private float dato;	
				
	public static SensorLuz getInstance(Context ctx) {		
		 if (myInstancia == null) { 
			myContext = ctx; 
	    	myInstancia = new SensorLuz();
		 }	
		 return myInstancia;
	}
	
	public SensorLuz() {		
		sm = (SensorManager) myContext.getSystemService(Context.SENSOR_SERVICE);		
		sensor = sm.getDefaultSensor(Sensor.TYPE_LIGHT);		
	}
	
	public float getDato() {
		return dato;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_LIGHT) 			
			dato=event.values[0];        		
	}
	
}
