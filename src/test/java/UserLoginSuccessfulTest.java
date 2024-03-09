import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserLoginSuccessfulTest {
    private final UserRegistrationSteps userRegistrationSteps = new UserRegistrationSteps();
    private final UserSteps userActionSteps = new UserSteps();
    private final UserLoginSteps userLoginSteps = new UserLoginSteps();
    private final UserToRegister user = new UserToRegister(SampleUser.email, SampleUser.password, SampleUser.name);
    private final UserToLogin userToLogin = new UserToLogin(SampleUser.email, SampleUser.password);
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

    @Test
    @DisplayName("It's possible to login a user")
    public void loginUserTest() {
        userLoginSteps.loginUser(userToLogin);
        userLoginSteps.checkUserLoginSuccessfulResponse();
    }}
