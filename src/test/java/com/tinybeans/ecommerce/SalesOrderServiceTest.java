package com.tinybeans.ecommerce;

import com.tinybeans.ecommerce.domain.data.SalesOrderDTO;
import com.tinybeans.ecommerce.exception.PlatformServiceException;
import com.tinybeans.ecommerce.exception.ResourceNotFoundException;
import com.tinybeans.ecommerce.service.SalesOrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/** User: ayoade_farooq@yahoo.com Date: 09/06/2021 Time: 10:22 */
@SpringBootTest
public class SalesOrderServiceTest {

  private SalesOrderService salesOrderService;

  @Autowired
  public void setSalesOrderService(SalesOrderService salesOrderService) {
    this.salesOrderService = salesOrderService;
  }



  @Test
  void createSalesOrderShouldFail()  {
    Assertions.assertThrows(
            PlatformServiceException.class,
            () -> {
              salesOrderService.createSalesOrder(
                      "0000uip0",
                      BigDecimal.valueOf(120),
                      1,
                      "1100011",
                      "maddison square gardeb",
                      "New York",
                      "USA",
                      "New  York ");
            });
  }
}
