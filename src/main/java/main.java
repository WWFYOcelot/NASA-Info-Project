import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.util.*;

import org.json.*;
import com.google.gson.Gson;

public class main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {

    // https://exoplanetarchive.ipac.caltech.edu/TAP/sync?query=select
        Planets planetDataA = getData("https://exoplanetarchive.ipac.caltech.edu/TAP/sync?query=select+pl_name+from+ps&format=json");
        start(planetDataA);
    }

    public static Planets getData(String a) throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(URI.create(a))
                .header("accept", "application/json").build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONArray arr = new JSONArray(response.body());

        ArrayList<Planet> planet = new ArrayList<Planet>();
        Planets planets = new Planets();
        planets.setPlanets(planet);
        for (int i = 0; i < arr.length(); i++) {
            planet.add(new Gson().fromJson(arr.get(i).toString(), Planet.class));
        }
        return planets;
    }

    public static void start(Planets planets) {
        for (int i = 0; i < planets.getPlanets().size(); i++) {
            System.out.println(planets.getPlanets().get(i).getPl_name());
        }
        String name = sc.nextLine();
        boolean work = false;
        for (int i = 0; i < planets.getPlanets().size(); i++) {
            if (planets.getPlanets().get(i).getPl_name().equalsIgnoreCase(name)) {
                work = true;
            }
        }
        if (work) {
            System.out.println("work");
        }

    }
}