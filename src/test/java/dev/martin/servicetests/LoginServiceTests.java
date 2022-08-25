package dev.martin.servicetests;

import dev.martin.data.TownspersonDAO;
import dev.martin.data.TownspersonDAOPostrgres;
import dev.martin.entities.Role;
import dev.martin.entities.Townsperson;
import dev.martin.services.LoginService;
import dev.martin.services.LoginServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LoginServiceTests {

    static TownspersonDAO townspersonDAO = Mockito.mock(TownspersonDAO.class);
    static LoginService loginService = new LoginServiceImpl(townspersonDAO);

    @Test
    public void logins_must_have_a_valid_username() {
        Mockito.when(townspersonDAO.getTownspersonByUsername("Jimmyyyum")).thenReturn(null);
        Assertions.assertThrows(RuntimeException.class,
                () -> loginService.validate_user("Jimmyyyum", "Jimspass"));
    }

    @Test
    public void logins_must_have_a_matching_password() {
        Townsperson townsperson = new Townsperson(1, "Jimmyyum", "Jimspass",
                Role.CONSTITUENT, true);
        Mockito.when(townspersonDAO.getTownspersonByUsername("Jimmyyum")).thenReturn(townsperson);
        Assertions.assertThrows(RuntimeException.class,
                () -> loginService.validate_user("Jimmyyyum", "Jimssssspass"));
    }

}
