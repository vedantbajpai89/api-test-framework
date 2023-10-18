package com.mindtickle.api.pojos.users;

import lombok.Setter;

@Setter
public class GetUpdatedUserResponseDetails {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}
