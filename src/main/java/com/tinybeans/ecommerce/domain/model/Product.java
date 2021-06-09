package com.tinybeans.ecommerce.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/** User: ayoade_farooq@yahoo.com Date: 08/06/2021 Time: 00:13 */
@Entity
@Table(name = "products")
@Getter
@Setter
public class Product implements Serializable {
  private static final long serialVersionUID = 5186013952828648626L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "category")
  private String productCategory;

  @Lob
  @Column(name = "description")
  private String productDescription;

  @Column(name = "manufacturer")
  private String productManufacturer;

  @NotEmpty(message = "Product Name is mandatory")
  @Column(name = "name")
  private String productName;

  @NotNull(message = "Please provide some price")
  @Column(name = "price")
  private double productPrice;

  @Lob
  @Column(name = "image")
  private byte[] productImage;
}
