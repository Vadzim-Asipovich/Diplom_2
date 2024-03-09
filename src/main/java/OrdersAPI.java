import io.restassured.response.Response;

public class OrdersAPI extends BaseHttpClient {
    private final String apiPath = "/api/orders";
    public Response getOrderList(String accessToken) {
        return doAuthorizedGetRequest(apiPath, accessToken);
    }
    public Response createOrder(String accessToken, Object body) {
        return doAuthorizedPostRequest(apiPath, accessToken, body);
    }
}
