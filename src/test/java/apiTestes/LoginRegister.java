package apiTestes;

import core.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Feature("Testes no fluxo de login")
public class LoginRegister extends BaseTest {

    public String Text = "A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things.";
    public String Token = "QpwL5tke4Pnpja7X4";
    public String ErroPass = "Missing password";
    public String Company = "StatusCode Weekly";


    @Test
    @DisplayName("Registro usuário com sucesso")
    public void registerUserSuccess() {
        File file = new File("/Users/leandro/Documents/TestApiS/src/test/java/jsonFile/registerUser.json");

        given()
                .body(file)
                .when()
                .post(APP_REGISTER)
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", is(4))
                .and()
                .body("token", containsString(Token));
    }

    @Test
    @DisplayName("Registro usuário com falha")
    public void registerUserFail() {
        File file = new File("/Users/leandro/Documents/TestApiS/src/test/java/jsonFile/registerUserFail.json");

        given()
                .body(file)
                .when()
                .post(APP_REGISTER)
                .then()
                .statusCode(400)
                .assertThat()
                .body("error", containsString(ErroPass));
    }

    @Test
    @DisplayName("Tentar realizar login com usuário falho")
    public void loginUserFail() {
        File file = new File("/Users/leandro/Documents/TestApiS/src/test/java/jsonFile/loginFail.json");

        given()
                .body(file)
                .when()
                .post(APP_LOGIN)
                .then()
                .statusCode(400)
                .assertThat()
                .body("error", containsString(ErroPass));
    }

    @Test
    @DisplayName("Tentar realizar login com usuário ok")
    public void loginUserSuccess() {
        File file = new File("/Users/leandro/Documents/TestApiS/src/test/java/jsonFile/loginSuccess.json");

        given()
                .body(file)
                .when()
                .post(APP_LOGIN)
                .then()
                .statusCode(200)
                .assertThat()
                .body("token", containsString(Token));
    }

    @Test
    @DisplayName("tempo de resposta")
    public void delayedResponse() {
        given()
                .when()
                .get(APP_USERS + "?delay=3")
                .then()
                .statusCode(200)
                .body("ad.company", containsString(Company))
                .and()
                .body("ad.text", containsString(Text));
    }
}
