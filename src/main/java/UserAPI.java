import io.restassured.response.Response;

public class UserAPI extends BaseHttpClient{
    private final String apiPath = "/api/auth/user";
    public Response deleteUser(String accessToken) {
        return doAuthorizedDeleteRequest(apiPath, accessToken);
    }
    public Response getUser(String accessToken) {
        return doAuthorizedGetRequest(apiPath, accessToken);
    }
    public Response updateUser(String accessToken, Object body) {
        return doAuthorizedPatchRequest(apiPath, accessToken, body);
    }

}
