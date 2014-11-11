package net.eni.gestion.pedagogie.resource.implementation;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.eni.gestion.pedagogie.commun.composant.authentification.annotation.CheckSession;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.fichier.FileBean;
import net.eni.gestion.pedagogie.resource.FichierResource;
import net.eni.gestion.pedagogie.service.FichierService;

import com.google.inject.Inject;
import com.j256.ormlite.misc.TransactionManager;
import com.sun.jersey.multipart.FormDataMultiPart;

@Path("/fichiers")
public class FichierResourceImpl implements FichierResource {

	/**
	 * Service de gestion des fichiers
	 */
	private final FichierService fichierService;

	@Inject
	public FichierResourceImpl(FichierService pFichierService) {
		fichierService = pFichierService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.eni.gestion.pedagogie.resource.implementation.test#deposer(com.sun
	 * .jersey.multipart.FormDataMultiPart)
	 */
	@POST
	@Path("/deposer")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@CheckSession
	public String deposer(final FormDataMultiPart form) throws ApplicationException {
		try {
			return TransactionManager.callInTransaction(
					Connexion.getInstance().getConnexion(),
					new Callable<String>() {
						public String call()
								throws ApplicationException {
							return fichierService.deposer(form);
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.eni.gestion.pedagogie.resource.implementation.test#telecharger(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	@GET
	@Path("telecharger/{entite_type}/{entite_id}/{filename}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@CheckSession
	public Response telecharger(@PathParam("entite_type") final String pType,
			@PathParam("entite_id") final String pId,
			@PathParam("filename") final String filename) throws Exception {
		try {
			return TransactionManager.callInTransaction(
					Connexion.getInstance().getConnexion(),
					new Callable<Response>() {
						public Response call()
								throws ApplicationException {
							try {
								return fichierService.telecharger(pType, pId, filename);
							} catch (Exception e) {
								throw new ApplicationException("Erreur los du téléchargement du fichier");
							}
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.eni.gestion.pedagogie.resource.implementation.test#charger(java.lang
	 * .String, java.lang.String)
	 */
	@GET
	@Path("charger/{entite_type}/{entite_id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CheckSession
	public List<FileBean> charger(@PathParam("entite_type") final String pType,
			@PathParam("entite_id") final String pId) throws ApplicationException {
		try {
			return TransactionManager.callInTransaction(
					Connexion.getInstance().getConnexion(),
					new Callable<List<FileBean>>() {
						public List<FileBean> call()
								throws ApplicationException {
							return fichierService.charger(pType, pId);
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.eni.gestion.pedagogie.resource.implementation.test#supprimer(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	@DELETE
	@Path("supprimer/{entite_type}/{entite_id}/{filename}")
	@Produces(MediaType.APPLICATION_JSON)
	@CheckSession
	public String supprimer(@PathParam("entite_type") final String pType,
			@PathParam("entite_id") final String pId,
			@PathParam("filename") final String filename) throws ApplicationException {
		try {
			return TransactionManager.callInTransaction(
					Connexion.getInstance().getConnexion(),
					new Callable<String>() {
						public String call()
								throws ApplicationException {
							return fichierService.supprimer(pType, pId, filename);
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}
	
	public final static String DEFAULT_IMAGE = "default.png";

	@GET
	@Path("image/{stagiaireId}")
	@Produces("image/*")
	@CheckSession
	public Response getImage(@PathParam("stagiaireId") final Integer pStagiaireId) throws ApplicationException {
		try {
			return TransactionManager.callInTransaction(
					Connexion.getInstance().getConnexion(),
					new Callable<Response>() {
						public Response call()
								throws ApplicationException {
							File lFile = fichierService.getStagiairePhoto(pStagiaireId);
							if (!lFile.exists()) {
								StringBuilder lStrBuilder = new StringBuilder();
								lStrBuilder.append("/net/eni/gestion/pedagogie/commun/configuration/");
								lStrBuilder.append(DEFAULT_IMAGE);
								lFile = new File(this.getClass().getResource(lStrBuilder.toString()).getFile());
							}
							if (!lFile.exists()){
								throw new WebApplicationException(404);
							}
							String lMimeType = new MimetypesFileTypeMap().getContentType(lFile);
							return Response.ok(lFile, lMimeType).build();
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
		
		
	}

}
