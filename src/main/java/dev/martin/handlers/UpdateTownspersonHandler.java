package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Townsperson;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateTownspersonHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        int id = Integer.parseInt(ctx.pathParam("id"));

        //convert json into townsperson, set id to match with path
        String inJson = ctx.body();
        Gson gson = new Gson();
        Townsperson townsperson = gson.fromJson(inJson, Townsperson.class);
        townsperson.setTownId(id);

        Townsperson modifiedTownsperson = App.townspersonService.modifyTownsperson(townsperson);
        String outJson = gson.toJson(modifiedTownsperson);
        ctx.result(outJson);


    }

}
