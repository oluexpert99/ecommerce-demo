package com.tinybeans.ecommerce.domain.data;

import lombok.Data;

/** User: ayoade_farooq@yahoo.com Date: 08/06/2021 Time: 00:51 */
@Data
public class ProductDTO {

  private Long id;

  private String productCategory;

  private String productDescription;

  private String productManufacturer;

  private String productName;

  private double productPrice;

  private String unitStock;

  private String  productImage;
}
