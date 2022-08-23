package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Complaint;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetComplaintByIdHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //get id from path param, and try to get complaint by id with it
        int id = Integer.parseInt(ctx.pathParam("id"));
        Complaint complaint = App.complaintService.retrieveComplaintById(id);

        //Return 404 if a complaint was not found
        if (complaint == null) {
            ctx.status(404);
            ctx.result("No complaint with id of " + id + " was found");
        }
        //Otherwise, put complaint into json and return it
        else {
            Gson gson = new Gson();
            String outJson = gson.toJson(complaint);
            ctx.status(200);
            ctx.result(outJson);
        }

    }

}
