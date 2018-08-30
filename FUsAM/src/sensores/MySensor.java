package sensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public abstract class MySensor implements SensorEventListener {

	protected SensorManager sm;
	protected Sensor sensor;	
	protected static Context myContext;
		
	public void registrar() {		
		sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);;				
	}
	
	public void desRegistrar() {		
		sm.unregisterListener(this);				
	}	
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub		
	}	

}
