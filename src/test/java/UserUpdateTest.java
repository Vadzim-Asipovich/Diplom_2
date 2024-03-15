import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserUpdateTest {
    private final UserRegistrationSteps userRegistrationSteps = new UserRegistrationSteps();
    private final UserSteps userSteps = new UserSteps();
    private final UserLoginSteps userLoginSteps = new UserLoginSteps();
    private final UserToRegister user = new UserToRegister(SampleUser.email, SampleUser.password, SampleUser.name);
    private final UserToRegister updatedUser = new UserToRegister(SampleUser.secondEmail, SampleUser.secondPassword, SampleUser.secondName);
    private final List<String> accessTokenList = new ArrayList<>();
    @Before
    public void setUp() {
        userRegistrationSteps.createUser(user);
        accessTokenList.add(userRegistrationSteps.getAccessToken());
    }
    @After
    public void tearDown() {
        for (String token : accessTokenList) {
            userSteps.deleteUser(token);
        }
    }
    @Test
    @DisplayName("It's possible to update all user fields as an authorized user")
    public void updateAuthorizedUserTest() {
        userSteps.updateUser(accessTokenList.get(0), updatedUser);
        userSteps.checkUserUpdateSuccessfulResponse();
        userSteps.checkUserUpdateResponseContainsUpdatedUserData(updatedUser);
        userLoginSteps.loginUser(new UserToLogin(updatedUser.getEmail(), updatedUser.getPassword()));
        userLoginSteps.checkUserLoginSuccessfulResponse();
    }

    @Test
    @DisplayName("It's impossible to update user as an unauthorized user")
    public void updateUnauthorizedUserTest() {
        userSteps.updateUser("", updatedUser);
        userSteps.checkUnauthorizedUserUpdateFailedResponse();
    }

    @Test
    @DisplayName("It's impossible to update user with duplicate email")
    public void updateDuplicateEmailUserTest() {
        userRegistrationSteps.createUser(updatedUser);
        accessTokenList.add(userRegistrationSteps.getAccessToken());
        userSteps.updateUser(accessTokenList.get(1), user);
        userSteps.checkUserUpdateToExistingEmailFails();
    }

}
