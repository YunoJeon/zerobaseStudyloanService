package com.zerobase.loanService.product.repository;

import com.zerobase.loanService.product.entity.ProductList;
import com.zerobase.loanService.type.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductListRepository extends JpaRepository<ProductList, Long> {
    List<ProductList> findByOrgCode(Organization orgCode);
}
