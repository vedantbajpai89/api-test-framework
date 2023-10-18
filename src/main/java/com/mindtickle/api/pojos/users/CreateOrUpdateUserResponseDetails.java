package com.mindtickle.api.pojos.users;

import lombok.Setter;

@Setter
public class CreateOrUpdateUserResponseDetails {
    private int code;
    private String type;
    private String message;
}
