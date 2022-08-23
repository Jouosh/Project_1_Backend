package dev.martin.services;

import dev.martin.data.ComplaintDAO;
import dev.martin.entities.Complaint;

import java.util.List;

public class ComplaintServiceImpl implements ComplaintService{

    //Connection to data layer
    private ComplaintDAO complaintDAO;

    //Constructor
    public ComplaintServiceImpl(ComplaintDAO complaintDAO) {
        this.complaintDAO = complaintDAO;
    }

    //Create
    @Override
    public Complaint registerComplaint(Complaint complaint) {

        //Check that there is a description
        if (complaint.getDescription().length() == 0) {
            throw new RuntimeException("Complaint must have a description");
        }

        //If passed, send to data layer, and return result
        Complaint savedComplaint = complaintDAO.createComplaint(complaint);

        return savedComplaint;
    }

    //Read
    @Override
    public List<Complaint> retrieveAllComplaints() { return complaintDAO.getAllComplaints(); }

    @Override
    public Complaint retrieveComplaintById(int id) { return complaintDAO.getComplaintById(id); }

    //Update
    @Override
    public Complaint modifyComplaint(Complaint complaint) {

        //Make sure there's a description
        if (complaint.getDescription().length() == 0) {
            throw new RuntimeException("Updated complaint must have a description");
        }

        //Send to data layer and return result
        Complaint modifiedComplaint = complaintDAO.updateComplaint(complaint);

        return modifiedComplaint;
    }

}
