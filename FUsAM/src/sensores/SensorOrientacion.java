package sensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

public class SensorOrientacion extends MySensor {

	protected static SensorOrientacion myInstancia;
	private float[] dato;	
		
	public static SensorOrientacion getInstance(Context ctx) {		
		 if (myInstancia == null) { 
			myContext = ctx; 
	    	myInstancia = new SensorOrientacion();
		 }	
		 return myInstancia;
	}
			
	public SensorOrientacion() {
		dato = new float[3];
		sm = (SensorManager) myContext.getSystemService(Context.SENSOR_SERVICE);				
		sensor = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
	}
	
	public float[] getDato() {
		return dato;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
		     dato[0]=event.values[0];
		     dato[1]=event.values[1];
		     dato[2]=event.values[2];
		}     
	}
	
}
