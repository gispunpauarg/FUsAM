package sensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

public class SensorTemperatura extends MySensor {

	private static SensorTemperatura myInstancia;
	private float dato;	
		
	public static SensorTemperatura getInstance(Context ctx) {		
		 if (myInstancia == null) { 
			myContext = ctx; 
	    	myInstancia = new SensorTemperatura();
		 }	
		 return myInstancia;
	}	 
			
	public SensorTemperatura() {		
		sm = (SensorManager) myContext.getSystemService(Context.SENSOR_SERVICE);		
		sensor = sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);		
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
