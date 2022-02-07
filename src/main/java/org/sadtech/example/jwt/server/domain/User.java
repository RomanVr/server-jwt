package org.sadtech.example.jwt.server.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Set<Role> roles;

}
