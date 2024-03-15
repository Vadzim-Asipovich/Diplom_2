import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
    private final UserRegistrationSteps userRegistrationSteps = new UserRegistrationSteps();
    private final IngredientsSteps ingredientsSteps = new IngredientsSteps();
    OrderCreationSteps orderCreationSteps = new OrderCreationSteps();
    private final UserSteps userSteps = new UserSteps();
    private final OrderSteps orderSteps = new OrderSteps();
    private final UserToRegister user = new UserToRegister(SampleUser.email, SampleUser.password, SampleUser.name);
    String accessToken;
    String ingredientID;
    @Before
    public void setUp() {
        userRegistrationSteps.createUser(user);
        accessToken = userRegistrationSteps.getAccessToken();
        ingredientID = ingredientsSteps.getFirstIngredientID();
        orderCreationSteps.createOrder(accessToken, ingredientID);
    }
    @After
    public void tearDown() {
        userSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("It's possible to get order list as authorized user")
    public void getOrderListAsAuthorizedUserTest() {
        orderSteps.getOrderList(accessToken);
        orderSteps.checkOrderListResponseAuthorized();
    }
    @Test
    @DisplayName("It's impossible to get order list as unauthorized user")
    public void getOrderListAsUnauthorizedUserTest() {
        orderSteps.getOrderList("");
        orderSteps.checkOrderListResponseUnauthorized();
    }
}
