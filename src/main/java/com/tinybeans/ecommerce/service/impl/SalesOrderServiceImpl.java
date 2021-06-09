package com.tinybeans.ecommerce.service.impl;

import com.stripe.Stripe;
import com.stripe.model.Address;
import com.stripe.model.Charge;
import com.stripe.model.PaymentMethod;
import com.tinybeans.ecommerce.domain.data.OrderStatus;
import com.tinybeans.ecommerce.domain.data.SalesOrderDTO;
import com.tinybeans.ecommerce.domain.model.BillingAddress;
import com.tinybeans.ecommerce.domain.model.SalesOrder;
import com.tinybeans.ecommerce.domain.repository.BillingAddressRepository;
import com.tinybeans.ecommerce.domain.repository.SalesOrderRepository;
import com.tinybeans.ecommerce.exception.PlatformServiceException;
import com.tinybeans.ecommerce.service.ProductService;
import com.tinybeans.ecommerce.service.SalesOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/** User: ayoade_farooq@yahoo.com Date: 08/06/2021 Time: 14:26 */
@Service
@Slf4j
public class SalesOrderServiceImpl implements SalesOrderService {

  private BillingAddressRepository billingAddressRepository;
  private SalesOrderRepository salesOrderRepository;

  private ProductService productService;

  @Autowired
  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

  @Autowired
  public void setBillingAddressRepository(BillingAddressRepository billingAddressRepository) {
    this.billingAddressRepository = billingAddressRepository;
  }

  @Autowired
  public void setSalesOrderRepository(SalesOrderRepository salesOrderRepository) {
    this.salesOrderRepository = salesOrderRepository;
  }

  @Value("${stripe.secret.key}")
  private String API_SECRET_KEY;

  private Charge chargeNewCard(String token, double amount) throws Exception {
    Stripe.apiKey = API_SECRET_KEY;
    Map<String, Object> chargeParams = new HashMap<>();
    chargeParams.put("amount", (int) (amount * 100));
    chargeParams.put("currency", "USD");
    chargeParams.put("source", token);
    return Charge.create(chargeParams);
  }

  @Override
  public SalesOrderDTO createSalesOrder(
      String token,
      BigDecimal amount,
      long productId,
      String zipCode,
      String address,
      String state,
      String country,
      String city) {
    SalesOrder salesOrder = new SalesOrder();
    try {
      Charge charge = chargeNewCard(token, amount.doubleValue());
      salesOrder.setAmount(amount);
      salesOrder.setCustomerId(charge.getBillingDetails().getName());
      salesOrder.setOrderDate(LocalDateTime.now());
      salesOrder.setOrderStatus(OrderStatus.APPROVED);
      salesOrder.setTransactionRef(charge.getId());
      BillingAddress billingAddress = new BillingAddress();

      billingAddress.setCity(city);
      billingAddress.setCountry(country);
      billingAddress.setState(state);
      billingAddress.setZipCode(zipCode);
      billingAddress = billingAddressRepository.save(billingAddress);
      salesOrder.setBillingAddress(billingAddress);
      salesOrder.setProduct(productService.getProduct(productId));
      salesOrder = salesOrderRepository.save(salesOrder);

      SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
      salesOrderDTO.setId(salesOrder.getId());
      salesOrderDTO.setTransactionRef(salesOrder.getTransactionRef());
      return salesOrderDTO;
    } catch (Exception e) {
      throw new PlatformServiceException(
          "error.msg.service", String.format("exception occurred %s", e.getLocalizedMessage()), e);
    }
  }
}
