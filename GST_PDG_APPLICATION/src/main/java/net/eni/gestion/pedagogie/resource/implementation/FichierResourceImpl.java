package net.eni.gestion.pedagogie.resource.implementation;

import java.io.File;
import java.util.List;

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

import net.eni.gestion.pedagogie.commun.composant.FileBean;
import net.eni.gestion.pedagogie.errorhandling.ApplicationException;
import net.eni.gestion.pedagogie.resource.FichierResource;
import net.eni.gestion.pedagogie.service.FichierService;

import com.google.inject.Inject;
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
	public String deposer(FormDataMultiPart form) {
		return fichierService.deposer(form);
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
	public Response telecharger(@PathParam("entite_type") String pType,
			@PathParam("entite_id") String pId,
			@PathParam("filename") String filename) throws Exception {
		return fichierService.telecharger(pType, pId, filename);
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
	public List<FileBean> charger(@PathParam("entite_type") String pType,
			@PathParam("entite_id") String pId) throws ApplicationException {
		return fichierService.charger(pType, pId);
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
	public String supprimer(@PathParam("entite_type") String pType,
			@PathParam("entite_id") String pId,
			@PathParam("filename") String filename) {
		return fichierService.supprimer(pType, pId, filename);
	}

	@GET
	@Path("image/{stagiaireId}")
	@Produces("image/*")
	public Response getImage(@PathParam("stagiaireId") Integer pStagiaireId) {
		File lFile = fichierService.getStagiairePhoto(pStagiaireId);
		if (!lFile.exists()) {
			throw new WebApplicationException(404);
		}
		String lMimeType = new MimetypesFileTypeMap().getContentType(lFile);
		return Response.ok(lFile, lMimeType).build();
	}

}
