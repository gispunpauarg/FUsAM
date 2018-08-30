import usabilidad.MyLog;

public aspect Memoria extends Metricas {
	// TODO Auto-generated aspect
	
	after() : TareaInicio() {		
		long freeSize = 0L;
	    long totalSize = 0L;
	    long usedSize = -1L;
	    try {
	        Runtime info = Runtime.getRuntime();
	        freeSize = info.freeMemory();
	        totalSize = info.totalMemory();
	        usedSize = totalSize - freeSize;
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
		MyLog.getInstance().appendLog("M", "TI", "Memoria", String.valueOf(usedSize));			              
	}
	
	before() : TareaFin() {	
		long freeSize = 0L;
	    long totalSize = 0L;
	    long usedSize = -1L;
	    try {
	        Runtime info = Runtime.getRuntime();
	        freeSize = info.freeMemory();
	        totalSize = info.totalMemory();
	        usedSize = totalSize - freeSize;
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
		MyLog.getInstance().appendLog("M", "TF", "Memoria", String.valueOf(usedSize));				      
	}
}