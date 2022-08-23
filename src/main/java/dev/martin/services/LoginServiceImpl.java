package dev.martin.services;

import dev.martin.data.TownspersonDAO;
import dev.martin.entities.Townsperson;
import dev.martin.exceptions.NoTownspersonFoundException;
import dev.martin.exceptions.PasswordMismatchException;

public class LoginServiceImpl implements LoginService {

    private TownspersonDAO townspersonDAO;

    public LoginServiceImpl(TownspersonDAO townspersonDAO) {
        this.townspersonDAO = townspersonDAO;
    }

    @Override
    public Townsperson validate_user(String username, String password) {

        Townsperson townsperson = this.townspersonDAO.getTownspersonByUsername(username);

        if (townsperson == null) {
            throw new NoTownspersonFoundException("No employee found with username" + username);
        }

        if (!townsperson.getPassword().equals(password)) {
            throw new PasswordMismatchException("Password does not match database");
        }
        return townsperson;
    }

}
