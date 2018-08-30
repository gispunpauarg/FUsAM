import java.io.IOException;
import usabilidad.MyLog;
import android.media.MediaRecorder;

public aspect Ruido extends Contexto {
	// TODO Auto-generated aspect
	
	private MediaRecorder mRecorder;
	
	after() : TareaInicio() {			    
		if (mRecorder == null) {
			mRecorder = new MediaRecorder();
			mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);			
		}	
		//No indicamos ningun archivo ya que solo queremos escuchar
		mRecorder.setOutputFile("/dev/null");
        try {
            mRecorder.prepare();
        } catch (IllegalStateException e) {	            
            MyLog.getInstance().appendLog("C", "TI", "Ruido", "IllegalStateException");
        } catch (IOException e) {
            MyLog.getInstance().appendLog("C", "TI", "Ruido", "IOException");
        }	 
        mRecorder.start();
        double ruido = mRecorder.getMaxAmplitude();
        MyLog.getInstance().appendLog("C", "TI", "Ruido", String.valueOf(ruido)); 
        mRecorder.stop();
    }
	
	before() : TareaFin() {	
		if (mRecorder == null) {
			mRecorder = new MediaRecorder();        
        	mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        	mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        	mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);        	
		}	
		//No indicamos ningun archivo ya que solo queremos escuchar
    	mRecorder.setOutputFile("/dev/null");
        try {
            mRecorder.prepare();
        } catch (IllegalStateException e) {	            
            MyLog.getInstance().appendLog("C", "TF", "Ruido", "IllegalStateException");
        } catch (IOException e) {
            MyLog.getInstance().appendLog("C", "TF", "Ruido", "IOException");
        }	 
        mRecorder.start();
        double ruido = mRecorder.getMaxAmplitude();
        MyLog.getInstance().appendLog("C", "TF", "Ruido", String.valueOf(ruido));
        mRecorder.stop();
	}
}