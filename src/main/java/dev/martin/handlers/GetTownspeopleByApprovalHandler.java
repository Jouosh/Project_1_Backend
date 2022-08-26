package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Townsperson;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetTownspeopleByApprovalHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //Get list based on path param, and turn to Json
        boolean approval = Boolean.parseBoolean(ctx.pathParam("approval"));
        List<Townsperson> townspeople = App.townspersonService.retrieveTownspeopleByApproval(approval);
        Gson gson = new Gson();
        String outJson = gson.toJson(townspeople);

        //set status and result then return
        ctx.status(200);
        ctx.result(outJson);

    }

}
