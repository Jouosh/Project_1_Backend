package dev.martin.data;

import dev.martin.entities.Complaint;
import dev.martin.entities.Status;
import dev.martin.utils.ConnectionUtil;

import java.sql.*;

public class ComplaintDAOPostgres implements ComplaintDAO {

    //Create
    @Override
    public Complaint createComplaint(Complaint complaint) {

        //Create connection in try-catch block
        try (Connection conn = ConnectionUtil.createConnection()) {

            //Create prepared statement, fill in the blank and execute
            String sql = "insert into complaint values (default, ?, -1, 'PENDING')";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, complaint.getDescription());
            preparedStatement.execute();

            //Get generated id, and put into variable
            ResultSet keys = preparedStatement.getGeneratedKeys();
            keys.next();
            int generatedKey = keys.getInt("complaint_id");

            //Set complaint fields to match with table, then return
            complaint.setComplaintId(generatedKey);
            complaint.setStatus(Status.PENDING);
            complaint.setMeeting(-1);
            return complaint;

        //print stack in case of failure
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
