import android.app.Activity;
import sensores.SensorConexion;
import usabilidad.MyLog;

public aspect Conectividad extends Contexto {
	// TODO Auto-generated aspect
		
	private SensorConexion sensor;
	private Activity activity;

	//Se necesita permiso para acceder al estado de la red
	//<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
	
	after() : TareaInicio() {
		activity=((Tareas)thisJoinPoint.getTarget()).getActivity();		
		sensor = SensorConexion.getInstance(activity.getApplicationContext());		
		if (sensor != null) 			
			MyLog.getInstance().appendLog("C", "TI", "Conectividad", sensor.getDato());			
		else
			MyLog.getInstance().appendLog("C", "TI", "Conectividad", "null");													
	}
	
	before() : TareaFin() {
		activity=((Tareas)thisJoinPoint.getTarget()).getActivity();		
		sensor = SensorConexion.getInstance(activity.getApplicationContext());		
		if (sensor != null) 			
			MyLog.getInstance().appendLog("C", "TF", "Conectividad", sensor.getDato());			
		else
			MyLog.getInstance().appendLog("C", "TF", "Conectividad", "null");													
	}		

}
