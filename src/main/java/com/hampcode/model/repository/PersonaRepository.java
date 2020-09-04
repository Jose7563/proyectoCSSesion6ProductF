package com.hampcode.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.hampcode.model.entity.Persona;

@Named
public class PersonaRepository implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	
	@PersistenceContext(unitName="pwPU")
	private EntityManager em;
	
	
	public Long insert(Persona persona) throws Exception {
		em.persist(persona);
		return persona.getId();
	}
	
	
	public Long update(Persona persona) throws Exception {
		em.merge(persona);
		return persona.getId();
	}
	
	
	public void delete(Persona persona) throws Exception {
		em.remove(persona);
	}
	
	
	public List<Persona> findAll() throws Exception{
		List<Persona> personas=new ArrayList<>();
		
		TypedQuery<Persona> query=em.createQuery("FROM Persona p"
				,Persona.class);
		personas=query.getResultList();
		
		return personas;
	}
	
	
	public Optional<Persona> findById(Long id) throws Exception{
		Persona personaFound;
		
		TypedQuery<Persona> query=em.createQuery("FROM Persona p WHERE p.id=?1"
				,Persona.class);
		
		
		
		query.setParameter(1, id);
		personaFound=query.getSingleResult();
		
		return Optional.of(personaFound);
	}
	
	
	public List<Persona> findByName(String name) throws Exception{
		List<Persona> products=new ArrayList<>();
		
		TypedQuery<Persona> query=em.createQuery("FROM Product p WHERE p.name LIKE ?1"
				,Persona.class);
		query.setParameter(1, "%"+name+"%");
		products=query.getResultList();
		
		return products;
	}
	

	
	

	
}
