//package com.hampcode.model.repository;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.inject.Named;
//import javax.management.Query;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import com.hampcode.model.entity.Product;
//import com.hampcode.model.entity.ProductByCategory;
//
//@Named
//public class ProductByCategoryRepository implements Serializable {
//
//	
//	private static final long serialVersionUID = 1L;
//	
//	@PersistenceContext(unitName="pwPU")
//	private EntityManager em;
//	
//	List<ProductByCategory> products=null;
//	
//	public List<ProductByCategory> finByProductCategory() throws Exception{
//		
//		
//		Query query=em.createQuery(" select  com.hampcode.model.entity.ProductByCategory FROM Product p"
//				,Product.class);
//		products=query.getResultList();
//		
//		return products;
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//
//}
