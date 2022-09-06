package com.indeconAC.apacheCamel.services;

import java.util.HashMap;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indeconAC.apacheCamel.models.Products;
import com.indeconAC.apacheCamel.models.Values;

/**
 * ProductService
 */
@Service
public class ProductService {

    @Autowired
    ProducerTemplate producerTemplate;

    
    public Products getAll(){
        return producerTemplate.requestBody("direct:call-list-products", null, Products.class);
    }


    public Values findById(String key) {
        
        var headers = new HashMap<String, Object>();
        headers.put("key", key);
        return producerTemplate.requestBodyAndHeaders("direct:call-find-products", null, headers, Values.class);
    }
}