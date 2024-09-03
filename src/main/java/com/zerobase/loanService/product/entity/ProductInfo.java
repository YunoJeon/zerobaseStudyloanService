package com.zerobase.loanService.product.entity;

import com.zerobase.loanService.converter.ProductConverter;
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
@Table(name = "product_info")
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상품 아이디

    @Column(unique = true)
    @Convert(converter = ProductConverter.class)
    private Product prodCode; // 상품 코드

    private String prodName; // 상품 이름
    private double prodMinIntr; // 상품 최소 이자
    private double prodMaxIntr; // 상품 최대 이자
}
