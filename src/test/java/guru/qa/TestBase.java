package guru.qa;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    @BeforeAll
    public static void beforeAll(){
            RestAssured.baseURI = "https://reqres.in";
            RestAssured.basePath = "/api";
    }
}