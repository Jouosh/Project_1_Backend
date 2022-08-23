package dev.martin.data;

import dev.martin.entities.Complaint;
import dev.martin.entities.Role;
import dev.martin.entities.Status;
import dev.martin.entities.Townsperson;
import dev.martin.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TownspersonDAOPostrgres implements TownspersonDAO{

    @Override
    public Townsperson getTownspersonByUsername(String username) {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Prepare statement, and fill in blank with id
            String sql = "select * from townsperson where username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);

            //Put results into result set, then into a townsperson object
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Townsperson townsperson = new Townsperson();
            townsperson.setTownId(resultSet.getInt("town_id"));
            townsperson.setUsername(resultSet.getString("username"));
            townsperson.setPassword(resultSet.getString("password"));
            townsperson.setRole(Role.valueOf(resultSet.getString("role")));

            //return selected townsperson
            return townsperson;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
}
