package net.eni.gestion.pedagogie.service.implementation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.eni.gestion.pedagogie.commun.composant.FileBean;
import net.eni.gestion.pedagogie.commun.composant.PropertyFileLoader;
import net.eni.gestion.pedagogie.service.FichierService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.sun.jersey.core.header.ContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

public class FichierServiceImpl implements FichierService {
	
	private PropertyFileLoader propertyFileLoader = PropertyFileLoader
			.getInstance("fichiers");
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.implementation.testa#deposer(com.sun.jersey.multipart.FormDataMultiPart)
	 */
	@Override
	public String deposer(FormDataMultiPart form) {
		String entiteType = form.getField("entite_type").getValue();
		String entiteId = form.getField("entite_id").getValue();
		StringBuilder lStrBuilder = new StringBuilder();
		lStrBuilder.append(getDirectoryLocation(entiteType, entiteId));
		FormDataBodyPart filePart = form.getField("file");
		ContentDisposition headerOfFilePart = filePart.getContentDisposition();
		lStrBuilder.append(headerOfFilePart.getFileName());
		InputStream fileInputStream = filePart.getValueAs(InputStream.class);
		String lFilePath = lStrBuilder.toString();
		saveFile(fileInputStream, lFilePath);
		return lFilePath;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.implementation.testa#telecharger(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Response telecharger(String pType, String pId, String filename) throws Exception {
		StringBuilder lStrBuilder = new StringBuilder();
		lStrBuilder.append(getDirectoryLocation(pType, pId));
		lStrBuilder.append(filename);
		byte[] docStream = renvoyer(lStrBuilder.toString());
		return Response
				.ok(docStream, MediaType.APPLICATION_OCTET_STREAM)
				.header("content-disposition",
						"attachment; filename = " + filename).build();
	}
	
	/**
	 * Renvoie d'un fichier vers la partie client
	 * @param chemin
	 * @return
	 */
	private byte[] renvoyer(String chemin) {
		File file = new File(chemin);
		byte[] docStream = null;
		try {
			docStream = FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return docStream;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.implementation.testa#supprimer(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String supprimer(String pType, String pId, String filename) {
		StringBuilder lStrBuilder = new StringBuilder();
		lStrBuilder.append(getDirectoryLocation(pType, pId));
		lStrBuilder.append(filename);
		String lFilePath = lStrBuilder.toString();
		FileUtils.deleteQuietly(new File(lFilePath));
		return lFilePath;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.implementation.testa#charger(java.lang.String, java.lang.String)
	 */
	@Override
	public List<FileBean> charger(String pType, String pId) {
		String repertoire = getDirectoryLocation(pType, pId);
		List<FileBean> list = new ArrayList<FileBean>();
		if (null==repertoire){
			return list;
		}
		File lFile = new File(repertoire);
		if(lFile.exists() && lFile.isDirectory()){
			Iterator<File> files = FileUtils.iterateFiles(lFile, propertyFileLoader.getValue("valid.file.extentions").split("\\|"), false);
			while (files.hasNext()) {
				File file = files.next();
				FileBean fileBean = new FileBean(file.getName(), file.length(),
				file.getAbsolutePath(), FilenameUtils.getExtension(file.getName()));
				list.add(fileBean);
			}
		}
		return list;
	}
	
	/**
	 * Sauvegarde d'un fichier vers le repertoire de destination
	 * @param uploadedInputStream
	 * @param serverLocation
	 */
	private void saveFile(InputStream uploadedInputStream, String chemin) {
		try {
			File lFile = new File(chemin);
			lFile.getParentFile().mkdirs();
			OutputStream outpuStream = new FileOutputStream(lFile);
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
			uploadedInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode permettant de déterminer le chemin de dépot/telechargement en 
	 * fonction d'un type d'entité et d'un identifiant
	 * @param entite_type
	 * @param entite_id
	 * @return
	 */
	private String getDirectoryLocation(String entite_type, String entite_id){
		StringBuilder lStrBuilder = new StringBuilder();
		lStrBuilder.append(propertyFileLoader.getValue("directory.location"));
		switch (entite_type) {
		case "SujetEvaluation":
			lStrBuilder.append(propertyFileLoader.getValue("directory.sujetevaluation.location"));
			break;
		case "Evaluation":
			lStrBuilder.append(propertyFileLoader.getValue("directory.evaluation.location"));
			break;
		case "SessionValidation":
			lStrBuilder.append(propertyFileLoader.getValue("directory.sessionvalidation.location"));
		case "TitreProfessionnel":
			lStrBuilder.append(propertyFileLoader.getValue("directory.titreprofessionnel.location"));
			break;
		default:
			return null;
		}
		lStrBuilder.append(entite_id);
		lStrBuilder.append("\\");
		return lStrBuilder.toString();
	}
}
