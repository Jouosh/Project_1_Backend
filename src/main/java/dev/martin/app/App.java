package dev.martin.app;

import dev.martin.data.ComplaintDAOPostgres;
import dev.martin.handlers.CreateComplaintHandler;
import dev.martin.service.ComplaintService;
import dev.martin.service.ComplaintServiceImpl;
import io.javalin.Javalin;

public class App {

    public static ComplaintService complaintService = new ComplaintServiceImpl(new ComplaintDAOPostgres());

    public static void main(String args[]) {

        Javalin app = Javalin.create(config -> {
            config.enableDevLogging();
            config.enableCorsForAllOrigins();
        });

        //Complaint handlers
        CreateComplaintHandler createComplaintHandler = new CreateComplaintHandler();

        //Complaint routes
        app.post("/complaint", createComplaintHandler);

        app.start();

    }

}
