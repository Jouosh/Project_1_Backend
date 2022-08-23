package dev.martin.services;

import dev.martin.data.TownspersonDAO;
import dev.martin.entities.Townsperson;

public class LoginServiceImpl implements LoginService {

    private TownspersonDAO townspersonDAO;

    public LoginServiceImpl(TownspersonDAO employeeDAO) {
        this.townspersonDAO = townspersonDAO;
    }

    @Override
    public Townsperson validate_user(String username, String password) {

        Townsperson townsperson = this.townspersonDAO.getTownspersonByUsername(username);

        if (townsperson == null) {
            throw new RuntimeException("No employee found with username" + username);
        }

        if (!townsperson.getPassword().equals(password)) {
            throw new RuntimeException("Password does not match database");
        }
        return townsperson;
    }

}
