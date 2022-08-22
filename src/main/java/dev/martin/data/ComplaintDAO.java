package dev.martin.data;

import dev.martin.entities.Complaint;

import java.util.List;

public interface ComplaintDAO {

    //Create
    Complaint createComplaint(Complaint complaint);

    //Read
    Complaint getComplaintById(int id);

    List<Complaint> getAllComplaints();

    //Update
    Complaint updateComplaint(Complaint complaint);

}
