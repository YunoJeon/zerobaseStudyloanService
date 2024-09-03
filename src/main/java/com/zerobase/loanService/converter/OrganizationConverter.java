package com.zerobase.loanService.converter;

import com.zerobase.loanService.type.Organization;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrganizationConverter implements AttributeConverter<Organization, String> {

    @Override
    public Organization convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Organization.valueOf(dbData);
    }

    @Override
    public String convertToDatabaseColumn(Organization attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name();

    }
}
