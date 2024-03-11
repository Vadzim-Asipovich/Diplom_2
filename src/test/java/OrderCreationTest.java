import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderCreationTest {
    private final UserRegistrationSteps userRegistrationSteps = new UserRegistrationSteps();
    private final IngredientsSteps ingredientsSteps = new IngredientsSteps();
    OrderCreationSteps orderCreationSteps = new OrderCreationSteps();
    private final UserSteps userSteps = new UserSteps();
    private final UserToRegister user = new UserToRegister(SampleUser.email, SampleUser.password, SampleUser.name);
    String accessToken;
    String ingredientID;
    @Before
    public void setUp() {
        userRegistrationSteps.createUser(user);
        accessToken = userRegistrationSteps.getAccessToken();
        ingredientID = ingredientsSteps.getFirstIngredientID();
    }
    @After
    public void tearDown() {
        userSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("It's possible to create an order without authorization with ingredient")
    public void createUnauthorizedOrderWithIngredientTest() {
        orderCreationSteps.createOrder("", ingredientID);
        orderCreationSteps.checkUnauthorizedOrderCreationResponse();
    }
    @Test
    @DisplayName("It's impossible to create an order without ingredient (unauthorized)")
    public void createUnauthorizedOrderWithoutIngredientTest() {
        orderCreationSteps.createOrderWithoutIngredient("");
        orderCreationSteps.checkUnauthorizedOrderWithoutIngredientResponse();
    }
    @Test
    @DisplayName("It's possible to create an order with authorization")
    public void createAuthorizedOrderTest() {
        orderCreationSteps.createOrder(accessToken, ingredientID);
        orderCreationSteps.checkAuthorizedOrderCreationResponse();
    }
    @Test
    @DisplayName("It's impossible to create an order with incorrect product id")
    public void createOrderWithIncorrectProductIDTest() {
        orderCreationSteps.createOrder(accessToken, "incorrectID");
        orderCreationSteps.checkOrderCreationWithIncorrectProductID();
    }
}
