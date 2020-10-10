package apiTestes;

import core.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

@Feature("Realizar testes lista de usuarios exibindo cenários positivos e negativos")
public class AcessarUsers extends BaseTest {
    @Test
    @DisplayName("Acessar a lista da api de usuários")
    public void testeAcessarListaUsers() {
        given()
                .when()
                .get(APP_USERS + "?page=2")
                .then()
                .statusCode(200)
                .body("data.id", hasItem(12))
                .and()
                .body("data.email", hasItem("rachel.howell@reqres.in"));
    }

    @Test
    @DisplayName("Acessar usuário com o id = 2")
    public void testeAcessarUserId2() {
        given()
                .when()
                .get(APP_USERS + "/2")
                .then()
                .statusCode(200)
                .assertThat()
                .body("data.id", is(2))
                .and()
                .body("data.first_name", equalTo("Janet"));
    }

    @Test
    @DisplayName("Tentar acesso ao usuário não encontrado")
    public void testeAcessarUserNotFound() {
        given()
                .when()
                .get(APP_USERS + "/24")
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("Lista de usuário com resq Unknown")
    public void testeAcessarResourceListaUsers() {
        given()
                .when()
                .get(APP_RESOURCES)
                .then()
                .statusCode(200)
                .body("data.id", hasItem(2))
                .and()
                .body("data.year", hasItem(2001));
    }

    @Test
    @DisplayName("Acessar usuário com resq Unknown com id = 2")
    public void testeAcessarResourceUserId2() {
        given()
                .when()
                .get(APP_RESOURCES + "/2")
                .then()
                .statusCode(200)
                .assertThat()
                .body("data.id", is(2))
                .and()
                .body("data.name", containsString("fuchsia rose"));
    }

    @Test
    @DisplayName("Tentar acesso ao usuário não encontrado resq Unknown")
    public void testeAcessarResourceUserNotFound() {
        given()
                .when()
                .get(APP_RESOURCES + "/23")
                .then()
                .statusCode(404);
    }
}