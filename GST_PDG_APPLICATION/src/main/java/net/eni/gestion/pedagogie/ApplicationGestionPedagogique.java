package net.eni.gestion.pedagogie;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class ApplicationGestionPedagogique extends Application 
{

@Override
public Set<Class<?>> getClasses()
{
    //log.info("starting the HelloWorld application...");
	Set<Class<?>> set = new HashSet<Class<?>>();
    return set;
}
}