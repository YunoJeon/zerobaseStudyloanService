package com.zerobase.loanService.product.entity;

import com.zerobase.loanService.converter.OrganizationConverter;
import com.zerobase.loanService.converter.ProductConverter;
import com.zerobase.loanService.type.Organization;
import com.zerobase.loanService.type.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product_list")
public class ProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기관 아이디

    @Convert(converter = OrganizationConverter.class)
    private Organization orgCode; // 기관 코드

    @Convert(converter = ProductConverter.class)
    private Product prodCode; // 제품 코드

    @Column(unique = true, name = "org_code_prod_code")
    private String orgCodeAndProdCode; // 기관과 상품 코드 조합

    @PrePersist
    @PreUpdate
    private void generateOrgCodeAndProdCode() {
        this.orgCodeAndProdCode = orgCode.name() + "_" + prodCode.name();
    }
}
