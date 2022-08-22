package dev.martin.data;

import dev.martin.entities.Complaint;

import java.util.List;

public interface ComplaintDAO {

    //Create
    Complaint createComplaint(Complaint complaint);

    //Read
    List<Complaint> getAllComplaints();

    //Update
    Complaint updateComplaints(Complaint complaint);

}
