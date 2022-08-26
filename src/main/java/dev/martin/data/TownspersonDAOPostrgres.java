package dev.martin.data;

import dev.martin.entities.Complaint;
import dev.martin.entities.Role;
import dev.martin.entities.Status;
import dev.martin.entities.Townsperson;
import dev.martin.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TownspersonDAOPostrgres implements TownspersonDAO{

    @Override
    public Townsperson createTownsperson(Townsperson townsperson) {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Create prepared statement and fill in blanks
            String sql = "insert into townsperson values (default, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,townsperson.getUsername());
            preparedStatement.setString(2, townsperson.getPassword());
            preparedStatement.setString(3, townsperson.getRole().toString());
            preparedStatement.setBoolean(4, townsperson.isApproved());

            //Execute and get generated id
            preparedStatement.execute();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            keys.next();
            int generatedKey = keys.getInt("town_id");

            //Set meetingId and return meeting
            townsperson.setTownId(generatedKey);
            return townsperson;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public Townsperson getTownspersonByUsername(String username) {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Prepare statement, and fill in blank with id
            String sql = "select * from townsperson where username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);

            //Put results into result set, then into a townsperson object
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Townsperson townsperson = new Townsperson();
                townsperson.setTownId(resultSet.getInt("town_id"));
                townsperson.setUsername(resultSet.getString("username"));
                townsperson.setPassword(resultSet.getString("password"));
                townsperson.setRole(Role.valueOf(resultSet.getString("role")));
                townsperson.setApproved(resultSet.getBoolean("approved"));
                return townsperson;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public List<Townsperson> getTownspeopleByApproval(boolean approved) {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Prepare statement, and fill in blank with id
            String sql = "select * from townsperson where approved = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setBoolean(1, approved);

            List<Townsperson> townspeople = new ArrayList();

            //Put results into result set, then into a townsperson object
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Townsperson townsperson = new Townsperson();
                townsperson.setTownId(resultSet.getInt("town_id"));
                townsperson.setUsername(resultSet.getString("username"));
                townsperson.setPassword(resultSet.getString("password"));
                townsperson.setRole(Role.valueOf(resultSet.getString("role")));
                townsperson.setApproved(resultSet.getBoolean("approved"));
                townspeople.add(townsperson);
            }

            //return selected townsperson
            return townspeople;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Townsperson updateTownsperson(Townsperson townsperson) {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Create and prepare SQL statement
            String sql = "update townsperson set username = ?, password = ?, role = ?, approved = ? where town_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //Fill in the blanks with townspersons info
            preparedStatement.setString(1, townsperson.getUsername());
            preparedStatement.setString(2, townsperson.getPassword());
            preparedStatement.setString(3, townsperson.getRole().toString());
            preparedStatement.setBoolean(4, townsperson.isApproved());
            preparedStatement.setInt(5, townsperson.getTownId());

            //Execute update and return townsperson
            preparedStatement.executeUpdate();
            return townsperson;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
