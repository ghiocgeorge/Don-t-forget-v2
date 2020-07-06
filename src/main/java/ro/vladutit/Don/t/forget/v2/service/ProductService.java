package ro.vladutit.Don.t.forget.v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.vladutit.Don.t.forget.v2.model.Product;
import ro.vladutit.Don.t.forget.v2.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public Optional<Product> getProduct(String codeBarId) {
        return productRepository.findById(codeBarId);
    }

    public void addProduct (Product product) {
        productRepository.save(product);
    }
}
