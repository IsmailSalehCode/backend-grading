package com.ismail.gradesubmission.security;

public class SecurityConstants {
    public static final String SECRET_KEY = "f_QiDhWmZq4t7w!z$C&F)J@NcRfUjXn2r5u8x/6?D*G-KaPdSgVkYp3s6v9y$B&W";
    public static final int TOKEN_EXPIRATION = 14_400_000; // 14,400,000 milliseconds = 4 hours.
    public static final String BEARER = "Bearer "; // Authorization : "Bearer " + Token
    public static final String AUTHORIZATION = "Authorization"; // "Authorization" : Bearer Token
    public static final String REGISTER_PATH = "/user/register"; // Public path that clients can use to register.
}