package com.product_service.product_service.Controller;

import com.product_service.product_service.DTO.ProductRequest;
import com.product_service.product_service.DTO.ProductResponse;
import com.product_service.product_service.DTO.UpdateProductDetailDto;
import com.product_service.product_service.Repository.ProductRepository;
import com.product_service.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping()
    public String saveProduct(@RequestBody ProductRequest productRequest){
      return productService.saveProduct(productRequest);
    }

    @GetMapping()
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public ProductResponse updateProductDetails(@RequestBody UpdateProductDetailDto updateProductDetailDto, @PathVariable("id")     Long id) {
        return productService.updateProductDetails(updateProductDetailDto,id);
    }
}
