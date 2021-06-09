package com.tinybeans.ecommerce;

import com.tinybeans.ecommerce.domain.data.ProductDTO;
import com.tinybeans.ecommerce.exception.ResourceNotFoundException;
import com.tinybeans.ecommerce.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.net.URISyntaxException;
import java.util.List;

/** User: ayoade_farooq@yahoo.com Date: 09/06/2021 Time: 09:54 */
@SpringBootTest
@Sql({"/products.sql"})
public class ProductServiceTest {

  private ProductService productService;

  @Autowired
  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

  @Test
  void testGetAllProductsWithSuccess() {

    List<ProductDTO> productDTOList = productService.getAllProducts();

    Assertions.assertNotNull(productDTOList);
  }

  @Test()
  void getProductWithWrongIdShouldFail() {

    Assertions.assertThrows(
        ResourceNotFoundException.class,
        () -> {
          productService.getProduct(9L);
        });
  }

  @Test
  void testGetProductWithSuccess() {
    ProductDTO productDTO = productService.getProductById(1L);
    Assertions.assertNotNull(productDTO);
  }
}
