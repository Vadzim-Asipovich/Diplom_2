import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class IngredientsSteps {
    private IngredientsAPI ingredientsAPI = new IngredientsAPI();
    private ValidatableResponse response;
    @Step("Get first ingredient from list")
    public String getFirstIngredientID() {
        response = ingredientsAPI.getIngredientsList().then();
        return response.extract().path("data[0]._id");
    }
}
