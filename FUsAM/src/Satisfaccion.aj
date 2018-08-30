
public abstract aspect Satisfaccion {
	// TODO Auto-generated aspect
	
	pointcut TareaInicio():adviceexecution() && within(Tareas) 
	 && if(thisJoinPointStaticPart.getSignature().getName().contains("before"));

	pointcut TareaFin():adviceexecution() && within(Tareas) 
	 && if(thisJoinPointStaticPart.getSignature().getName().contains("after"));
	
}
