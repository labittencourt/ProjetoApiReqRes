package core;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;

import java.util.regex.Matcher;

public class BaseTest implements Constants {
    @BeforeClass

    public static void setup(){
        RestAssured.baseURI = APP_BASE_URL;
        RestAssured.port = APP_PORT;

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setContentType(APP_CONTENT_TYPE);
        RestAssured.requestSpecification = reqBuilder.build();

        ResponseSpecBuilder resBuilder2 = new ResponseSpecBuilder();
        resBuilder2.expectResponseTime(Matchers.lessThan(MAX_TIME_OUT));
        RestAssured.responseSpecification = resBuilder2.build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
