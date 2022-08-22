package dev.martin.service;

import dev.martin.entities.Complaint;

import java.util.List;

public interface ComplaintService {

    //Create
    Complaint registerComplaint(Complaint complaint);

    //Read
    List<Complaint> retrieveAllComplaints();
    Complaint retrieveComplaintById(int id);

    //Update
    Complaint modifyComplaint(Complaint complaint);
}
