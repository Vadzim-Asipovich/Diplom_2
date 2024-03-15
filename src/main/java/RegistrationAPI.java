import io.restassured.response.Response;

public class RegistrationAPI extends BaseHttpClient{
    private final String apiPath = "/api/auth/register";
    public Response registerUser(UserToRegister user) {
        return doPostRequest(apiPath, user);
    }
}
