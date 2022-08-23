package dev.martin.servicetests;

import dev.martin.data.ComplaintDAO;
import dev.martin.entities.Complaint;
import dev.martin.entities.Status;
import dev.martin.services.ComplaintService;
import dev.martin.services.ComplaintServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ComplaintServiceTests {

    //Create mock DAO and real service
    static ComplaintDAO complaintDAO = Mockito.mock(ComplaintDAO.class);
    static ComplaintService complaintService =new ComplaintServiceImpl(complaintDAO);

    @Test
    public void registered_complaints_must_have_a_description() {
        Complaint complaint = new Complaint(1, "", -1, Status.PENDING);
        Mockito.when(complaintDAO.createComplaint(complaint)).thenReturn(complaint);
        Assertions.assertThrows(RuntimeException.class, () -> complaintService.registerComplaint(complaint));
    }

    @Test
    public void modified_complaints_must_have_a_description() {
        Complaint complaint = new Complaint(1, "", -1, Status.PENDING);
        Mockito.when(complaintDAO.updateComplaint(complaint)).thenReturn(complaint);
        Assertions.assertThrows(RuntimeException.class, () -> complaintService.modifyComplaint(complaint));
    }

}
