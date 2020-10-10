package core;

import io.restassured.http.ContentType;

public interface Constants {

    Integer APP_PORT = 443;
    String APP_BASE_URL = "https://reqres.in/api";
    String APP_USERS = "/users";
    String APP_RESOURCES = "/unknown";
    String APP_REGISTER = "/register";
    String APP_LOGIN = "/login";

    ContentType APP_CONTENT_TYPE = ContentType.JSON;
    Long MAX_TIME_OUT = 5000L;
}
