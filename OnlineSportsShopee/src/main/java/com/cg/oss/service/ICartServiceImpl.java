package com.cg.oss.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.oss.entities.Cart;
import com.cg.oss.entities.Product;
import com.cg.oss.exception.ResourceNotFoundException;
import com.cg.oss.repopsitories.ICartRepository;
import com.cg.oss.repopsitories.IProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ICartServiceImpl implements ICartService {

	@Autowired
	private ICartRepository cartRepo;
	
	@Autowired 
	private IProductRepository productRepo;

	@Override
	public Cart addCart(Cart cart) throws ResourceNotFoundException {
		long productId = cart.getProduct().getProductId();
		Optional<Product> product1 = productRepo.findById(productId);
		Product product = product1.get();
		if(!product.isInStock()) {
			throw new ResourceNotFoundException("OutOf Stock");
		}
		cart.setImageName(product.getImageName());
		cart.setPrice(product.getPriceAfterDiscount());
		cart.setProductName(product.getProductName());
        int quantity = cart.getQuantity();
		double sum =0;
 		for(int i=1;i<=quantity;i++) {
 			sum = sum + cart.getPrice();
 		}
 		cart.setTotal(sum);
		return cartRepo.save(cart);
	}

	@Override
	public Cart deleteCart(long cartId) throws ResourceNotFoundException {
		Optional<Cart> cart = cartRepo.findById(cartId);
		if (!cart.isPresent()) {
			throw new ResourceNotFoundException("No Cart Details Found For Deletion");
		}
		cartRepo.delete(cart.get());
		return cart.get();
	}

	@Override
	public Cart updateCart(long cartId, Cart cart) throws ResourceNotFoundException {
		
		  Optional<Cart> cart1 =  cartRepo.findById(cartId);
	        if (!cart1.isPresent()) {
	        	throw new ResourceNotFoundException("Cart Not Found");
	        }
	        else  {
	        	long productId = cart.getProduct().getProductId();
	    		Optional<Product> product1 = productRepo.findById(productId);
	    		Product product = product1.get();
	    		if(!product.isInStock()) {
	    			throw new ResourceNotFoundException("OutOfStock");
	    		}
	    		cart.setCartId(cartId);
	    		cart.setImageName(product.getImageName());
	    		cart.setPrice(product.getPriceAfterDiscount());
	    		cart.setProductName(product.getProductName());
	            int quantity = cart.getQuantity();
	    		double sum =0;
	     		for(int i=1;i<=quantity;i++) {
	     			sum = sum + cart.getPrice();
	     		}
	     		cart.setTotal(sum);
	            return cartRepo.save(cart);
	        }
	}

	@Override
	public Cart getCartDetails(long cartId) throws ResourceNotFoundException {
		Optional<Cart> cart = cartRepo.findById(cartId);
		if (!cart.isPresent()) {
			throw new ResourceNotFoundException("No Cart Details Found For View Cart");
		}
		return cart.get();

	}

	@Override
	public List<Cart> getAllCartDetails() {
		// TODO Auto-generated method stub
		return cartRepo.findAll();
	}

}
