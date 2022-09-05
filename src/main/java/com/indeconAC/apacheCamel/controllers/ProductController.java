package com.indeconAC.apacheCamel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.indeconAC.apacheCamel.models.Products;
import com.indeconAC.apacheCamel.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/listar")
	public Products list() {		
		return productService.getAll();
	}

    /*
	@GetMapping("/buscar/{key}")
	public Values listOfValues(@PathVariable String key) {		
		return productService.findById(key); 
	}

	@GetMapping("/buscar/{key}/{date}")
	public Product listOfDates(@PathVariable("key") String key, @PathVariable("date") String date) {
		
		return productService.findByDate(key,date); 
	} */
}
