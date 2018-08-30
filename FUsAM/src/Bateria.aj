import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import usabilidad.MyLog;

public aspect Bateria  extends Metricas {
	// TODO Auto-generated aspect
	
	private Activity activity;
	private Context ctx;
	
	after() : TareaInicio() {
		activity=((Tareas)thisJoinPoint.getTarget()).getActivity();
		ctx=activity.getApplicationContext();
		IntentFilter batIntentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);		
	    Intent battery = ctx.registerReceiver(null, batIntentFilter);
	    int nivelBateria = battery.getIntExtra(BatteryManager.EXTRA_LEVEL, -1); 
		MyLog.getInstance().appendLog("M", "TI", "Bateria", String.valueOf(nivelBateria));			              
	}
	
	before() : TareaFin() {	
		activity=((Tareas)thisJoinPoint.getTarget()).getActivity();
		ctx=activity.getApplicationContext();
		IntentFilter batIntentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);		
	    Intent battery = ctx.registerReceiver(null, batIntentFilter);
	    int nivelBateria = battery.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		MyLog.getInstance().appendLog("M", "TF", "Bateria", String.valueOf(nivelBateria));				      
	}
}