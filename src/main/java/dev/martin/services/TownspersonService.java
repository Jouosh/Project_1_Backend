package dev.martin.services;

import dev.martin.entities.Townsperson;

import java.util.List;

public interface TownspersonService {

    //Create
    Townsperson registerTownsperson(Townsperson townsperson);

    List<Townsperson> retrieveTownspeopleByApproval(boolean approval);

}
