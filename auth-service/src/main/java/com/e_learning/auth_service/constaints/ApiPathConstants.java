package com.e_learning.auth_service.constaints;

public final class ApiPathConstants {
    public static final String BASE = "/api/v1/auth-service";
    public static final String WELCOME = BASE + "/welcome";
    public static final String REGISTER = BASE + "/register";
    public static final String LOGIN = BASE + "/login";
    public static final String VALIDATE = BASE + "/validate";
    public static final String REFRESH = BASE + "/refresh";
    public static final String GET_AUTH_DETAILS = BASE + "/details/{userId}";
}
