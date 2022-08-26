package dev.martin.data;

import dev.martin.entities.Complaint;
import dev.martin.entities.Townsperson;

import java.util.List;

public interface TownspersonDAO {

    //Create
    Townsperson createTownsperson(Townsperson townsperson);

    //Read
    Townsperson getTownspersonByUsername(String username);
    List<Townsperson> getTownspeopleByApproval(boolean approved);

    //Update
    Townsperson updateTownsperson(Townsperson townsperson);

}
