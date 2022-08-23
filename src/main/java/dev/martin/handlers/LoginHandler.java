package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.dtos.LoginCredentials;
import dev.martin.entities.Townsperson;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class LoginHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        String body = ctx.body();
        Gson gson = new Gson();
        LoginCredentials credentials = gson.fromJson(body, LoginCredentials.class);

        Townsperson townsperson = App.loginService.validate_user(credentials.getUsername(), credentials.getPassword());
        String outJson = gson.toJson(townsperson);
        ctx.result(outJson);

    }
}
