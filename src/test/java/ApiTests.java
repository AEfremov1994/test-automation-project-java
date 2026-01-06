import controllers.UserController;
import io.restassured.response.Response;
import models.AddUserResponse;
import models.GetUserResponse;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

public class ApiTests {

    UserController userController = new UserController();

    @Test
    public void createUserControllerTest() {
        User userBuilder = User.builder()
                .username("testCreateUsername")
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .password("password")
                .phone("phone")
                .userStatus(User.UserStatus.ACTIVE)
                .build();

        Response response = userController.createUser(userBuilder);
        AddUserResponse createdUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());
        Assertions.assertFalse(createdUserResponse.getMessage().isEmpty());

        userController.deleteUserByUsername("testCreateUsername");
    }

    @Test
    public void getUserByUsernameTest() {
        User userBuilder = User.builder()
                .id(124)
                .username("testGetUsername")
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .password("password")
                .phone("phone")
                .userStatus(User.UserStatus.ACTIVE)
                .build();

        userController.createUser(userBuilder);

        Response response = userController.getUserByUsername("testGetUsername");

        Assertions.assertEquals(200, response.statusCode());

        GetUserResponse foundUser = response.as(GetUserResponse.class);
        Assertions.assertEquals("testGetUsername", foundUser.getUsername());

        userController.deleteUserByUsername("testGetUsername");
    }

    @Test
    public void updateUserControllerTest() {
        User userBuilder = User.builder()
                .id(123)
                .username("testUpdateUsername")
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .password("password")
                .phone("phone")
                .userStatus(User.UserStatus.ACTIVE)
                .build();

        userController.createUser(userBuilder);

        userBuilder.setEmail("newEmail@example.com");
        Response response = userController.updateUser(userBuilder);
        AddUserResponse updatedUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, updatedUserResponse.getCode());
        Assertions.assertEquals("unknown", updatedUserResponse.getType());
        Assertions.assertFalse(updatedUserResponse.getMessage().isEmpty());

        GetUserResponse updatedUser = userController.getUserByUsername("testUpdateUsername").as(GetUserResponse.class);
        Assertions.assertEquals("newEmail@example.com", updatedUser.getEmail());

        userController.deleteUserByUsername("testUpdateUsername");
    }

    @Test
    public void deleteUserByUsernameTest() {
        User userBuilder = User.builder()
                .username("testDeleteUsername")
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .password("password")
                .phone("phone")
                .userStatus(User.UserStatus.ACTIVE)
                .build();

        userController.createUser(userBuilder);

        Response response = userController.deleteUserByUsername("testDeleteUsername");
        AddUserResponse deletedUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, deletedUserResponse.getCode());
        Assertions.assertEquals("unknown", deletedUserResponse.getType());
        Assertions.assertEquals("testDeleteUsername", deletedUserResponse.getMessage());

        Response responseAfterDelete = userController.getUserByUsername("testDeleteUsername");
        Assertions.assertEquals(404, responseAfterDelete.statusCode());
    }
}
