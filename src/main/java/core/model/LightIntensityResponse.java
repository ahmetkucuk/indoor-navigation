package core.model;

/**
 * Created by ahmet on 3/31/17.
 */
public class LightIntensityResponse {

    private String id;
    private String[] route;
    private int[] values;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getRoute() {
        return route;
    }

    public void setRoute(String[] route) {
        this.route = route;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }
}
