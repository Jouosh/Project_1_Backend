package dev.martin.app;

import dev.martin.data.ComplaintDAOPostgres;
import dev.martin.data.MeetingDAOPostgres;
import dev.martin.handlers.CreateComplaintHandler;
import dev.martin.handlers.CreateMeetingHandler;
import dev.martin.handlers.GetAllMeetingsHandler;
import dev.martin.service.ComplaintService;
import dev.martin.service.ComplaintServiceImpl;
import dev.martin.service.MeetingService;
import dev.martin.service.MeetingServiceImpl;
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

        //Meeting handlers
        CreateMeetingHandler createMeetingHandler = new CreateMeetingHandler();
        GetAllMeetingsHandler getAllMeetingsHandler = new GetAllMeetingsHandler();

        //Complaint routes
        app.post("/complaint", createComplaintHandler);

        //Meeting routes
        app.post("/meeting", createMeetingHandler);
        app.get("/meeting", getAllMeetingsHandler);

        app.start();

    }

}
