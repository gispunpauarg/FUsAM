import usabilidad.MyLog;
import android.app.Activity;
import android.view.WindowManager;

public aspect Brillo extends Metricas {
	// TODO Auto-generated aspect
	
	private Activity activity;
	
	after() : TareaInicio() {		
		activity=((Tareas)thisJoinPoint.getTarget()).getActivity();
		WindowManager.LayoutParams lp = activity.getWindow().getAttributes();	    
		float brillo = lp.screenBrightness;
		MyLog.getInstance().appendLog("M", "TI", "Brillo", String.valueOf(brillo));			              
	}
	
	before() : TareaFin() {	
		activity=((Tareas)thisJoinPoint.getTarget()).getActivity();
		WindowManager.LayoutParams lp = activity.getWindow().getAttributes();	    
		float brillo = lp.screenBrightness;
		MyLog.getInstance().appendLog("M", "TF", "Brillo", String.valueOf(brillo));				      
	}
}