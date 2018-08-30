import usabilidad.MyLog; 
import android.app.Activity; 
import android.view.MenuItem; 

public aspect Tareas { 
	 // aspecto auto-generado por CargarAspectoTareas.java a partir del archivo definicionTareas.xml 

	 private Activity activity; 
	 public Activity getActivity() { return activity;} 
	 public void setActivity(Activity activity) { this.activity = activity;} 

	 // AgregarNota -------------------------------------------------------------------- 
	 pointcut AgregarNota(Activity act): execution(* com.example.note.NoteList.createNote(..)) && target(act); 
	 before(Activity act):AgregarNota(act) { 
		 MyLog.getInstance().appendLog("T", "TI", "AgregarNota", ""); 
		 this.setActivity(act); 
	 } 
	 // EditarNota -------------------------------------------------------------------- 
	 pointcut EditarNota(Activity act): execution(* com.example.note.NoteList.onListItemClick(..)) && target(act); 
	 before(Activity act):EditarNota(act) { 
		 MyLog.getInstance().appendLog("T", "TI", "EditarNota", ""); 
		 this.setActivity(act); 
	 } 
	 // GuardarNota -------------------------------------------------------------------- 
	 pointcut GuardarNota(Activity act, MenuItem a): execution(* com.example.note.NoteEdit.onOptionsItemSelected(MenuItem)) && target(act) && args(a) && if(a.toString().equals("Save")); 
	 after(Activity act, MenuItem a):GuardarNota(act, a) { 
		 MyLog.getInstance().appendLog("T", "TF", "GuardarNota", ""); 
		 this.setActivity(act); 
	 } 
	 // EliminarNota -------------------------------------------------------------------- 
	 pointcut EliminarNota(Activity act, MenuItem a): execution(* com.example.note.NoteEdit.onOptionsItemSelected(MenuItem)) && target(act) && args(a) && if(a.toString().equals("Delete")); 
	 after(Activity act, MenuItem a):EliminarNota(act, a) { 
		 MyLog.getInstance().appendLog("T", "TF", "EliminarNota", ""); 
		 this.setActivity(act); 
	 } 
}