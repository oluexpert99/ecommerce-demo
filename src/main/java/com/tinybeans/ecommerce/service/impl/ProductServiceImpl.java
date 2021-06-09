package com.tinybeans.ecommerce.service.impl;

import com.tinybeans.ecommerce.domain.data.ProductDTO;
import com.tinybeans.ecommerce.domain.model.Product;
import com.tinybeans.ecommerce.domain.repository.ProductRepository;
import com.tinybeans.ecommerce.exception.ResourceNotFoundException;
import com.tinybeans.ecommerce.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/** User: ayoade_farooq@yahoo.com Date: 08/06/2021 Time: 03:50 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
  private ProductRepository productRepository;

  @Autowired
  public void setProductRepository(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<ProductDTO> getAllProducts() {

    return convertEntityListToDTO(productRepository.findAll());
  }

  @Override
  public Page<ProductDTO> getProducts(Pageable pageable) {
    return null;
  }

  @Override
  public ProductDTO getProductById(Long productId) {

    return convertEntityToDTO(getProduct(productId));
  }

  @Override
  public Product getProduct(Long productId) {
    return productRepository
        .findById(productId)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    "error.msg.product.notfound",
                    String.format("product with id %s cannot be found ", productId)));
  }



  private List<ProductDTO> convertEntityListToDTO(List<Product> productList) {

    return productList.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
  }

  private ProductDTO convertEntityToDTO(Product product) {
    ProductDTO productDTO = new ProductDTO();
    BeanUtils.copyProperties(product, productDTO);
    productDTO.setProductImage(Base64.getMimeEncoder().encodeToString(product.getProductImage()));
    return productDTO;
  }
}
