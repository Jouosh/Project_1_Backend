package dev.martin.app;

import dev.martin.data.ComplaintDAOPostgres;
import dev.martin.data.MeetingDAOPostgres;
import dev.martin.data.TownspersonDAOPostrgres;
import dev.martin.exceptions.NoTownspersonFoundException;
import dev.martin.exceptions.PasswordMismatchException;
import dev.martin.exceptions.UsernameAlreadyInUseException;
import dev.martin.handlers.*;
import dev.martin.services.*;
import io.javalin.Javalin;

public class App {

    //Connections to service layer
    public static ComplaintService complaintService = new ComplaintServiceImpl(new ComplaintDAOPostgres());
    public static MeetingService meetingService = new MeetingServiceImpl(new MeetingDAOPostgres());
    public static LoginService loginService = new LoginServiceImpl(new TownspersonDAOPostrgres());
    public static TownspersonService townspersonService = new TownspersonServiceImpl(new TownspersonDAOPostrgres());

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

        //Login handler
        LoginHandler loginHandler = new LoginHandler();

        //Townsperson handlers
        CreateTownspersonHandler createTownspersonHandler = new CreateTownspersonHandler();
        GetTownspeopleByApprovalHandler getTownspeopleByApprovalHandler = new GetTownspeopleByApprovalHandler();

        //Complaint routes
        app.post("/complaints", createComplaintHandler);
        app.get("/complaints", getAllComplaintsHandler);
        app.get("/complaints/{id}", getComplaintByIdHandler);
        app.put("/complaints/{id}", updateComplaintHandler);

        //Meeting routes
        app.post("/meetings", createMeetingHandler);
        app.get("/meetings", getAllMeetingsHandler);

        //Login route
        app.post("/login", loginHandler);

        //Townsperson routes
        app.post("/townspeople", createTownspersonHandler);
        app.get("/townspeople/{approval}", getTownspeopleByApprovalHandler);

        //Login exceptions
        app.exception(PasswordMismatchException.class, (exception, ctx) -> {
            ctx.status(400);
            ctx.result("password did not match");
        });

        app.exception(NoTownspersonFoundException.class, (exception, ctx) -> {
            ctx.status(404);
            ctx.result("No employee found: " + exception.getMessage());
        });

        //Townsperson Exception
        app.exception(UsernameAlreadyInUseException.class, (exception, ctx) -> {
            ctx.status(400);
            ctx.result(exception.getMessage());
        });

        app.start();

    }

}
