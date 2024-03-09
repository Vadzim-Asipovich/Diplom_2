import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class UserLoginIncorrectCredentialsFailsTest {
    private final String email;
    private final String password;
    private final UserRegistrationSteps userRegistrationSteps = new UserRegistrationSteps();
    private final UserSteps userActionSteps = new UserSteps();
    private final UserLoginSteps userLoginSteps = new UserLoginSteps();
    private final UserToRegister user = new UserToRegister(SampleUser.email, SampleUser.password, SampleUser.name);
    String accessToken;
    @Before
    public void setUp() {
        userRegistrationSteps.createUser(user);
        accessToken = userRegistrationSteps.getAccessToken();
    }
    @After
    public void tearDown() {
        userActionSteps.deleteUser(accessToken);
    }

    public UserLoginIncorrectCredentialsFailsTest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] getUserData() {
        return new Object[][] {
                {SampleUser.email, SampleUser.secondPassword},
                {SampleUser.secondEmail, SampleUser.password},
                {SampleUser.email, ""},
                {"", SampleUser.password},
                {"", ""}
        };
    }
    @Test
    @DisplayName("It's impossible to login with incorrect credentials")
    public void loginWithIncorrectCredentialsTest() {
        UserToLogin user = new UserToLogin(email, password);
        userLoginSteps.loginUser(user);
        userLoginSteps.checkIncorrectCredentialsUserLoginFailedResponse();
    }
}
