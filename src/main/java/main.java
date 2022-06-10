import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.util.*;

import org.json.*;
import com.google.gson.Gson;

public class main {
    static Scanner sc = new Scanner(System.in);
    static Planets planets;
    static {
        try {
            planets = getData("https://exoplanetarchive.ipac.caltech.edu/TAP/sync?query=select+pl_name,disc_year,pl_masse,pl_rade,pl_orbper,pl_eqt,sy_snum,sy_pnum,sy_mnum,sy_dist+from+ps&format=json");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public main(){
    }

    public static void main(String[] args) throws IOException, InterruptedException {

    // https://exoplanetarchive.ipac.caltech.edu/TAP/sync?query=select
        start(planets);
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

    public static void start(Planets planets) throws IOException, InterruptedException {
        for (int i = 0; i < planets.getPlanets().size(); i++) {
            System.out.println(i+1 + ". " + planets.getPlanets().get(i).getPl_name());
        }
        System.out.println();
        System.out.println("Type a number to select that planet");
        int num = sc.nextInt();
        if (num < planets.getPlanets().size()) {
            display(planets.getPlanets().get(num));
        }
    }

    public static void display(Planet planet) throws IOException, InterruptedException {
        //pl_name,disc_year,pl_masse,pl_rade,pl_orbper,pl_eqt,sy_sum,sy_pnum,sy_mnum+from+ps&format=json");
        System.out.println();
        System.out.println("Chosen planet: " + planet.getPl_name());
        System.out.println("Press 1 for basic data");
        System.out.println("Press 2 for detailed data");
        System.out.println("Press 3 for solar data");
        System.out.println("Press 4 to go back");
        System.out.println("Press 5 to exit");
        int num = sc.nextInt();
        switch (num) {
            case 1:
                System.out.println("Discovery Year     Planetary Mass     Planetary Radius     Orbital Period     Temperature     System: # stars   # planets   # moons     System Distance");
                System.out.format("%d %21.5f %18.5f %23.5f %17f %15d %10d %10d %19f\n", planet.getDisc_year(), planet.getPl_masse(), planet.getPl_rade(), planet.getPl_orbper(), planet.getPl_eqt(), planet.getSy_snum(), planet.getSy_pnum(), planet.getSy_mnum(), planet.getSy_dist());
                display(planet);
                break;
            case 2:
                System.out.println("case 2");
                display(planet);
                break;
            case 3:
                getSolar(planet);
                System.out.println(planet.getSt_age());
                display(planet);
                break;
            case 4:
                start(planets);
                break;
            case 5:
                break;
        }
    }

    public static void getSolar(Planet planet) throws IOException, InterruptedException {
        Planets solarData = getData("https://exoplanetarchive.ipac.caltech.edu/TAP/sync?query=select+pl_name,st_spectype,st_teff,st_rad,st_mass,st_met,st_logg,st_age,st_dens+from+ps&format=json");
        for (int i = 0; i < solarData.getPlanets().size(); i++) {
            //st_spectype,st_teff,st_rad,st_mass,st_met,st_logg,st_age,st_dens
            if (solarData.getPlanets().get(i).getPl_name().equals(planet.getPl_name())) {
                planet.setSt_spectype(solarData.getPlanets().get(i).getSt_spectype());
                planet.setSt_teff(solarData.getPlanets().get(i).getSt_teff());
                planet.setSt_rad(solarData.getPlanets().get(i).getSt_rad());
                planet.setSt_mass(solarData.getPlanets().get(i).getSt_mass());
                planet.setSt_met(solarData.getPlanets().get(i).getSt_met());
                planet.setSt_logg(solarData.getPlanets().get(i).getSt_logg());
                planet.setSt_dens(solarData.getPlanets().get(i).getSt_dens());
                planet.setSt_age(solarData.getPlanets().get(i).getSt_age());
            }
        }
    }
}