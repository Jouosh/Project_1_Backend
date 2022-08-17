package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Complaint;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateComplaintHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //Get json input and turn into complaint object
        String inJson = ctx.body();
        Gson gson = new Gson();
        Complaint complaint = gson.fromJson(inJson, Complaint.class);

        //Send complaint to service layer, store result in registeredComplaint
        Complaint registeredComplaint = App.complaintService.registerComplaint(complaint);

        //turn registered object to json, output result and status
        String outJson = gson.toJson(registeredComplaint);
        ctx.status(201);
        ctx.result(outJson);

    }
}
