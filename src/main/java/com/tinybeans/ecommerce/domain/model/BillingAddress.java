package com.tinybeans.ecommerce.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/** User: ayoade_farooq@yahoo.com Date: 08/06/2021 Time: 01:11 */
@Entity
@Table(name = "billing_Address")
@Getter
@Setter
public class BillingAddress {

  private static final long serialVersionUID = 1028098616457762743L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "city")
  private String city;
  @Column(name = "state")
  private String state;
  @Column(name = "zipCode")
  private String zipCode;
  @Column(name = "country")
  private String country;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "billingAddress")
    private SalesOrder salesOrder;
}
