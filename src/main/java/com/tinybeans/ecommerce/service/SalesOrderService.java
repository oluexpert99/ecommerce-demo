package com.tinybeans.ecommerce.service;

import com.tinybeans.ecommerce.domain.data.SalesOrderDTO;

import java.math.BigDecimal;

/**
 * User: ayoade_farooq@yahoo.com
 * Date: 08/06/2021
 * Time: 14:23
 */
public interface  SalesOrderService {
    SalesOrderDTO createSalesOrder(String token, BigDecimal amount, long productId, String zipCode, String address, String state , String country, String city );
}
