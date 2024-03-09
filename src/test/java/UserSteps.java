import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;

public class UserSteps {
    private UserAPI userAPI = new UserAPI();
    private ValidatableResponse response;
    @Step("Get user")
    public void getUser(String accessToken) {
        response = userAPI.getUser(accessToken).then();
    }
    @Step("Delete user")
    public void deleteUser(String accessToken) {
        response = userAPI.deleteUser(accessToken).then();
    }
    @Step("Update user")
    public void updateUser(String accessToken, Object body) {
        response = userAPI.updateUser(accessToken, body).then();
    }
    @Step("Check user update response is successful")
    public void checkUserUpdateSuccessfulResponse() {
        response
                .assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
    }
    @Step("Check unauthorized user update response failed")
    public void checkUnauthorizedUserUpdateFailedResponse() {
        response
                .assertThat()
                .statusCode(401)
                .and()
                .body("message", equalTo("You should be authorised"));
    }
    @Step("Check user update response contains updated user data")
    public void checkUserUpdateResponseContainsUpdatedUserData(UserToRegister body) {
        response
                .assertThat()
                .body("user.email", equalTo(body.getEmail()))
                .and()
                .body("user.name", equalTo(body.getName()));
    }
}
