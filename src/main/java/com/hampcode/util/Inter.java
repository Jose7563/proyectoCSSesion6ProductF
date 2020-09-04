package com.hampcode.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class Inter implements Serializable {

	private Locale locale;
	private List<String> languages;

	public String getLanguage() {
		return getLocale().getLanguage();
	}

	public void setLanguage(String language) {
		locale = Locale.forLanguageTag(language);
	}

	public List<String> getPossibleLanguages() {
		if (languages == null) {
			languages = new ArrayList<>();
			Iterator<Locale> supportedLocales = FacesContext.getCurrentInstance().getApplication()
					.getSupportedLocales();
			while (supportedLocales.hasNext())
				languages.add(supportedLocales.next().getLanguage());
		}
		return languages;
	}

	private Locale getLocale() {
		if (locale == null)
			locale = FacesContext.getCurrentInstance().getApplication().getDefaultLocale();
		// in real life this should come from database
		return (locale != null) ? locale : Locale.getDefault();
	}

	private static final long serialVersionUID = 1L;

}
