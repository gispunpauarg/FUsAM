import usabilidad.MyLog;

public aspect Tiempo extends Metricas {
	// TODO Auto-generated aspect
	
	after() : TareaInicio() {
		MyLog.getInstance().appendLog("M", "TI", "Tiempo", String.valueOf(System.currentTimeMillis()));			              
	}
	
	before() : TareaFin() {	
		MyLog.getInstance().appendLog("M", "TF", "Tiempo", String.valueOf(System.currentTimeMillis()));				      
	}
}
