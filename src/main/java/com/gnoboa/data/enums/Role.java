package com.gnoboa.data.enums;

import com.gnoboa.data.utils.EnumConverter;
import com.gnoboa.data.utils.EnumDatabase;
import lombok.Getter;

@Getter
public enum Role implements EnumDatabase {
    BOSS("JEFE"),
    SELLER("VENDEDOR"),
    TECHNICIAN("TECNICO"),
    DEVELOPER("DESARROLLADOR");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public static class RoleConverter extends EnumConverter<Role> {
        public RoleConverter() {
            super(Role.class);
        }
    }
}
