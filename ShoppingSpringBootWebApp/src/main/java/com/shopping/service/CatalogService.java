package com.shopping.service;

import com.shopping.bean.Product;
import com.shopping.bean.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class CatalogService {
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Product> getAllProducts() {
        return Objects.requireNonNull(Objects.requireNonNull(restTemplate.getForObject("http://localhost:8084/products/", ProductList.class)).getProducts());
    }

    public Product getProductByCode(String code) {
        return restTemplate.getForObject("http://localhost:8084/products/code/" + code, Product.class);
    }

    public Product getProductById(int  id) {
        return restTemplate.getForObject("http://localhost:8084/products/" + id, Product.class);
    }
}
