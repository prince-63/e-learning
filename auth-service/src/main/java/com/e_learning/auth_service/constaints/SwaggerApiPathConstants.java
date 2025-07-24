package com.e_learning.auth_service.constaints;

public final class SwaggerApiPathConstants {

    public static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/webjars/**",
            "/actuator/health",
            "/actuator/info"
    };

}
