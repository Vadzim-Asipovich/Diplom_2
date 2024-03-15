import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserRegistrationIsNotUniqueFailsTest {
    private final UserRegistrationSteps userRegistrationSteps = new UserRegistrationSteps();
    private final UserSteps userActionSteps = new UserSteps();
    private final UserToRegister user = new UserToRegister(SampleUser.email, SampleUser.password, SampleUser.name);
    private String accessToken;
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
    @DisplayName("It's impossible to register a user with existing email")
    public void createRepeatedUserTest() {
        userRegistrationSteps.createUser(user);
        userRegistrationSteps.checkRepeatedUserCreationFailedResponse();
    }

}
