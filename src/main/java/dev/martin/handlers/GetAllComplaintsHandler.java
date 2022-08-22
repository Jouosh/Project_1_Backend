package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Complaint;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetAllComplaintsHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //Get list from service layer and convert to json form
        List<Complaint> complaintList = App.complaintService.retrieveAllComplaints();
        Gson gson = new Gson();
        String outJson = gson.toJson(complaintList);

        //set result and status then return
        ctx.status(200);
        ctx.result(outJson);

    }
}
