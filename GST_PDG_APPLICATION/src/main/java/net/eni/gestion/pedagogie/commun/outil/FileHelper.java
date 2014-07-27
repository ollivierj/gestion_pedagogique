package net.eni.gestion.pedagogie.commun.outil;

import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import net.eni.gestion.pedagogie.commun.composant.FileBean;
import net.eni.gestion.pedagogie.service.BlobService;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;

public class FileHelper {

	/**
	 * 
	 * @param request
	 * @return
	 */
	public static FileBean parseMultipart(HttpServletRequest request,
			BlobService blobService) {
		FileBean bean = null;
		if (ServletFileUpload.isMultipartContent(request)) {
			bean = new FileBean();
			ServletFileUpload uploadHandler = new ServletFileUpload();
			InputStream stream = null;
			bean.setFormFieldsMap(new HashMap<String, String>());
			try {
				FileItemIterator itr = uploadHandler.getItemIterator(request);
				while (itr.hasNext()) {
					FileItemStream item = itr.next();
					String name = item.getFieldName(); // form field name
					stream = item.openStream();
					if (item.isFormField()) {
						String value = Streams.asString(stream);
						bean.getFormFieldsMap().put(name, value);
					} else {
						bean.setFilename(item.getName());
						bean.setContentType(item.getContentType());
						bean.setSize(blobService.uploadBlob(stream,
								item.getName()));
					}
				}
			} catch (FileUploadException ex) {
				ex.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				IOUtils.closeQuietly(stream);
			}
		}
		return bean;
	}
}
