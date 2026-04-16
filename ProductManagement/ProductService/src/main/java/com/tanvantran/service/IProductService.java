package com.tanvantran.service;

import java.util.List;

import com.tanvantran.entity.Product;
import com.tanvantran.form.ProductFormForCreating;
import com.tanvantran.form.ProductFormForUpdating;

public interface IProductService {
	public List<Product> getAllProducts();

	public Product getProductById(short id);

	public Product createNewProduct(ProductFormForCreating productNewForm);

	public Product updateProduct(short id, ProductFormForUpdating productUpdateForm);

	public void deleteProductById(short id);

}

