package net.eni.gestion.pedagogie.resource.implementation;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.eni.gestion.pedagogie.commun.composant.FileBean;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.commun.outil.FileHelper;
import net.eni.gestion.pedagogie.resource.FichierResource;
import net.eni.gestion.pedagogie.service.BlobService;

import com.google.inject.Inject;
import com.sun.jersey.api.NotFoundException;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.core.header.FormDataContentDisposition;

@Path("/fichiers")
public class FichierResourceImpl implements FichierResource {

	private final BlobService blobService;
    
    /**
     * Constructeur
     * @param MService
     */
    @Inject
    public FichierResourceImpl(BlobService pBlobService) {
    	blobService = pBlobService;
    }
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.resource.implementation.FichierResource#uploadFile(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@POST
	@Path("deposer")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadFile(@Context HttpServletRequest request,
			@Context HttpServletResponse res) throws Exception {
		String response = "Unable to attach files";
		FileBean bean = FileHelper.parseMultipart(request, blobService);
		if (null != bean) {
			response = "{\"name\":\"" + bean.getFilename() + "\",\"type\":\""
					+ bean.getContentType() + "\",\"size\":\"" + bean.getSize()
					+ "\"}";
		}
		return Response.ok(response).build();
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.resource.implementation.FichierResource#downloadFile(java.lang.String)
	 */
	@GET
	@Path("telecharger")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadFile(
			@DefaultValue("empty") @QueryParam(value = "blobKey") String blobKey)
			throws Exception {
		if (blobKey.equals("empty"))
			throw new NotFoundException("blobKey cannot be empty!");

		byte[] docStream = blobService.getBlob(blobKey);
		return Response
				.ok(docStream, MediaType.APPLICATION_OCTET_STREAM)
				.header("content-disposition",
						"attachment; filename = " + blobKey).build();
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.resource.implementation.FichierResource#listFiles()
	 */
	@GET
	@Path("charger")
	@Produces(MediaType.APPLICATION_JSON)
    public List<FileBean> listFiles() throws GenericException {
		return blobService.getBlobs();
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.resource.implementation.FichierResource#deleteFile(java.lang.String)
	 */
	@GET
	@Path("supprimer")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteFile(
			@DefaultValue("empty") @QueryParam(value = "blobKey") String blobKey) {

		if (blobKey.equals("empty"))
			throw new NotFoundException("blobKey cannot be empty!");

		blobService.deleteBlob(blobKey);
		return Response.status(Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.resource.implementation.FichierResource#uploadFile(java.io.InputStream, com.sun.jersey.core.header.FormDataContentDisposition)
	 */
	public Response uploadFile(InputStream fileInputStream,
			FormDataContentDisposition contentDispositionHeader) {
		// TODO Auto-generated method stub
		return null;
	}
}
