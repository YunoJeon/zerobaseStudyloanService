package com.zerobase.loanService.product.repository;

import com.zerobase.loanService.product.entity.ProductInfo;
import com.zerobase.loanService.type.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductInfo, Long> {
    ProductInfo findByProdCode(Product prodCode);
}
