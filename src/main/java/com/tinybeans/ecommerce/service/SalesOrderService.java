package com.tinybeans.ecommerce.service;

import com.tinybeans.ecommerce.domain.data.SalesOrderDTO;

/**
 * User: ayoade_farooq@yahoo.com
 * Date: 08/06/2021
 * Time: 14:23
 */
public interface  SalesOrderService {

    SalesOrderDTO createSalesOrder(String token, double amount, long productId, String zipCode, String address, String state , String country, String city );
}
