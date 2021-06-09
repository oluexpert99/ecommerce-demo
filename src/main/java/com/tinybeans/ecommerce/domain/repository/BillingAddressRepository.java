package com.tinybeans.ecommerce.domain.repository;

import com.tinybeans.ecommerce.domain.model.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** User: ayoade_farooq@yahoo.com Date: 08/06/2021 Time: 14:22 */
@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long> {}
