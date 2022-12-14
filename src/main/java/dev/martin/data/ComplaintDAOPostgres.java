package dev.martin.data;

import dev.martin.entities.Complaint;
import dev.martin.entities.Status;
import dev.martin.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Complaint getComplaintById(int id) {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Prepare statement, and fill in blank with id
            String sql = "select * from complaint where complaint_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            //Put results into result set, then into a complaint object
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Complaint complaint = new Complaint();
            complaint.setComplaintId(resultSet.getInt("complaint_id"));
            complaint.setDescription(resultSet.getString("description"));
            complaint.setMeeting(resultSet.getInt("meeting"));
            complaint.setStatus(Status.valueOf(resultSet.getString("status")));

            //return selected complaint
            return complaint;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Complaint> getAllComplaints() {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Get all complaints from database and put into result set
            String sql = "select * from complaint";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            //convert result set into complaint instances and put into list
            List<Complaint> complaintList = new ArrayList();
            while (resultSet.next()) {
                Complaint complaint = new Complaint();
                complaint.setComplaintId(resultSet.getInt("complaint_id"));
                complaint.setDescription(resultSet.getString("description"));
                complaint.setMeeting(resultSet.getInt("meeting"));
                complaint.setStatus(Status.valueOf(resultSet.getString("status")));
                complaintList.add(complaint);
            }

            //return completed list
            return  complaintList;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Complaint updateComplaint(Complaint complaint) {

        try (Connection conn = ConnectionUtil.createConnection()) {

            //Create and prepare SQL statement
            String sql = "update complaint set description = ?, meeting = ?, status = ? where complaint_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //Fill in the blanks with complaint's info
            preparedStatement.setString(1, complaint.getDescription());
            preparedStatement.setInt(2, complaint.getMeeting());
            preparedStatement.setString(3, complaint.getStatus().toString());
            preparedStatement.setInt(4, complaint.getComplaintId());

            //Execute update and return complaint
            preparedStatement.executeUpdate();
            return complaint;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
