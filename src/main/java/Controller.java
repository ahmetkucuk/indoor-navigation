/**
 * Created by ahmet on 3/31/17.
 */
import core.JsonTransformer;
import core.tinyos.Listen;

import static spark.Spark.*;

public class Controller {

    private static final Listen listen = new Listen();

    public static void main(String[] args) {
        staticFiles.location("/public");

        redirect.get("/", "/main.html");

        get("/api/reset", (req, res) -> {
            listen.resetValues();
            return "Succeed";
        });

        get("/api/data", (req, res) -> {
            return listen.getLightIntesityResponse();
        }, new JsonTransformer());

    }

}
