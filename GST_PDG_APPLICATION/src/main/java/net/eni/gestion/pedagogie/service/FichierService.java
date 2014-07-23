package net.eni.gestion.pedagogie.service;

import java.io.InputStream;

public interface FichierService {
	public void saveFile(InputStream uploadedInputStream, String serverLocation);
}
