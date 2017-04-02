/**
 * Created by ahmet on 3/31/17.
 */
import core.JsonTransformer;
import core.model.LightIntensityResponse;
import core.tinyos.Listen;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class Controller {

    private static final Listen listen = new Listen();

    public static void main(String[] args) {
        listen.startInBackground();
        staticFiles.location("/public");

        redirect.get("/", "/main.html");

        get("/api/data", (req, res) -> {

            System.out.println("in main");
            return listen.getValues();
        }, new JsonTransformer());

    }

}
