import sensores.SensorHumedad;
import usabilidad.MyLog;
import android.app.Activity;

public aspect Humedad extends Contexto {
	// TODO Auto-generated aspect
	
	private SensorHumedad sensor;
	private Activity activity;
	
	after() : TareaInicio() {
		activity=((Tareas)thisJoinPoint.getTarget()).getActivity();
		sensor = SensorHumedad.getInstance(activity.getApplicationContext());		
		if (sensor != null) {
			sensor.registrar();
			MyLog.getInstance().appendLog("C", "TI", "Humedad", String.valueOf(sensor.getDato()));
			sensor.desRegistrar();
		} else
			MyLog.getInstance().appendLog("C", "TI", "Humedad", "null");
	}	
		
	before() : TareaFin() {		
		activity=((Tareas)thisJoinPoint.getTarget()).getActivity();
		sensor = SensorHumedad.getInstance(activity.getApplicationContext());		
		if (sensor != null)  {
			sensor.registrar();
			MyLog.getInstance().appendLog("C", "TF", "Humedad", String.valueOf(sensor.getDato()));
		 	sensor.desRegistrar();
		} else
			MyLog.getInstance().appendLog("C", "TF", "Humedad", "null");
	}	

}