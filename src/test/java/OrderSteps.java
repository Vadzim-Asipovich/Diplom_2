import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;

public class OrderSteps {
    private final OrdersAPI ordersAPI = new OrdersAPI();
    private ValidatableResponse response;
    @Step("Get order list")
    public void getOrderList(String accessToken) {
        response = ordersAPI.getOrderList(accessToken).then();
    }
    @Step("Check order list response with authorized user")
    public void checkOrderListResponseAuthorized() {
        response
                .assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
    }
    @Step("Check order list response with unauthorized user")
    public void checkOrderListResponseUnauthorized() {
        response
                .assertThat()
                .statusCode(401)
                .and()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("You should be authorised"));
    }

}
