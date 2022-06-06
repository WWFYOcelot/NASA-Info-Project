import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.util.ArrayList;

import org.json.*;
import com.google.gson.Gson;

public class main {

    public static void main(String[] args) throws IOException, InterruptedException {

        var planets = getData("https://exoplanetarchive.ipac.caltech.edu/TAP/sync?query=select+pl_name,pl_masse,ra,dec+from+ps+where+pl_masse+between+0.5+and+2.0&format=json");
        for (int i = 0; i < planets.getPlanets().size(); i++) {
            System.out.print(planets.getPlanets().get(i).getPl_name());
            System.out.print(" - Dec: " + planets.getPlanets().get(i).getDec());
            System.out.println();

            //github sucks flaccid cock
        }
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
}