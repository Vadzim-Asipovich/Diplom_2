import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
@RunWith(Parameterized.class)
public class UserRegistrationIsIncompleteFailsTest {
    private final String email;
    private final String password;
    private final String name;
    private final UserRegistrationSteps userRegistrationSteps = new UserRegistrationSteps();

    public UserRegistrationIsIncompleteFailsTest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Parameterized.Parameters
    public static Object[][] getUserData() {
        return new Object[][] {
                {SampleUser.email, SampleUser.password, ""},
                {SampleUser.email, "", SampleUser.name},
                {"", SampleUser.password, SampleUser.name}
        };
    }
    @Test
    @DisplayName("It's impossible to register a user with incomplete data")
    public void createIncompleteUserTest() {
        UserToRegister user = new UserToRegister(email, password, name);
        userRegistrationSteps.createUser(user);
        userRegistrationSteps.checkEmptyFieldUserCreationFailedResponse();
    }
}
