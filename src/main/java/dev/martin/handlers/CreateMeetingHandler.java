package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Meeting;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateMeetingHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //get body and turn into meeting
        String inJson = ctx.body();
        Gson gson = new Gson();
        Meeting meeting = gson.fromJson(inJson, Meeting.class);

        //Send meeting down to service layer, save result
        Meeting registeredMeeting = App.meetingService.registerMeeting(meeting);

        //turn registered meeting into json and output it with status 201
        String outJson = gson.toJson(registeredMeeting);
        ctx.status(201);
        ctx.result(outJson);
    }
}
