package tr.com.foodstore.userservice.authconfig;

public class SecurityConstants {
    public static final String SECRET = "FoodStrore";
    public static final long EXPIRATION_TIME = 1_800_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String UNAUTHORIZED_RESPONSE = "Unauthorized";
}