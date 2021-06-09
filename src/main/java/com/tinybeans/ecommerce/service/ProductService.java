package com.tinybeans.ecommerce.service;

import com.tinybeans.ecommerce.domain.data.ProductDTO;
import com.tinybeans.ecommerce.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/** User: ayoade_farooq@yahoo.com Date: 08/06/2021 Time: 00:33 */
public interface ProductService {

  List<ProductDTO> getAllProducts();

  Page<ProductDTO> getProducts(Pageable pageable);

  ProductDTO getProductById(Long productId);

  Product getProduct(Long productId);

}
