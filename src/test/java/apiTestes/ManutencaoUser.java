package apiTestes;

import core.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Feature("Manutenção com o usuário")
public class ManutencaoUser extends BaseTest {
    public String UserId;
    public String Job;

    @Test
    @DisplayName("Criando um usuário")
    public void a_createUser() {
        File file = new File("/Users/leandro/Documents/TestApiS/src/test/java/jsonFile/criarUser.json");

        Response response =
                given()
                        .body(file)
                        .when()
                        .post(APP_USERS)
                        .then()
                        .statusCode(201)
                        .assertThat()
                        .body("name", containsString("morpheus"))
                        .extract().response();
        UserId = response.path("id");
        System.out.println("o id é: " + UserId);
    }

    @Test
    @DisplayName("Realizar update de um usuário com put")
    public void b_updateUser() {
        File file = new File("/Users/leandro/Documents/TestApiS/src/test/java/jsonFile/updateUser.json");

        Response response =
                given()
                        .body(file)
                        .when()
                        .put(APP_USERS + "/" + UserId)
                        .then()
                        .statusCode(200)
                        .assertThat()
                        .body("name", containsString("morpheus"))
                        .extract().response();
        Job = response.path("job");
        System.out.println("new job status é: " + Job);
    }

    @Test
    @DisplayName("Realizar update de um usuário com patch")
    public void c_updateUser() {
        File file = new File("/Users/leandro/Documents/TestApiS/src/test/java/jsonFile/updateUser.json");

        Response response =
                given()
                        .body(file)
                        .when()
                        .patch(APP_USERS + "/" + UserId)
                        .then()
                        .statusCode(200)
                        .assertThat()
                        .body("name", containsString("morpheus"))
                        .extract().response();
        Job = response.path("job");
        System.out.println("new job status é: " + Job);
    }

    @Test
    @DisplayName("Deltar um usuário")
    public void deleteUser() {
        given()
                .when()
                .delete(APP_USERS + "/" + UserId)
                .then()
                .statusCode(204);
    }

    @Test
    @DisplayName("Realizar um get para validar a exclusão do usuário")
    public void testeAcessarUserNotFound() {
        given()
                .when()
                .get(APP_USERS + "/" + UserId)
                .then()
                .statusCode(404);
    }
}
