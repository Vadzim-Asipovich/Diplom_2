import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

public class OrderCreationSteps {
    private final OrdersAPI ordersAPI = new OrdersAPI();
    private ValidatableResponse response;

    @Step("Create order")
    public void createOrder(String accessToken, String ingredientID) {
        OrderToCreate orderToCreate = new OrderToCreate();
        orderToCreate.setIngredients(List.of(ingredientID));
        response = ordersAPI.createOrder(accessToken, orderToCreate).then();
    }
    @Step("Create order without ingredient")
    public void createOrderWithoutIngredient(String accessToken) {
        OrderToCreate orderToCreate = new OrderToCreate();
        response = ordersAPI.createOrder(accessToken, orderToCreate).then();
    }
    @Step("Check order creation unauthorized response")
    public void checkUnauthorizedOrderCreationResponse() {
        response
                .assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
    }
    @Step("Check order creation unauthorized without ingredient response")
    public void checkUnauthorizedOrderWithoutIngredientResponse() {
        response
                .assertThat()
                .statusCode(400)
                .and()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("Ingredient ids must be provided"));
    }
    @Step("Check order creation authorized response")
    public void checkAuthorizedOrderCreationResponse() {
        response
                .assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true))
                .and()
                .body("order.status", equalTo("done"));
    }
    @Step ("Check order creation with incorrect product id")
    public void checkOrderCreationWithIncorrectProductID() {
        response
                .assertThat()
                .statusCode(500);
    }

}
