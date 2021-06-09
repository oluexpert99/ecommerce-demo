package com.tinybeans.ecommerce.domain.model;

import com.tinybeans.ecommerce.domain.data.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/** User: ayoade_farooq@yahoo.com Date: 08/06/2021 Time: 01:05 */
@Entity
@Table(name = "sales_order")
@Getter
@Setter
public class SalesOrder implements Serializable {

  private static final long serialVersionUID = -6571020025726257848L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "order_status")
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @Column(name = "order_date")
  private LocalDateTime orderDate = LocalDateTime.now();

  @Column(name = "customer_id")
  private String customerId; // email

  @Column(name = "transaction_ref")
  private String transactionRef; //

  @Column(name = "amount", scale = 6, precision = 19, nullable = false)
  private BigDecimal amount;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "address_id", nullable = false)
  private BillingAddress billingAddress;

  @NotFound(action = NotFoundAction.IGNORE)
  @ManyToOne()
  @JoinColumn(name = "product_id")
  private Product product;
}
