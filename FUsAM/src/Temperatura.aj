import android.app.Activity;
import sensores.SensorTemperatura;
import usabilidad.MyLog;

public aspect Temperatura extends Contexto {
	// TODO Auto-generated aspect
	
	private SensorTemperatura sensor;
	private Activity activity;
	
	after() : TareaInicio() {
		activity=((Tareas)thisJoinPoint.getTarget()).getActivity();
		sensor = SensorTemperatura.getInstance(activity.getApplicationContext());		
		if (sensor != null) {
			sensor.registrar();
			MyLog.getInstance().appendLog("C", "TI", "Temperatura", String.valueOf(sensor.getDato()));
			sensor.desRegistrar();
		} else
			MyLog.getInstance().appendLog("C", "TI", "Temperatura", "null");
	}	
		
	before() : TareaFin() {		
		activity=((Tareas)thisJoinPoint.getTarget()).getActivity();
		sensor = SensorTemperatura.getInstance(activity.getApplicationContext());		
		if (sensor != null)  {
			sensor.registrar();
			MyLog.getInstance().appendLog("C", "TF", "Temperatura", String.valueOf(sensor.getDato()));
		 	sensor.desRegistrar();
		} else
			MyLog.getInstance().appendLog("C", "TF", "Temperatura", "null");
	}	

}
