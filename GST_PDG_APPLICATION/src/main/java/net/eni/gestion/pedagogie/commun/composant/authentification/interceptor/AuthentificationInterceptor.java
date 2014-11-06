package net.eni.gestion.pedagogie.commun.composant.authentification.interceptor;

import net.eni.gestion.pedagogie.commun.composant.authentification.Authentification;
import net.eni.gestion.pedagogie.commun.composant.authentification.annotation.CheckSession;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class AuthentificationInterceptor extends AbstractModule implements
		MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		if (((Authentification) methodInvocation.getThis()).checkSession()){
			return methodInvocation.proceed();
		}
		return null;
	}

	@Override
	protected void configure() {
		bindInterceptor(Matchers.any(),
				Matchers.annotatedWith(CheckSession.class), this);
	}


}