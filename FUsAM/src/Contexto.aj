
public abstract aspect Contexto {
	// TODO Auto-generated aspect
	
	pointcut TareaInicio():adviceexecution() && within(Tareas) 
    		 && if(thisJoinPointStaticPart.getSignature().getName().contains("before"));

	pointcut TareaFin():adviceexecution() && within(Tareas) 
			 && if(thisJoinPointStaticPart.getSignature().getName().contains("after"));
}
