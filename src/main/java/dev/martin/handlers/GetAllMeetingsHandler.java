package dev.martin.handlers;

import com.google.gson.Gson;
import dev.martin.app.App;
import dev.martin.entities.Meeting;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetAllMeetingsHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        //Get list of all meetings from service layer, then turn into Json
        List<Meeting> meetingList = App.meetingService.retrieveAllMeetings();
        Gson gson = new Gson();
        String outJson =gson.toJson(meetingList);

        //output list in Json form
        ctx.status(200);
        ctx.result(outJson);

    }
}
