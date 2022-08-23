package dev.martin.app;

import dev.martin.data.ComplaintDAOPostgres;
import dev.martin.data.MeetingDAOPostgres;
import dev.martin.handlers.*;
import dev.martin.services.ComplaintService;
import dev.martin.services.ComplaintServiceImpl;
import dev.martin.services.MeetingService;
import dev.martin.services.MeetingServiceImpl;
import io.javalin.Javalin;

public class App {

    //Connections to service layer
    public static ComplaintService complaintService = new ComplaintServiceImpl(new ComplaintDAOPostgres());
    public static MeetingService meetingService = new MeetingServiceImpl(new MeetingDAOPostgres());

    public static void main(String args[]) {

        //create app with proper config
        Javalin app = Javalin.create(config -> {
            config.enableDevLogging();
            config.enableCorsForAllOrigins();
        });

        //Complaint handlers
        CreateComplaintHandler createComplaintHandler = new CreateComplaintHandler();
        GetAllComplaintsHandler getAllComplaintsHandler = new GetAllComplaintsHandler();
        GetComplaintByIdHandler getComplaintByIdHandler = new GetComplaintByIdHandler();
        UpdateComplaintHandler updateComplaintHandler = new UpdateComplaintHandler();

        //Meeting handlers
        CreateMeetingHandler createMeetingHandler = new CreateMeetingHandler();
        GetAllMeetingsHandler getAllMeetingsHandler = new GetAllMeetingsHandler();

        //Complaint routes
        app.post("/complaints", createComplaintHandler);
        app.get("/complaints", getAllComplaintsHandler);
        app.get("/complaints/{id}", getComplaintByIdHandler);
        app.put("/complaints/{id}", updateComplaintHandler);

        //Meeting routes
        app.post("/meetings", createMeetingHandler);
        app.get("/meetings", getAllMeetingsHandler);

        app.start();

    }

}
