package com.hampcode.controller;


import java.io.FileOutputStream;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@SessionScoped
public class Archivo   implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 String so_ = System.getProperty("os.name");
	    String ruta_temporal = "";

	    public void handleFileUpload(FileUploadEvent event) {
	        if (so_.equalsIgnoreCase("linux")) {
	            ruta_temporal = "/tmp/";
	        } else {
	            ruta_temporal = "C:/Windows/";
	        }
	        UploadedFile uploadedFile = event.getFile();
	        String fileName = uploadedFile.getFileName();
	        byte[] contents = uploadedFile.getContents();
	        try {
	            FileOutputStream fos = new FileOutputStream(ruta_temporal + fileName.replace(" ", ""));
	            fos.write(contents);
	            fos.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        FacesMessage message = new FacesMessage("CARGA CORRECTA", event.getFile().getFileName() + " cargado al sistema");
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }

	
	
}
