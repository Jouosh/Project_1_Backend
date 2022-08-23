package dev.martin.data;

import dev.martin.entities.Townsperson;

public interface TownspersonDAO {

    Townsperson getTownspersonByUsername(String username);

}
