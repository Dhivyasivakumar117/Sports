package com.cg.oss.controller;

import java.time.LocalDate;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oss.entities.Product;
import com.cg.oss.exception.ResourceNotFoundException;
import com.cg.oss.service.IProductService;

@RestController
public class IProductController {
	
	@RequestMapping("/")
	public String home() {
		return "Hello..., Welcome to the Online Sports Shopee";
	}

	@Autowired
	private IProductService product_service;

	@RequestMapping(value = "/product/all", method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		return product_service.getAllProduct();
	}

	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public Product addProduct(@Valid @RequestBody Product new_product) {
		return product_service.addProduct(new_product);
	}

	
	
	@RequestMapping(value ="/product/id/{id}", method = RequestMethod.GET)
    public Product getProductDetails(@PathVariable("id") int product_Id) throws ResourceNotFoundException {
            Product product = product_service.getProduct(product_Id);
            if(product==null) {
                throw new ResourceNotFoundException("Product Not Found");
            } 
			   LocalDate now = LocalDate.now(); 
			   product.setEstimatedDelivery(now.plusDays(5));
            return product;
    }

	@RequestMapping(value = "/product/delete/{id}", method = RequestMethod.DELETE)
	public Product removeProduct(@PathVariable("id") int product_Id) throws  ResourceNotFoundException {
			Product product = product_service.removeProduct(product_Id);
			if (product == null) { 
				throw new ResourceNotFoundException("Product Not Found");
			}
			return product;
	}

	@RequestMapping(value = "/product/update/{id}", method = RequestMethod.PUT)
	public Product updateProduct(@PathVariable("id") int id, @RequestBody Product product) throws ResourceNotFoundException{

			Product product1 = product_service.updateProduct(id, product);
			if (product1 == null) {
				throw new ResourceNotFoundException("Product Not Found");
			}
			return product1;
		
	}

	@RequestMapping(value = "/product/name/{name}", method = RequestMethod.GET)
	public List<Product> findByProductByName(@PathVariable("name") String name) throws ResourceNotFoundException{

			List<Product> product = product_service.getProductsByName(name);
			if (product.isEmpty()) {
				throw new ResourceNotFoundException("Product Not Found");
			}
			return product;
		
	}

	@RequestMapping(value = "/product/size/{size}", method = RequestMethod.GET)
	public List<Product> findByProductBySize(@PathVariable("size") String size) throws ResourceNotFoundException{
			List<Product> product = product_service.getProductsBySize(size);
			if (product.isEmpty()) {
				throw new ResourceNotFoundException("Product Not Found");
			}
			return product;
	}

	@RequestMapping(value = "/product/price/{price}", method = RequestMethod.GET)
	public List<Product> findByProductByPrice(@PathVariable("price") double price) throws  ResourceNotFoundException{
			List<Product> product = product_service.getProductsByPrice(price);
			if (product.isEmpty()) {
				throw new ResourceNotFoundException("Product Not Found");
			}
			return product;
	}

	@RequestMapping(value = "/product/color/{color}", method = RequestMethod.GET)
	public List<Product> findByProductByColor(@PathVariable("color") String color) throws ResourceNotFoundException{
			List<Product> product = product_service.getProductsByColor(color);
			if (product.isEmpty()) {
				throw new ResourceNotFoundException("Product Not Found");
			}
			return product;
	}

}
