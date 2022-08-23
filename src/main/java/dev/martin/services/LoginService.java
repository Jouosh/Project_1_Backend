package dev.martin.services;

import dev.martin.entities.Townsperson;

public interface LoginService {

    Townsperson validate_user(String username, String password);

}