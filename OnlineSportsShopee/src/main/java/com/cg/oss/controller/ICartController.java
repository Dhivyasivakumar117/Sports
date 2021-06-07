package com.cg.oss.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oss.entities.Cart;
import com.cg.oss.exception.ResourceNotFoundException;
import com.cg.oss.service.ICartService;

@RestController
public class ICartController {

	@Autowired
	private ICartService cartService;
	 

	@RequestMapping(value = "/cart/add", method = RequestMethod.POST)
	public Cart addCart(@Valid @RequestBody Cart cart) throws ResourceNotFoundException {
			Cart cart1 = cartService.addCart(cart);
			if(cart1 == null) {
				throw new ResourceNotFoundException("OutOfStock... Try Again Later");
			}
			return cart1;
	}

	@RequestMapping(value = "/cart/delete/{id}", method = RequestMethod.DELETE)
	public Cart deleteCart(@PathVariable("id") long cartId) throws ResourceNotFoundException{
			Cart Cart = cartService.deleteCart(cartId);
			if (Cart == null) {
				throw new ResourceNotFoundException("Not Cart Found");
			}
			return Cart;
	}

	@RequestMapping(value = "/cart/update/{id}", method = RequestMethod.PUT)
	public Cart updateCart(@PathVariable("id") long cartId, @RequestBody Cart cart) throws ResourceNotFoundException{
			Cart Cart1 = cartService.updateCart(cartId, cart);
			if (Cart1 == null) {
				throw new ResourceNotFoundException("Not Cart Found");
			}
			return Cart1;
	}

	@RequestMapping(value = "/cart/id/{id}", method = RequestMethod.GET)
	public Cart getCartDetails(@PathVariable("id") long cartId) throws ResourceNotFoundException{
			Cart Cart = cartService.getCartDetails(cartId);
			if (Cart == null) {
				throw new ResourceNotFoundException("Not Cart Found");
			}
			return Cart;
	}

	@RequestMapping(value = "/cart/all", method = RequestMethod.GET)
	public List<Cart> getAllCartDetails() {
		return cartService.getAllCartDetails(); 
	}
}
