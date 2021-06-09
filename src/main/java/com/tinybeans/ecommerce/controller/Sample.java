package com.tinybeans.ecommerce.controller;

import com.tinybeans.ecommerce.domain.data.ProductDTO;
import com.tinybeans.ecommerce.domain.model.Product;
import com.tinybeans.ecommerce.domain.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/** User: ayoade_farooq@yahoo.com Date: 08/06/2021 Time: 02:59 */
@RestController
public class Sample {
  @Autowired private ProductRepository productRepository;

  @PostMapping("uploads")
  public ResponseEntity<?> uploadCustomerDocument(@RequestBody ProductDTO productDTO)
      throws IOException {
    Product product = new Product();

    BeanUtils.copyProperties(productDTO, product);

    productRepository.save(product);

    return ResponseEntity.ok("thanks baba ");
  }

  @PostMapping("/{id}/documents")
  public ResponseEntity<?> uploadCustomerDocument(
      @PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
    Product product = productRepository.findById(id).get();
    product.setProductImage(file.getBytes());
    productRepository.save(product);

    return ResponseEntity.ok("thanks again ");
  }
}
