import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;

public class UserLoginSteps {
    private final LoginAPI api = new LoginAPI();
    private ValidatableResponse response;
    @Step("Login user")
    public void loginUser(UserToLogin user) {
        response = api.loginUser(user).then();
    }
    @Step("Check user login response is successful")
    public void checkUserLoginSuccessfulResponse() {
        response
                .assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
    }
    @Step("Check incomplete user login response is failed")
    public void checkIncorrectCredentialsUserLoginFailedResponse() {
        response
                .assertThat()
                .statusCode(401)
                .and()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("email or password are incorrect"));
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
