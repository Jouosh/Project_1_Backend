package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Townsperson;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetTownspersonByUsernameHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        String username = ctx.pathParam("username");
        Townsperson townsperson = App.townspersonService.retrieveTownspersonByUsername(username);

        Gson gson = new Gson();
        String outJson = gson.toJson(townsperson);
        ctx.status(200);
        ctx.result(outJson);

    }

}
