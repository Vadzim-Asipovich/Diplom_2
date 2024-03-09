import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseHttpClient {

    private RequestSpecification baseRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(URL.HOST)
                .addHeader("Content-Type", "application/json")
                .build();
    }
    private RequestSpecification baseRequestSpecWithAuth(String accessToken) {
        return new RequestSpecBuilder()
                .setBaseUri(URL.HOST)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", accessToken)
                .build();
    }

    protected Response doGetRequest(String path) {
        return given()
                .spec(baseRequestSpec())
                .get(path)
                .thenReturn();
    }
    protected Response doAuthorizedGetRequest(String path, String accessToken) {
        return given()
                .spec(baseRequestSpecWithAuth(accessToken))
                .get(path)
                .thenReturn();
    }

    protected Response doPostRequest(String path, Object body) {
        return given()
                .spec(baseRequestSpec())
                .body(body)
                .post(path)
                .thenReturn();
    }
    protected Response doAuthorizedPostRequest(String path, String accessToken, Object body) {
        return given()
                .spec(baseRequestSpecWithAuth(accessToken))
                .body(body)
                .post(path)
                .thenReturn();
    }
    protected Response doDeleteRequest(String path) {
        return given()
                .spec(baseRequestSpec())
                .delete(path)
                .thenReturn();
    }
    protected Response doAuthorizedDeleteRequest(String path, String accessToken) {
        return given()
                .spec(baseRequestSpecWithAuth(accessToken))
                .delete(path)
                .thenReturn();
    }
    protected Response doPatchRequest(String path) {
        return given()
                .spec(baseRequestSpec())
                .patch(path)
                .thenReturn();
    }
    protected Response doAuthorizedPatchRequest(String path, String accessToken, Object body) {
        return given()
                .spec(baseRequestSpecWithAuth(accessToken))
                .body(body)
                .patch(path)
                .thenReturn();
    }
}