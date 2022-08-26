package dev.martin.services;

import dev.martin.data.TownspersonDAO;
import dev.martin.entities.Townsperson;
import dev.martin.exceptions.UsernameAlreadyInUseException;

public class TownspersonServiceImpl implements TownspersonService{

    //Connection to data layer
    TownspersonDAO townspersonDAO;

    //Constructor
    public TownspersonServiceImpl(TownspersonDAO townspersonDAO) {
        this.townspersonDAO = townspersonDAO;
    }

    @Override
    public Townsperson registerTownsperson(Townsperson townsperson) {

        if (townspersonDAO.getTownspersonByUsername(townsperson.getUsername()) != null) {
            throw new UsernameAlreadyInUseException(townsperson.getUsername() + " is already in use");
        }

        if (townsperson.getUsername().length() <= 3) {
            throw new RuntimeException("Username is too short");
        }

        if (townsperson.getPassword().length() <= 3) {
            throw new RuntimeException("Password is too short");
        }

        Townsperson savedTownsperson = townspersonDAO.createTownsperson(townsperson);
        return savedTownsperson;
    }
}
