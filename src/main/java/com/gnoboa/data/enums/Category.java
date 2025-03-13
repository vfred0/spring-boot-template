package com.gnoboa.data.enums;

import com.gnoboa.data.utils.EnumConverter;
import com.gnoboa.data.utils.EnumDatabase;
import lombok.Getter;

@Getter
public enum Category implements EnumDatabase {
    OATMEAL("AVENA"),
    WINES("VINOS"),
    CHOCOLATE("CHOCOLATE"),
    COFFEES("CAFES"),
    FLOUR_MIXES("MEZCLAS_HARINAS"),
    INSTANT_SOUPS("SOPAS_INSTANTANEAS"),
    SWEETENERS("ENDULZANTES"),
    SNACKS("SNACKS"),
    PROMOTIONAL("PROMOCIONAL");

    private final String value;
        
    Category(String value) {
        this.value = value;
    }
    
    public static class  CategoryConverter extends EnumConverter<Category> {
        public CategoryConverter() {
            super(Category.class);
        }
    }

}