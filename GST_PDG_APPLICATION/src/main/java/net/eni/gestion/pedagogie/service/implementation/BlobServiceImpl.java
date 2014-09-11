package net.eni.gestion.pedagogie.service.implementation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.FileBean;
import net.eni.gestion.pedagogie.commun.composant.PropertyFileLoader;
import net.eni.gestion.pedagogie.service.BlobService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.CountingInputStream;

public class BlobServiceImpl implements BlobService {

	private PropertyFileLoader propertyFileLoader = PropertyFileLoader
			.getInstance("fichiers");

	public long uploadBlob(InputStream inputStream, String filename) {
		Writer output = null;
		CountingInputStream countingInputStream = null;
		long filesize = 0;
		try {
			// if uploading form IE it get complete path
			filename = FilenameUtils.getName(filename);
			output = new FileWriter(new File(getDirectoryLoation() + filename));
			countingInputStream = new CountingInputStream(inputStream);
			IOUtils.copy(countingInputStream, output);
			filesize = countingInputStream.getByteCount();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(countingInputStream);
			IOUtils.closeQuietly(output);
		}
		return filesize;
	}

	/**
		  * 
		  */
	public byte[] getBlob(String blobKey) {
		File file = new File(getDirectoryLoation() + blobKey);
		byte[] docStream = null;
		try {
			docStream = FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return docStream;
	}

	public void deleteBlob(String blobKey) {
		FileUtils.deleteQuietly(new File(getDirectoryLoation() + blobKey));
	}

	/**
		  * 
		  */
	public List<FileBean> getBlobs() {

		List<FileBean> list = new ArrayList<FileBean>();
		Iterator<File> files = FileUtils.iterateFiles(new File(
				getDirectoryLoation()), getValidFileExtentions(), false);

		while (files.hasNext()) {
			File file = files.next();
			FileBean fileBean = new FileBean(file.getName(), file.length(),
					file.getAbsolutePath(), FilenameUtils.getExtension(file
							.getName()));
			list.add(fileBean);
		}
		return list;
	}

	public String getDirectoryLoation() {
		return propertyFileLoader.getValue("directory.location");
	}

	public String[] getValidFileExtentions() {
		return propertyFileLoader.getValue("valid.file.extentions")
				.split("\\|");
	}
}
