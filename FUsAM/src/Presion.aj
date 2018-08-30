import sensores.SensorPresion;
import usabilidad.MyLog;
import android.app.Activity;

public aspect Presion extends Contexto {
	// TODO Auto-generated aspect
	
	private SensorPresion sensor;
	private Activity activity;
			
	after() : TareaInicio() {
		activity=((Tareas)thisJoinPoint.getTarget()).getActivity();		
		sensor = SensorPresion.getInstance(activity.getApplicationContext());		
		if (sensor != null) {
			sensor.registrar();
			MyLog.getInstance().appendLog("C", "TI", "Presion", String.valueOf(sensor.getDato()));
			sensor.desRegistrar();
		} else
			MyLog.getInstance().appendLog("C", "TI", "Presion", "null");
	}
	
	before() : TareaFin() {	
		activity=((Tareas)thisJoinPoint.getTarget()).getActivity();		
		sensor = SensorPresion.getInstance(activity.getApplicationContext());		
		if (sensor != null) {
			sensor.registrar();
			MyLog.getInstance().appendLog("C", "TF", "Presion", String.valueOf(sensor.getDato()));
			sensor.desRegistrar();
		} else
			MyLog.getInstance().appendLog("C", "TF", "Presion", "null");						      
	}	
	
}
