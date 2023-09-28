package guru.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresIn extends TestBase {

    @Test
    @DisplayName("GET метод возвращает корректную информацию об одном пользователе")
    void getSingleUserInfoTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"))
                .body("data.avatar", is("https://reqres.in/img/faces/2-image.jpg"));
    }

    @Test
    @DisplayName("GET метод возвращает корректное количество пользователей в списке")
    void getCorrectListOfUsersTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", is(2))
                .body("per_page", is(6))
                .body("total", is(12))
                .body("total_pages", is(2));
    }

    @Test
    @DisplayName("POST метод позволяет создать нового пользователя")
    void createNewUserTest() {
        String userInfo = "{\n" + "\"name\": \"Harry\",\n" + "\"job\": \"magician\"\n" +"}";

        given()
                .log().uri()
                .log().method()
                .contentType(JSON)
                .body(userInfo)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Harry"))
                .body("job", is("magician"));
    }

    @Test
    @DisplayName("PUT метод позволяет обновить информацию о пользователе")
    void updateUserTest() {
        String initialUserInfo = "{\n" + "\"name\": \"Dudley\",\n" + "\"job\": \"muggle\"\n" +"}";
        String changedUserInfo = "{\n" + "\"name\": \"Remus\",\n" + "\"job\": \"professor\"\n" +"}";
        String id =
                given()
                .log().uri()
                .log().method()
                .contentType(JSON)
                .body(initialUserInfo)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Dudley"))
                .body("job", is("muggle"))
                .extract().path("id");

        given()
                .log().uri()
                .log().method()
                .contentType(JSON)
                .body(changedUserInfo)
                .when()
                .put("/users/"+id)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("Remus"))
                .body("job", is("professor"));
    }

    @Test
    @DisplayName("DELETE метод позволяет удалить информацию о пользователе")
    void deleteUserTest() {
        String initialUserInfo = "{\n" + "\"name\": \"Ron\",\n" + "\"job\": \"student\"\n" +"}";
        String id =
                given()
                        .log().uri()
                        .log().method()
                        .contentType(JSON)
                        .body(initialUserInfo)
                        .when()
                        .post("/users")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(201)
                        .body("name", is("Ron"))
                        .body("job", is("student"))
                        .extract().path("id");

        given()
                .log().uri()
                .log().method()
                .contentType(JSON)
                .when()
                .delete("/users/"+id)
                .then()
                .log().status()
                .statusCode(204);
    }
}
