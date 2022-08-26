package dev.martin.services;

import dev.martin.entities.Townsperson;

import java.util.List;

public interface TownspersonService {

    //Create
    Townsperson registerTownsperson(Townsperson townsperson);

    //Read
    List<Townsperson> retrieveTownspeopleByApproval(boolean approval);

    //Update
    Townsperson modifyTownsperson(Townsperson townsperson);

}
