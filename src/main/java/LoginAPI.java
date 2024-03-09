import io.restassured.response.Response;

public class LoginAPI extends BaseHttpClient{
    private final String apiPath = "/api/auth/login";
    public Response loginUser(UserToLogin user) {
        return doPostRequest(apiPath, user);
    }
}
