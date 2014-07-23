package net.eni.gestion.pedagogie.resource;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import net.eni.gestion.pedagogie.commun.composant.FileBean;
import net.eni.gestion.pedagogie.commun.composant.GenericException;

import com.sun.jersey.core.header.FormDataContentDisposition;

public interface FichierResource {

	public abstract Response uploadFile(HttpServletRequest request,
			HttpServletResponse res) throws Exception;

	/**
	 * In Memory solution
	 * 
	 * @param blobKey
	 * @return
	 * @throws Exception
	 */
	public abstract Response downloadFile(String blobKey) throws Exception;

	/**
	 * list all valid files in a directory
	 * 
	 * @return
	 * @throws GenericException
	 */
	public abstract List<FileBean> listFiles() throws GenericException;

	/**
	 * remove file from directory
	 * 
	 * @param blobKey
	 * @return
	 */
	public abstract Response deleteFile(String blobKey);

	public abstract Response uploadFile(InputStream fileInputStream,
			FormDataContentDisposition contentDispositionHeader);

}