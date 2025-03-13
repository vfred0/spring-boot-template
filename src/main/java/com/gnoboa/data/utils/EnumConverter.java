package com.gnoboa.data.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EnumConverter<T extends Enum<T> & EnumDatabase>
        implements AttributeConverter<T, String> {

    private final Class<T> enumClass;

    public EnumConverter(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        for (T enumValue : enumClass.getEnumConstants()) {
            if (enumValue.getValue().equals(dbData)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException(
                "Valor no válido para " + enumClass.getSimpleName() + ": " + dbData
        );
    }
}