package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Townsperson;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateTownspersonHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //Get json input and turn into townsperson object
        String inJson = ctx.body();
        Gson gson = new Gson();
        Townsperson townsperson = gson.fromJson(inJson, Townsperson.class);

        //Send townsperson to service layer, store result
        Townsperson registeredTownsperson = App.townspersonService.registerTownsperson(townsperson);

        //turn registered object to json, output result and status
        String outJson = gson.toJson(registeredTownsperson);
        ctx.status(201);
        ctx.result(outJson);

    }

}
