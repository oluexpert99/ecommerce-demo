package com.tinybeans.ecommerce.domain.repository;

import com.tinybeans.ecommerce.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: ayoade_farooq@yahoo.com
 * Date: 08/06/2021
 * Time: 00:29
 */
@Repository
public interface ProductRepository  extends JpaRepository<Product,Long> {
}
