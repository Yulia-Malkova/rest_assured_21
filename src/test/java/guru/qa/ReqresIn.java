package guru.qa;

import guru.qa.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static guru.qa.specs.UserSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresIn {

    @Test
    @DisplayName("GET метод возвращает корректную информацию об одном пользователе")
    void getSingleUserInfoTest() {
        UserResponseModel response = step("Отправить GET запрос для отдельного пользователя", () ->
        given(RequestSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(userResponseSpec)
                .extract().as(UserResponseModel.class));

        step("Проверить ответ", () -> {
                assertEquals(2, response.getData().getId());
                assertEquals("janet.weaver@reqres.in", response.getData().getEmail());
                assertEquals("Janet", response.getData().getFirstName());
                assertEquals("Weaver", response.getData().getLastName());
                assertEquals("https://reqres.in/img/faces/2-image.jpg", response.getData().getAvatar());
        });
    }

    @Test
    @DisplayName("GET метод возвращает информацию о всех пользователях")
    void getCorrectListOfUsersTest() {
        UserListResponseModel response = step("Отправить GET запрос для всех пользователей", () ->
        given(RequestSpec)
                .when()
                .get("/users?page=2")
                .then()
                .spec(userResponseSpec)
                .extract().as(UserListResponseModel.class));

        step("Проверить ответ", () -> {
            assertEquals(2, response.getPage());
            assertEquals(6, response.getPerPage());
            assertEquals(12, response.getTotal());
            assertEquals(2, response.getTotalPages());
            assertEquals(2, response.getTotalPages());
            assertEquals(12,response.getData().get(5).getId());
            assertEquals("rachel.howell@reqres.in", response.getData().get(5).getEmail());
            assertEquals("Rachel", response.getData().get(5).getFirstName());
            assertEquals("Howell", response.getData().get(5).getLastName());
            assertEquals("https://reqres.in/img/faces/12-image.jpg", response.getData().get(5).getAvatar());
        });
    }

    @Test
    @DisplayName("POST метод позволяет создать нового пользователя")
    void createNewUserTest() {
        UserCreationBodyModel userInfo = new UserCreationBodyModel();
        userInfo.setName("Harry");
        userInfo.setJob("farmer");

        UserCreationResponseModel response = step("Отправить POST запрос для создания нового пользователя", () ->

            given(RequestSpec)
                .body(userInfo)
                .when()
                .post("/users")
                .then()
                .spec(userCreationResponseSpec)
                .extract().as(UserCreationResponseModel.class));

        step("Проверить ответ", () -> {
            assertEquals("Harry", response.getName());
            assertEquals("farmer", response.getJob());
        });
    }

    @Test
    @DisplayName("PUT метод позволяет обновить информацию о пользователе")
    void updateUserTest() {
        UserCreationBodyModel changedUserInfo = new UserCreationBodyModel();
        changedUserInfo.setName("Bill");
        changedUserInfo.setJob("professor");

        UserUpdateResponseModel response = step("Отправить PUT запрос для обновления пользователя", () ->
        given(RequestSpec)
                .body(changedUserInfo)
                .when()
                .put("/users/2")
                .then()
                .spec(userUpdateResponseSpec)
                .extract().as(UserUpdateResponseModel.class));

        step("Проверить ответ", () -> {
            assertEquals("Bill", response.getName());
            assertEquals("professor", response.getJob());
        });
    }

    @Test
    @DisplayName("DELETE метод позволяет удалить информацию о пользователе")
    void deleteUserTest() {
        step("Проверить статус ответа при удалении пользователя", () ->
        given(RequestSpec)
                .when()
                .delete("/users/2")
                .then()
                .spec(userDeletionResponseSpec));
    }
}
