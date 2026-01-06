package models;

import lombok.Data;

@Data
public class GetUserResponse {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private User.UserStatus userStatus;
}
