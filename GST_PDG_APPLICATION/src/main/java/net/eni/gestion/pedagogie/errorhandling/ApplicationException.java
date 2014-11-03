package net.eni.gestion.pedagogie.errorhandling;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.configuration.ApplicationConstants;

/**
 * Class to map application related exceptions
 * 
 * @author amacoder
 * 
 */
@XmlRootElement
public class ApplicationException extends Exception {
	private static final long serialVersionUID = -8999932578270387947L;
	/**
	 * contains redundantly the HTTP status of the response sent back to the
	 * client in case of error, so that the developer does not have to look into
	 * the response headers. If null a default
	 */
	Integer status;
	/** application specific error code */
	int code;
	/** link documenting the exception */
	String link;
	/** detailed error description for developers */
	String developerMessage;
	/** detailed error description for developers */
	String userMessage;

	/**
	 * 
	 * @param status
	 * @param code
	 * @param message
	 * @param developerMessage
	 * @param link
	 */
	public ApplicationException(int status, int code, String message,
			String developerMessage, String link) {
		super(message);
		this.status = status;
		this.code = code;
		this.developerMessage = developerMessage;
		this.userMessage = message;
		this.link = link;
	}
	
	public ApplicationException(String message){
		this(Response.Status.CONFLICT.getStatusCode(), 409, message,
		message, ApplicationConstants.GENERIC_LINK);
	}

	public ApplicationException() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
}
