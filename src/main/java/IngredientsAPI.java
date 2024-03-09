import io.restassured.response.Response;

public class IngredientsAPI extends BaseHttpClient {
    private final String apiPath = "/api/ingredients";
    public Response getIngredientsList() {
        return doGetRequest(apiPath);
    }
}
