import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

public class UserRegistrationSuccessfulTest {
    private final UserRegistrationSteps userRegistrationSteps = new UserRegistrationSteps();
    private final UserSteps userActionSteps = new UserSteps();
    private final UserToRegister user = new UserToRegister(SampleUser.email, SampleUser.password, SampleUser.name);

    @After
    public void tearDown() {
        String accessToken = userRegistrationSteps.getAccessToken();
        userActionSteps.deleteUser(accessToken);
    }
    @Test
    @DisplayName("It's possible to register a unique user")
    public void createUniqueUserTest() {
        userRegistrationSteps.createUser(user);
        userRegistrationSteps.checkUserCreationSuccessfulResponse();
    }
}






