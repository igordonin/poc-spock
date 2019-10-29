package com.pareto.spock.core.seguranca;

public enum Role {
    ROLE_ADMIN(Roles.ROLE_ADMIN),
    ROLE_MANAGER(Roles.ROLE_MANAGER),
    ROLE_ANALYST(Roles.ROLE_ANALYST);

    private String value;

    Role(String value) {
        this.value = value;
    }

    public static class Roles {
        public final static String ROLE_ADMIN = "ROLE_ADMIN";
        public final static String ROLE_MANAGER = "ROLE_MANAGER";
        public final static String ROLE_ANALYST = "ROLE_ANALYST";
    }
}