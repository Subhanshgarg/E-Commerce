package com.product_service.product_service.service;

import com.product_service.product_service.DTO.ProductRequest;
import com.product_service.product_service.DTO.ProductResponse;
import com.product_service.product_service.DTO.UpdateProductDetailDto;
import com.product_service.product_service.Repository.ProductRepository;
import com.product_service.product_service.model.Product;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public String saveProduct(ProductRequest productRequest) {
        try {
            Product product = Product.builder().productId(productRequest.getProductId())
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .build();
            productRepository.save(product);
        } catch (Exception e){
            System.out.println(e);
        }
    return "Product Saved Successfully";
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products=productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .description(product.getDescription())
                .price(product.getPrice())
                .name(product.getName())
                .build();
    }

    public ProductResponse updateProductDetails(UpdateProductDetailDto updateProductDetailDto, Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            Product product=productOptional.get();
            updateProductDetailDto.getDescription().ifPresent(product::setDescription);
            updateProductDetailDto.getName().ifPresent(product::setName);
            updateProductDetailDto.getPrice().ifPresent(product::setPrice);
            productRepository.save(product);
            return  mapToProductResponse(product);
        }
        else{
            throw new RuntimeException("Product Not Found");
        }

    }
}
