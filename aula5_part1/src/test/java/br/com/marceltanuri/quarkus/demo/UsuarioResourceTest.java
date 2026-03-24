package br.com.marceltanuri.quarkus.demo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioResourceTest {

    @Test
    @Order(1)
    public void testListarUsuariosVazio() {
        given()
          .when().get("/usuarios")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }

    @Test
    @Order(2)
    public void testCriarUsuario() {
        given()
            .contentType("application/json")
            .body("{\"nome\":\"Test User\",\"email\":\"test@example.com\"}")
        .when()
            .post("/usuarios")
        .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("nome", is("Test User"))
            .body("email", is("test@example.com"));
    }

    @Test
    @Order(3)
    public void testCriarUsuarioComEmailConflitante() {
        given()
            .contentType("application/json")
            .body("{\"nome\":\"Another User\",\"email\":\"test@example.com\"}")
        .when()
            .post("/usuarios")
        .then()
            .statusCode(409);
    }

    @Test
    @Order(4)
    public void testListarUsuarios() {
        given()
          .when().get("/usuarios")
          .then()
             .statusCode(200)
             .body("size()", is(1))
             .body("[0].nome", equalTo("Test User"));
    }


}
