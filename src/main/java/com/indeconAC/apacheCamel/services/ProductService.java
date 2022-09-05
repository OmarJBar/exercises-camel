package com.indeconAC.apacheCamel.services;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indeconAC.apacheCamel.models.Products;

/**
 * ProductService
 */
@Service
public class ProductService {

    @Autowired
    ProducerTemplate producerTemplate;

    
    public Products getAll(){
        return producerTemplate.requestBody("direct:product-list", null, Products.class);
    }
}