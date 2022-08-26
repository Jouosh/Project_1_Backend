package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Complaint;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateComplaintHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //Get id from path
        int id = Integer.parseInt(ctx.pathParam("id"));

        //convert json into complaint, set id to match with path
        String inJson = ctx.body();
        Gson gson = new Gson();
        Complaint complaint = gson.fromJson(inJson, Complaint.class);
        complaint.setComplaintId(id);

        //send employee to service layer to modify, then output result as body
        Complaint modifiedComplaint = App.complaintService.modifyComplaint(complaint);
        String outJson = gson.toJson(modifiedComplaint);
        ctx.result(outJson);

    }

}
