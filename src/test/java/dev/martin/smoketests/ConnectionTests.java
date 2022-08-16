package dev.martin.smoketests;

import dev.martin.utils.ConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionTests {

    @Test
    void connection_available() {
        Connection connection = ConnectionUtil.createConnection();
        System.out.println(connection);
        Assertions.assertNotEquals(null, connection);
    }
}