import android.app.Activity;
import sensores.SensorOrientacion;
import usabilidad.MyLog;

public aspect Movimiento extends Contexto {
	// TODO Auto-generated aspect
	
	private SensorOrientacion sensor;
	private Activity activity;
	
	after() : TareaInicio() {
		activity=((Tareas)thisJoinPoint.getTarget()).getActivity();		
		sensor = SensorOrientacion.getInstance(activity.getApplicationContext());		
		if (sensor != null) {
			sensor.registrar();
			MyLog.getInstance().appendLog("C", "TI", "Movimiento", String.valueOf(sensor.getDato()));
			sensor.desRegistrar();
		} else
			MyLog.getInstance().appendLog("C", "TI", "Movimiento", "null");
	}	
		
	before() : TareaFin() {		
		activity=((Tareas)thisJoinPoint.getTarget()).getActivity();
		sensor = SensorOrientacion.getInstance(activity.getApplicationContext());		
		if (sensor != null)  {
			sensor.registrar();
			MyLog.getInstance().appendLog("C", "TF", "Movimiento", String.valueOf(sensor.getDato()));
		 	sensor.desRegistrar();
		} else
			MyLog.getInstance().appendLog("C", "TF", "Movimiento", "null");
	}	

}
