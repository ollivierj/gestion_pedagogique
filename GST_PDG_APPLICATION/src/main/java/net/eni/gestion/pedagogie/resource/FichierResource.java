package net.eni.gestion.pedagogie.resource;

import java.util.List;

import javax.ws.rs.core.Response;

import net.eni.gestion.pedagogie.commun.composant.FileBean;
import net.eni.gestion.pedagogie.commun.composant.GenericException;

import com.sun.jersey.multipart.FormDataMultiPart;

public interface FichierResource {

	/**
	 * Ressource pour le depot de fichier
	 * @param form
	 * @return
	 */
	public abstract String deposer(FormDataMultiPart form);

	/**
	 * Ressource pour le telechargement de fichier
	 * @param pType
	 * @param pId
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public abstract Response telecharger(String pType, String pId,
			String filename) throws Exception;

	/**
	 * Ressource pour le chargement d'une liste de propriétés de fichiers
	 * @param pType
	 * @param pId
	 * @return
	 * @throws GenericException
	 */
	public abstract List<FileBean> charger(String pType, String pId)
			throws GenericException;

	/**
	 * Ressource pour la suppression de fichier
	 * @param pType
	 * @param pId
	 * @param filename
	 * @return
	 */
	public abstract String supprimer(String pType, String pId, String filename);
}