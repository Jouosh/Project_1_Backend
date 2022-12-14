package dev.martin.services;

import dev.martin.data.TownspersonDAO;
import dev.martin.entities.Townsperson;
import dev.martin.exceptions.NoTownspersonFoundException;
import dev.martin.exceptions.UsernameAlreadyInUseException;

import java.util.List;

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

    @Override
    public Townsperson retrieveTownspersonByUsername(String username) {

        Townsperson townsperson = this.townspersonDAO.getTownspersonByUsername(username);

        if (townsperson == null) {
            throw new NoTownspersonFoundException("No employee found with username" + username);
        }

        return townsperson;
    }

    @Override
    public List<Townsperson> retrieveTownspeopleByApproval(boolean approval) {
        return townspersonDAO.getTownspeopleByApproval(approval);
    }

    @Override
    public Townsperson modifyTownsperson(Townsperson townsperson) {

        if (townsperson.getUsername().length() <= 3) {
            throw new RuntimeException("Username is too short");
        }

        if (townsperson.getPassword().length() <= 3) {
            throw new RuntimeException("Password is too short");
        }

        Townsperson savedTownsperson = townspersonDAO.updateTownsperson(townsperson);
        return savedTownsperson;
    }
}
