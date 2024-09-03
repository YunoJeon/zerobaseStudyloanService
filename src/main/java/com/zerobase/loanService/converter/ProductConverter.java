package com.zerobase.loanService.converter;

import com.zerobase.loanService.type.Product;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProductConverter implements AttributeConverter<Product, String> {

    @Override
    public Product convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Product.valueOf(dbData);
    }

    @Override
    public String convertToDatabaseColumn(Product attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name();
    }
}
