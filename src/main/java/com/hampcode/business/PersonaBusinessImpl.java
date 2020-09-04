package com.hampcode.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.hampcode.model.entity.Persona;
import com.hampcode.model.entity.Product;
import com.hampcode.model.repository.PersonaRepository;

@Named
public class PersonaBusinessImpl implements Serializable{


	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private PersonaRepository personaRepository;
	
	@Transactional
	public void delete(Persona persona) throws Exception {
		personaRepository.delete(persona);
	}

	
	@Transactional
	public Long insert(Persona persona) throws Exception {
		return personaRepository.insert(persona);
	}

	
	@Transactional
	public Long update(Persona persona) throws Exception{
		return personaRepository.update(persona);
	}
	
	
	public List<Persona> getAll() throws Exception {
		return personaRepository.findAll();
	}
	
	
	public List<Persona> getProductsByName(String name) throws Exception{
		return personaRepository.findByName(name);
	}

	
	

}
