import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;

public class UserRegistrationSteps {
    private RegistrationAPI api = new RegistrationAPI();
    private ValidatableResponse response;

    @Step("Create a user")
    public void createUser(UserToRegister user) {
        response = api.registerUser(user).then();
    }

    @Step("Check user creation response is successful")
    public void checkUserCreationSuccessfulResponse() {
        response
                .assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
    }
    @Step("Check if response is failed for user with existing email")
    public void checkRepeatedUserCreationFailedResponse() {
        response
                .assertThat()
                .statusCode(403)
                .and()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("User already exists"));
    }
    @Step("Check if response is failed when one of the fields is empty")
    public void checkEmptyFieldUserCreationFailedResponse() {
        response
                .assertThat()
                .statusCode(403)
                .and()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("Email, password and name are required fields"));
    }
    @Step("Get accessToken")
    public String getAccessToken(){
        return response.extract().body().path("accessToken").toString();
    }
    @Step("Get refreshToken")
    public String getRefreshToken(){
        return response.extract().body().path("refreshToken").toString();
    }
}
