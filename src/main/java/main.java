import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.util.*;

import org.json.*;
import com.google.gson.Gson;

public class main {
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {

    // https://exoplanetarchive.ipac.caltech.edu/TAP/sync?query=select
        Planets planetDataA = getData("https://exoplanetarchive.ipac.caltech.edu/TAP/sync?query=select+pl_name+from+ps&format=json");
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