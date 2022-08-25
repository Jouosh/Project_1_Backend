package dev.martin.data;

import dev.martin.entities.Townsperson;

import java.util.List;

public interface TownspersonDAO {

    //Read
    Townsperson getTownspersonByUsername(String username);
    List<Townsperson> getTownspersonsByApproval(boolean approved);

}
