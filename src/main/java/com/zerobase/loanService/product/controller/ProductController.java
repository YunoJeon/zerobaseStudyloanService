package com.zerobase.loanService.product.controller;

import com.zerobase.loanService.product.entity.ProductInfo;
import com.zerobase.loanService.product.entity.ProductList;
import com.zerobase.loanService.product.service.ProductService;
import com.zerobase.loanService.type.Organization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product", description = "상품 정보 API")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // POST 요청시 기관, 상품정보 DB 등록
    @PostMapping("/product")
    @Operation(summary = "상품 정보 수신 API", description = "금융사로부터 상품 정보를 받는 API")
    public ProductInfo receiveProductInfo(@RequestBody ProductInfo productInfo) {
        return productService.saveProductInfo(productInfo);
    }

    // GET 요청시 최초 캐시 등록, DB값 조회
    @GetMapping("/product/{id}")
    @Operation(summary = "상품 정보 조회 API", description = "상품 정보를 조회하는 API")
    public ProductInfo getProductInfo(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    // POST 요청시 기관, 상품 DB 등록
    @PostMapping("/product-list")
    @Operation(summary = "금융사 - 상품 정보 수신 API", description = "금융사별 상품 정보를 받는 API")
    public ProductList saveProductList(@RequestBody ProductList productList) {
        return productService.saveProductList(productList);
    }

    @GetMapping("/products/org/{orgCode}")
    @Operation(summary = "금융사 - 상품 실제 정보 수신 API", description = "실제 상품 정보를 조회하는 API")
    public List<ProductInfo> getProductsByOrgCode(@PathVariable @Parameter(description = "기관 코드") Organization orgCode) {
        return productService.getProductByOrgCode(orgCode);
    }
}
