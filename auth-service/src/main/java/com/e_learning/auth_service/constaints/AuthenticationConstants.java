package com.e_learning.auth_service.constaints;

public final class AuthenticationConstants {
    public static final String JWT_SECRET_KEY = "JWT_SECRET";
    public static final String JWT_SECRET_DEFAULT_VALUE = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    public static final String JWT_HEADER = "Authorization";
    public static final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 60 * 2;
}