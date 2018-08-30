import android.app.Activity;
import sensores.SensorLuz;
import usabilidad.MyLog;

public aspect Luminosidad extends Contexto {
	// TODO Auto-generated aspect
	
	private SensorLuz sensor;	
	private Activity activity;
	
	after() : TareaInicio() {		
		activity=((Tareas)thisJoinPoint.getTarget()).getActivity();
		sensor = SensorLuz.getInstance(activity.getApplicationContext());		
		if (sensor != null) {
			sensor.registrar();
			MyLog.getInstance().appendLog("C", "TI", "Luminosidad", String.valueOf(sensor.getDato()));
			sensor.desRegistrar();
		} else
			MyLog.getInstance().appendLog("C", "TI", "Luminosidad", "null");
	}	
		
	before() : TareaFin() {		
		activity=((Tareas)thisJoinPoint.getTarget()).getActivity();
		sensor = SensorLuz.getInstance(activity.getApplicationContext());		
		if (sensor != null)  {
			sensor.registrar();
			MyLog.getInstance().appendLog("C", "TF", "Luminosidad", String.valueOf(sensor.getDato()));
		 	sensor.desRegistrar();
		} else
			MyLog.getInstance().appendLog("C", "TF", "Luminosidad", "null");
	}	

}
