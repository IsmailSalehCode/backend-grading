package com.ismail.grading.security;

public class SecurityConstants {
    public static final String SECRET_KEY = "f_QiDhWmZq4t7w!z$C&F)J@NcRfUjXn2r5u8x/6?D*G-KaPdSgVkYp3s6v9y$B&W";
    public static final int TOKEN_EXPIRATION = 14_400_000; // 14,400,000 milliseconds = 4 hours.
    public static final String BEARER = "Bearer "; // Authorization : "Bearer " + Token
    /**
     * The inclusion of "Bearer" in the Authorization header helps servers
     * understand the type of authentication being used. It distinguishes bearer
     * tokens from other types of tokens or authentication mechanisms that might be
     * used in the future. This standardization allows for a
     * consistent way of handling authentication tokens in web applications and
     * APIs.
     */
    public static final String AUTHORIZATION = "Authorization"; // "Authorization" : Bearer Token
    public static final String REGISTER_PATH = "/user/register"; // Public path that clients can use to register.
}