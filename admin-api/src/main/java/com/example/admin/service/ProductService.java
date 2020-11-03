package com.example.admin.service;


import com.example.common.domain.Product;
import com.example.common.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    public final ProductRepository productRepository;


    public List<Product> getProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return  products;
    }

}
