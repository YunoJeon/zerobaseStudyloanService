package com.zerobase.loanService.product.service;

import com.zerobase.loanService.common.exception.ProductNotFoundException;
import com.zerobase.loanService.product.entity.ProductInfo;
import com.zerobase.loanService.product.entity.ProductList;
import com.zerobase.loanService.product.repository.ProductListRepository;
import com.zerobase.loanService.product.repository.ProductRepository;
import com.zerobase.loanService.type.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductListRepository productListRepository;

    // 상품 조회시 캐시 등록
    @Cacheable(value = "productCache", key = "#id")
    public ProductInfo getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // 상품 업데이트시 캐시 삭제
    @CacheEvict(value = "productCache", allEntries = true) // 캐시를 비워 최신 데이터를 사용하도록 함
    public ProductInfo saveProductInfo(ProductInfo productInfo) {
        if (productInfo.getProdCode() == null) {
            throw new IllegalArgumentException("Product code can`t be null");
        }
        return productRepository.save(productInfo);
    }

    @CacheEvict(value = "productCache", key = "#productList.orgCode")
    public ProductList saveProductList(ProductList productList) {
        return productListRepository.save(productList);
    }

    @Cacheable(value = "productCache", key = "#orgCode")
    public List<ProductInfo> getProductByOrgCode(Organization orgCode) {
        // 기관별로 상품 코드 목록 조회
        List<ProductList> productLists = productListRepository.findByOrgCode(orgCode);

        if (productLists.isEmpty()) {
            throw new ProductNotFoundException("No product found for organization: " + orgCode);
        }

        // 상품 코드로 상품 정보 조회
        return productLists.stream()
                .map(p1 -> productRepository.findByProdCode(p1.getProdCode()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
