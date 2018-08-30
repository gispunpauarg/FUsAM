import android.content.Context;
import android.util.Log;

public aspect MetodosApp {
	// TODO Auto-generated aspect
	// Intercepta todos los m�todos de la aplicaci�n a probar.
	// Se utiliza para que el evaluador pueda descubrir los 
	// m�todos iniciales y finales de las tareas de la aplicaci�n.
	// ejemplo: execution(* com.example.note.*.*(..))
	
	pointcut llamada(Context tar, Context thi) : execution(* com.example.note.*.*(..))
	//pointcut llamada(Context tar, Context thi) : execution(* *.*.*(..))
	//pointcut llamada(Context tar, Context thi) : execution(* com.turtleplayer.view.*.*(..))
	&& target(tar) && this(thi);

	before(Context tar, Context thi) : llamada(tar, thi)
	{	
		Object [] args = thisJoinPoint.getArgs();
		Log.d("FUsAM", "metodo: "+thisJoinPoint.getSignature());
		if (args != null)
		for (int i = 0; i < args.length; i++) {
			if (args[i] != null)
				Log.d("FUsAM", "args: "+args[i].toString());
		}	
	}
}	
