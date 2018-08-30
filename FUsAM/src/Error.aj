import usabilidad.MyLog;

public aspect Error extends Metricas {
	// TODO Auto-generated aspect
	
	after() throwing(Exception e) : TareaInicio() {		
		MyLog.getInstance().appendLog("M", "TI", "Error", e.getMessage());		
	}
	
	after() throwing(Exception e) : TareaFin() {
		MyLog.getInstance().appendLog("M", "TF", "Error", e.getMessage());		
	}
}
