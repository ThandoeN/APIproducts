package com.example.demo;


import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class ProductServiceController {
    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {

            List<Product> Products = new ArrayList<Product>();
            if (name == null)
                productRepository.findAll().forEach(Products::add);
            else
                productRepository.findByName(name).forEach(Products::add);
            if (Products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(Products, HttpStatus.OK);
        }
}

