package dev.martin.service;

import dev.martin.data.ComplaintDAO;
import dev.martin.entities.Complaint;

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

        if (complaint.getDescription().length() == 0) {
            throw new RuntimeException("Complaint must have a description");
        }

        Complaint savedComplaint = complaintDAO.createComplaint(complaint);

        return savedComplaint;
    }
}
