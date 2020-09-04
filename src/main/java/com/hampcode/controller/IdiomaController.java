package com.hampcode.controller;

//import java.io.Serializable;
//import java.util.LinkedHashMap;
//import java.util.Locale;
//import java.util.Map;
//
//import javax.enterprise.context.SessionScoped;
//import javax.faces.context.FacesContext;
//import javax.faces.event.ValueChangeEvent;
//import javax.inject.Named;
//
//@Named
//@SessionScoped
//public class IdiomaController implements Serializable {
//	
//	 /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	private String codigoIdioma;
//	    
//	    private Map<String,Object> listaIdiomas;
//	    
//	    public IdiomaController() {
//	        super();
//	        this.codigoIdioma = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
//	        this.listaIdiomas = new LinkedHashMap<String,Object>();
//	        this.listaIdiomas.put("Español", new Locale("es"));
//	        this.listaIdiomas.put("Inglés", new Locale("en"));
//	    }
//
//	    public String getCodigoIdioma() {
//	        return codigoIdioma;
//	    }
//	    
//	    public void setCodigoIdioma(String codigoIdioma) {
//	        this.codigoIdioma = codigoIdioma;
//	    }
//	    
//	    public Map<String, Object> getListaIdiomas() {
//	        return listaIdiomas;
//	    }
//	    
//	    public void doCambioIdiomaConLista(ValueChangeEvent e)
//	    {
//
//	        String newCodigoIdioma = e.getNewValue().toString();
//	        System.out.println("newCodigoIdioma=" + newCodigoIdioma);
//	        System.out.println("idiomaBean=" + this.toString());
//
//	        //loop country map to compare the locale code
//	        for (Map.Entry<String, Object> entry : listaIdiomas.entrySet()) 
//	        {
//
//	               if(entry.getValue().toString().equals(newCodigoIdioma))
//	               {
//	                   System.out.println("Asignando nueva locale al contexto de Faces.");
//	                   FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale)entry.getValue());
//	               }
//	        }
//	    }
//	    
//	    public void doCambioIdiomaConImagen(String nuevoIdioma)
//	    {
//
//	        //loop country map to compare the locale code
//	        for (Map.Entry<String, Object> entry : listaIdiomas.entrySet()) 
//	        {
//
//	               if(entry.getValue().toString().equals(nuevoIdioma))
//	               {
//	                   System.out.println("Pinchado en imagen " + nuevoIdioma + ". Asignando nueva locale al contexto de Faces");
//	                   this.codigoIdioma = nuevoIdioma;
//	                   FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale)entry.getValue());
//	               }
//	        }
//	    }    
//	   
////	    @Override
////	    public String toString() {
////	        return "IdiomaBean [codigoIdioma=" + codigoIdioma + ", listaIdiomas=" + listaIdiomas + "]";
////	    }     
//
//}
//
//
