package net.eni.gestion.pedagogie.service;

import java.util.List;

import javax.ws.rs.core.Response;

import net.eni.gestion.pedagogie.commun.composant.FileBean;

import com.sun.jersey.multipart.FormDataMultiPart;

public interface FichierService {
	
	/**
	 * Methode pour l'upload de fichier
	 * @param form
	 * @return
	 */
	public abstract String deposer(FormDataMultiPart form);

	/**
	 * Methode pour le telechargement de fichier 
	 * @param pType
	 * @param pId
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public abstract Response telecharger(String pType, String pId,
			String filename) throws Exception;

	/**
	 * Methode pour la suppression de fichier
	 * @param pType
	 * @param pId
	 * @param filename
	 * @return
	 */
	public abstract String supprimer(String pType, String pId, String filename);

	/**
	 * Methode pour le chargement des informations des fichiers presents dans un repertoire
	 * @param pType
	 * @param pId
	 * @return
	 */
	public abstract List<FileBean> charger(String pType, String pId);
	  
	 
}
