import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.util.*;
import javax.swing.*;

import org.json.*;
import com.google.gson.Gson;

public class main {
    static Scanner sc = new Scanner(System.in);
    static Planets planets;
    static {
        try {
            planets = getAllData("https://exoplanetarchive.ipac.caltech.edu/TAP/sync?query=select+pl_name+from+ps&format=json");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }



    public main(){
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        start();
    }

    public static String formatName(String a) {
        String[] b = a.split(" ");
        for (int i = 0; i < b.length-1; i++) {
            b[i] += "%20";
        }
        b[0] = "%27" + b[0];
        b[b.length-1] += "%27";
        String d = "";
        for (String c:b) {
            d += c;
        }
    return d;
    }

    public static Planet getData(String a) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(a))
                .header("accept", "application/json").build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONArray arr = new JSONArray(response.body());

        Planet planet = new Gson().fromJson(arr.get(0).toString(), Planet.class);
        return planet;
    }

    public static Planets getAllData(String a) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(a))
                .header("accept", "application/json").build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONArray arr = new JSONArray(response.body());

        ArrayList<Planet> planet = new ArrayList<>();
        Planets planets = new Planets();
        planets.setPlanets(planet);
        for (int i = 0; i < arr.length(); i++) {
            planet.add(new Gson().fromJson(arr.get(i).toString(), Planet.class));
        }
        return planets;
    }

    public static void start() throws IOException, InterruptedException {
        System.out.println("WELCOME TO THE PLANETARY DATA DISPLAY");
        System.out.println("Press 1 to choose a planet by name");
        System.out.println("Press 2 to see a list of all planets");
        System.out.println("Press 3 to exit");
        int num = sc.nextInt();
        sc.nextLine();
        switch(num) {
            case 1:
                System.out.println("Type the name of a planet");
                String name = sc.nextLine();
                for (int i = 0; i < planets.getPlanets().size(); i++){
                    if (planets.getPlanets().get(i).getPl_name().equals(name)) {
                        display(planets.getPlanets().get(i));
                    }
                }
            case 2:
                menu(planets);
                break;
            case 3:
                break;
        }
    }

    public static void menu(Planets planets) throws IOException, InterruptedException {
        for (int i = 0; i < planets.getPlanets().size(); i++) {
            System.out.println(i+1 + ". " + planets.getPlanets().get(i).getPl_name());
        }
        System.out.println();
        System.out.println("Type a number to select that planet");
        int num = sc.nextInt();
        if (num <= planets.getPlanets().size()) {
            display(planets.getPlanets().get(num - 1));
        }
    }

    public static void display(Planet planet) throws IOException, InterruptedException {
        //pl_name,disc_year,pl_masse,pl_rade,pl_orbper,pl_eqt,sy_sum,sy_pnum,sy_mnum+from+ps&format=json");
        System.out.println();
        System.out.println("Chosen planet: " + planet.getPl_name());
        System.out.println("Press 1 for basic data");
        System.out.println("Press 2 for detailed data");
        System.out.println("Press 3 for solar data");
        System.out.println("Press 4 to go to planet list");
        System.out.println("Press 5 to return to start screen");
        System.out.println("Press 6 to exit");
        int num = sc.nextInt();
        switch (num) {
            case 1:
                getDefault(planet);
                System.out.println("Discovery Year     Planetary Mass     Planetary Radius     Orbital Period     Temperature     System: # stars   # planets   # moons     System Distance");
                System.out.format("%d %21.3f %18.3f %23.3f %17.3f %15d %10d %10d %19.3f\n", planet.getDisc_year(), planet.getPl_masse(), planet.getPl_rade(), planet.getPl_orbper(), planet.getPl_eqt(), planet.getSy_snum(), planet.getSy_pnum(), planet.getSy_mnum(), planet.getSy_dist());
                display(planet);
                break;
            case 2:
                getExtended(planet);
                System.out.println("Eccentricity   Insolation Flux   Inclination   Max Orbit Radius   Mid-Transit to Stellar Radius Ratio");
                System.out.format("%f %15.3f %17.3f %14.3f %19.3f\n", planet.getPl_orbeccen(), planet.getPl_insol(), planet.getPl_orbincl(), planet.getPl_orbsmax(), planet.getPl_ratdor());
                display(planet);
                break;
            case 3:
                getSolar(planet);
                System.out.println("Spectral Type   Temperature   Solar Radius   Solar Mass   Stellar Metallicity   Surface Gravity   Stellar Density   Stellar Age");
                System.out.format("%s %19.3f %13.3f %14.3f %12.3f %21.3f %17.3f %17.3f\n", planet.getSt_spectype(), planet.getSt_teff(), planet.getSt_rad(), planet.getSt_mass(), planet.getSt_met(), planet.getSt_logg(), planet.getSt_dens(), planet.getSt_age());
                display(planet);
                break;
            case 4:
                menu(planets);
                break;
            case 5:
                start();
                break;
            case 6:
                break;
        }
    }


    public static void getDefault(Planet planet) throws IOException, InterruptedException {
        Planet defaultData = getData("https://exoplanetarchive.ipac.caltech.edu/TAP/sync?query=select+pl_name,disc_year,pl_masse,pl_rade,pl_orbper,pl_eqt,sy_snum,sy_pnum,sy_mnum,sy_dist+from+ps+where+pl_name+=+"+formatName(planet.getPl_name())+"&format=json");
        planet.setDisc_year(defaultData.getDisc_year());
        planet.setPl_masse(defaultData.getPl_masse());
        planet.setPl_rade(defaultData.getPl_rade());
        planet.setPl_orbper(defaultData.getPl_orbper());
        planet.setPl_eqt(defaultData.getPl_eqt());
        planet.setSy_snum(defaultData.getSy_snum());
        planet.setSy_pnum(defaultData.getSy_pnum());
        planet.setSy_mnum(defaultData.getSy_mnum());
        planet.setSy_dist(defaultData.getSy_dist());
    }

    public static void getSolar(Planet planet) throws IOException, InterruptedException {
        Planet solarData = getData("https://exoplanetarchive.ipac.caltech.edu/TAP/sync?query=select+st_spectype,st_teff,st_rad,st_mass,st_met,st_logg,st_dens,st_age+from+ps+where+pl_name+=+"+formatName(planet.getPl_name())+"&format=json");
        planet.setSt_spectype(solarData.getSt_spectype());
        planet.setSt_teff(solarData.getSt_teff());
        planet.setSt_rad(solarData.getSt_rad());
        planet.setSt_mass(solarData.getSt_mass());
        planet.setSt_met(solarData.getSt_met());
        planet.setSt_logg(solarData.getSt_logg());
        planet.setSt_dens(solarData.getSt_dens());
        planet.setSt_age(solarData.getSt_age());

    }

    public static void getExtended(Planet planet) throws IOException, InterruptedException {
        Planet extended = getData("https://exoplanetarchive.ipac.caltech.edu/TAP/sync?query=select+pl_orbeccen,pl_insol,pl_orbincl,pl_orbsmax,pl_ratdor+from+ps+where+pl_name+=+"+formatName(planet.getPl_name())+"&format=json");
        planet.setPl_orbeccen(extended.getPl_orbeccen());
        planet.setPl_insol(extended.getPl_insol());
        planet.setPl_orbincl(extended.getPl_orbincl());
        planet.setPl_orbsmax(extended.getPl_orbsmax());
        planet.setPl_ratdor(extended.getPl_ratdor());
    }
        public static void testing() {
        Planet pl1 = new Planet(1.0f, 255.0f, 149598261.0, (float) (Math.pow(3.8, 10)), 0.00001f);
        planets.getPlanets().add(0, pl1);
        for (int i = 0; i < planets.getPlanets().get(0).importantPlanetStats().size(); i++) {
            System.out.println(planets.getPlanets().get(0).importantPlanetStats().get(i));
        }
        for (int i = 0; i < planets.getPlanets().get(0).planetStatGrades().size(); i++) {
            System.out.println(planets.getPlanets().get(0).planetStatGrades().get(i));
        }
        System.out.println(planets.getPlanets().get(0).lifePredictor());
    }

    public static void planetStatTable(int listSize) {
        JFrame jFrame = new JFrame();
        ArrayList<Integer> planetList = new ArrayList<Integer>();
        for(int i = 0; i < listSize; i ++){
            System.out.println("New Planet Location");
            planetList.add(sc.nextInt());
        }
        String[][] tableData = new String[planetList.size()-1][5];
        for (int i = 0; i < tableData.length; i++) {
            for (int j = 0; j < tableData[i].length; j++) {
                tableData[i][j] = toString(planets.getPlanets().get(planetList.get(i)).importantPlanetStats().get(j));
            }
        }

        String[] tableColumn = {"Mass", "Temp", "Orbital Distance", "Local Stellar Luminosity", "Eccentricity"};

        JTable jTable = new JTable(tableData, tableColumn);

        jTable.setBounds(30, 40, 230, 280);

        JScrollPane jScrollPane = new JScrollPane(jTable);
        jFrame.add(jScrollPane);
        jFrame.setSize(350, 300);
        jFrame.setVisible(true);
    }
    public static String toString(double num){
        String string = "";
        return string + num;
    }
}
