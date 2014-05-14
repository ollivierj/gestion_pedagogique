package net.eni.gestion.pedagogie.commun.composant;

import java.io.Serializable;

/**
 * @author jollivier
 * Classe de base pour la gestion des exceptions
 */
public class GenericException extends Exception implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	@Override
	public String getLocalizedMessage() {
		// TODO Auto-generated method stub
		return super.getLocalizedMessage();
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

	public GenericException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenericException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public GenericException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public GenericException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
