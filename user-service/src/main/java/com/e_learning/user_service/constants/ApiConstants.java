package com.e_learning.user_service.constants;

public final class ApiConstants {
    private static final String BASE = "/api/v1/user-service";
    public static final String GET_USER_DETAILS = BASE + "/details/{detailsId}";
    public static final String UPDATE_BIO = BASE + "/bio/{userId}";
    public static final String UPDATE_AVATAR = BASE + "/avatar/{userId}";
    public static final String UPDATE_PROFESSION = BASE + "/profession/{userId}";
    public static final String UPDATE_PHONE = BASE + "/phone/{userId}";
}

