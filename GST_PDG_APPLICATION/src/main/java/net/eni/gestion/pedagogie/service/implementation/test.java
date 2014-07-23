package net.eni.gestion.pedagogie.service.implementation;

import java.io.File;
import java.util.ArrayList;

public class test {
	public void listf(String directoryName, ArrayList<File> files) {
	    File directory = new File(directoryName);

	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile()) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	            listf(file.getAbsolutePath(), files);
	        }
	    }
	}
	public static void main(String[] args) {
		ArrayList<File> files = new ArrayList<File>();
		test test = new test();
		test.listf("\\\\freebox\\elements", files);
		System.out.println(files);
	}

}
