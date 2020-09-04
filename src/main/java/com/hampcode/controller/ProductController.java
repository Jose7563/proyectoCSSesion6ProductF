package com.hampcode.controller;


import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.hampcode.business.ProductBusiness;
import com.hampcode.business.SupplierBusiness;
import com.hampcode.model.entity.Product;
import com.hampcode.model.entity.Supplier;
import com.hampcode.util.Message;

@Named
@SessionScoped
public class ProductController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProductBusiness productBusiness;
	
	@Inject  
	private SupplierBusiness supplierBusiness;

	private UploadedFile uploadedFile;
	private Product product; //NuevoProducto
	private List<Product> products;//ListaProductos
	private Product productSelect;//Producto Seleccionado Editar
	private String filterName;// Criterio de Busqueda

	
//	private List<Product> productCategory1;

	
	private BarChartModel barModel;
	private PieChartModel pieModel;
	
	
	
	
	
	 String so_ = System.getProperty("os.name");
	    String ruta_temporal = "";

	    public void handleFileUpload(FileUploadEvent event) {
	        if (so_.equalsIgnoreCase("linux")) {
	            ruta_temporal = "/tmp/";
	        } else {
	            ruta_temporal = "C:/Windows/";
	        }
	         uploadedFile = event.getFile();
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
	public void listar() throws Exception {
		
	List<Product> productos;
				
				
		try {
			productos = productBusiness.getAll();
			graficar(productos);
			graficarChart(productos);
		} catch (Exception e) {
			Message.messageError("Error Carga de Productos :" + e.getMessage());
		}
	}
	
	
	
	
	public void graficarChart(List<Product> lista) {
		barModel = new BarChartModel(); 
		
		for(Product pro : lista) {
			ChartSeries serie = new BarChartSeries();
			serie.setLabel(pro.getName());
			serie.set(pro.getName(),pro.getUnitsInStock());
			barModel.addSeries(serie);
		}
		
		barModel.setTitle("Unidades en Stock de Porductos");
		barModel.setLegendPosition("ne");
		barModel.setAnimate(true);
		Axis xAxis= barModel.getAxis(AxisType.X);
		xAxis.setLabel("Nombre de Producto");
		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("cantida en stock de Producto");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}
	
	
	
	public void graficar(List<Product> lista){
		pieModel = new PieChartModel(); 
		
		for (Product pro : lista) {
			pieModel.set(pro.getName(), pro.getUnitsInStock());
			
		}
		pieModel.setTitle("Datos");
		pieModel.setLegendPosition("e");
		pieModel.setFill(false);
		pieModel.setShowDataLabels(true);
		pieModel.setDiameter(150);
		}
	
	
	private Supplier supplier;
	private List<Supplier> suppliers;
	
	@PostConstruct
	public void init() {
		product = new Product();
		products = new ArrayList<Product>();
		supplier=new Supplier();
		suppliers=new ArrayList<Supplier>();
		
		
		getAllProducts();
		
		
	}

	

	
	
	
	
	public void getAllProducts() {
		try {
			products = productBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de Productos :" + e.getMessage());
		}
	}
	
	public void getAllSuppliers() {
		try {
			suppliers = supplierBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de Proveedores :" + e.getStackTrace());
		}
	}

	public String newProduct() {		
		resetForm();
		getAllSuppliers();
		return "insert.xhtml";
	}

	public String listProduct() {
		return "list.xhtml";
	}

	public String saveProduct() {
		String view = "";
		try {
			
//			InputStream input= file.getInputstream(); 
//			ByteArrayOutputStream output = new ByteArrayOutputStream();
//			byte[] buffer = new byte[1024];
//
//			for (int  length =0; (length=input.read(buffer))>0;) {
//				output.write(buffer,0, length);
//			}
			if (product.getId() != null) {
				product.setSupplier(supplier);
//				product.setImage(output.toByteArray());
				productBusiness.update(product);
				Message.messageInfo("Registro actualizado exitosamente");
			} else {
				product.setSupplier(supplier);
				productBusiness.insert(product);
				Message.messageInfo("Registro guardado exitosamente");

			}
			this.getAllProducts();
			resetForm();
			view = "list";
		} catch (Exception e) {
			Message.messageError("Error Product :" + e.getStackTrace());
		}
		return view;
	}

	public String editProduct() {
		String view = "";
		try {
			if (this.productSelect != null) {
				this.product = productSelect;

				view = "update";// Vista
			} else {
				Message.messageInfo("Debe seleccionar un producto");
			}
		} catch (Exception e) {
			Message.messageError("Error Product :" + e.getMessage());
		}

		return view;
	}

	public void searchProductByName() {
		try {

			products = productBusiness.getProductsByName(this.filterName.trim());
			resetForm();
			if (products.isEmpty()) {
				Message.messageInfo("No se encontraron productos");

			}

		} catch (Exception e) {
			Message.messageError("Error Product Search :" + e.getMessage());
		}
	}

	public void selectProduct(SelectEvent e) {
		this.productSelect = (Product) e.getObject();
	}

	public void resetForm() {
		this.filterName="";
		this.product = new Product();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getProductSelect() {
		return productSelect;
	}

	public void setProductSelect(Product productSelect) {
		this.productSelect = productSelect;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	
	//Suppliers
	
	
	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}
		
	public Supplier getSupplier() {
			return supplier;
		}

	public void setSupplier(Supplier supplier) {
			this.supplier = supplier;
		}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}




	public BarChartModel getBarModel() {
		return barModel;
	}




	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}




	
	
	

	

}
