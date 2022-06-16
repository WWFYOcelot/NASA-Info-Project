import java.lang.reflect.Array;
import java.util.ArrayList;

public class Planet {
//    pl_name,pl_masse,ra,dec
    private double planetMass;
    static double earthMass = 5973600000000000000000000.0;
    static double earthTemp = 255.0;
    static double earthOrbitalDistance = 149598261.0;
    static double sunLuminosity = Math.pow(3.9, 1026);
    static double primeEccentricity = 0.000;
    static ArrayList<Double> earthStats;
    private ArrayList<Double> planetStats;
    private String pl_name;
    private int sy_snum;
    private int sy_pnum;
    private int sy_mnum;
    private int cb_flag;
    private String discovermethod;
    private int disc_year;
    private double pl_orbper;
    private double pl_rade;
    private float pl_masse;
    private float pl_dens;
    private float pl_orbeccen;
    private float pl_insol;
    private float pl_eqt;
    private float pl_orbincl;
    private String st_spectype;
    private float st_teff;
    private double st_rad;
    private double st_mass;
    private double st_met;
    private float st_logg;
    private double st_age;
    private float st_dens;
    private double sy_dist;
    private double pl_orbsmax;
    private float pl_ratdor;
    private float st_lum;
    private double sy_bmag;

    public ArrayList<Double> getEarthStats(){
        earthStats.add(earthTemp);
        earthStats.add(earthMass);
        earthStats.add(earthOrbitalDistance);
        earthStats.add(sunLuminosity);
        earthStats.add(primeEccentricity);
        return earthStats;
    }
    public ArrayList<Double> getPlanetStats(){
        planetStats.add((double)(getPl_eqt()));
        planetStats.add(getPlanetMass());
        planetStats.add(getPl_orbsmax());
        planetStats.add((double)(getSt_lum()));
        planetStats.add((double)(getPl_orbeccen()));
        return planetStats;
    }
    public double getPlanetMass(){
        return pl_masse*earthMass;
    }

    public String getPl_name() {
        return pl_name;
    }
    public void setPl_name(String pl_name) {
        this.pl_name = pl_name;
    }
    public float getPl_masse() {
        return pl_masse;
    }
    public void setPl_masse(float pl_masse) {
        this.pl_masse = pl_masse;
    }
    public int getSy_snum() {
        return sy_snum;
    }

    public double getSy_bmag() {

        return sy_bmag;
    }

    public void setSy_bmag(double sy_bmag) {
        this.sy_bmag = sy_bmag;
    }

    public float getSt_lum() {
        return st_lum;
    }

    public void setSt_lum(float st_lum) {
        this.st_lum = st_lum;
    }

    public float getPl_ratdor() {
        return pl_ratdor;
    }

    public void setPl_ratdor(float pl_ratdor) {
        this.pl_ratdor = pl_ratdor;
    }

    public double getPl_orbsmax() {
        return pl_orbsmax;
    }

    public void setPl_orbsmax(double pl_orbsmax) {
        this.pl_orbsmax = pl_orbsmax;
    }

    public double getSy_dist() {
        return sy_dist;
    }

    public void setSy_dist(double sy_dist) {
        this.sy_dist = sy_dist;
    }

    public float getSt_dens() {
        return st_dens;
    }

    public void setSt_dens(float st_dens) {
        this.st_dens = st_dens;
    }

    public double getSt_age() {
        return st_age;
    }

    public void setSt_age(double st_age) {
        this.st_age = st_age;
    }

    public float getSt_logg() {
        return st_logg;
    }

    public void setSt_logg(float st_logg) {
        this.st_logg = st_logg;
    }

    public double getSt_met() {
        return st_met;
    }

    public void setSt_met(double st_met) {
        this.st_met = st_met;
    }

    public double getSt_mass() {
        return st_mass;
    }

    public void setSt_mass(double st_mass) {
        this.st_mass = st_mass;
    }

    public double getSt_rad() {
        return st_rad;
    }

    public void setSt_rad(double st_rad) {
        this.st_rad = st_rad;
    }

    public float getSt_teff() {
        return st_teff;
    }

    public void setSt_teff(float st_teff) {
        this.st_teff = st_teff;
    }

    public String getSt_spectype() {
        return st_spectype;
    }

    public void setSt_spectype(String st_spectype) {
        this.st_spectype = st_spectype;
    }

    public float getPl_orbincl() {
        return pl_orbincl;
    }

    public void setPl_orbincl(float pl_orbincl) {
        this.pl_orbincl = pl_orbincl;
    }

    public float getPl_eqt() {
        return pl_eqt;
    }

    public void setPl_eqt(float pl_eqt) {
        this.pl_eqt = pl_eqt;
    }

    public float getPl_insol() {
        return pl_insol;
    }

    public void setPl_insol(float pl_insol) {
        this.pl_insol = pl_insol;
    }

    public float getPl_orbeccen() {
        return pl_orbeccen;
    }

    public void setPl_orbeccen(float pl_orbeccen) {
        this.pl_orbeccen = pl_orbeccen;
    }

    public float getPl_dens() {
        return pl_dens;
    }

    public void setPl_dens(float pl_dens) {
        this.pl_dens = pl_dens;
    }

    public double getPl_rade() {
        return pl_rade;
    }

    public void setPl_rade(double pl_rade) {
        this.pl_rade = pl_rade;
    }

    public double getPl_orbper() {
        return pl_orbper;
    }

    public void setPl_orbper(double pl_orbper) {
        this.pl_orbper = pl_orbper;
    }

    public int getDisc_year() {
        return disc_year;
    }

    public void setDisc_year(int disc_year) {
        this.disc_year = disc_year;
    }

    public String getDiscovermethod() {
        return discovermethod;
    }

    public void setDiscovermethod(String discovermethod) {
        this.discovermethod = discovermethod;
    }

    public int getCb_flag() {
        return cb_flag;
    }

    public void setCb_flag(int cb_flag) {
        this.cb_flag = cb_flag;
    }

    public int getSy_mnum() {
        return sy_mnum;
    }

    public void setSy_mnum(int sy_mnum) {
        this.sy_mnum = sy_mnum;
    }

    public void setSy_snum(int sy_snum) {
        this.sy_snum = sy_snum;
    }
    public int getSy_pnum() {
        return sy_pnum;
    }
    public void setSy_pnum(int sy_pnum) {
        this.sy_pnum = sy_pnum;
    }

    public double gradeStat(double earthStat, double planetStat){
        double stat;
        stat = (earthStat - planetStat)/earthStat;
        if(stat < 0){
            stat*=-1;
        }
        return stat;
    }
    public ArrayList<Double> earthData(){
        ArrayList<Double> earthStats = new ArrayList<Double>();
        earthStats.add(earthMass);
        earthStats.add(earthTemp);
        earthStats.add(earthOrbitalDistance);
        earthStats.add(sunLuminosity);
        earthStats.add(primeEccentricity);
        return earthStats;
    }
    public ArrayList<Double> importantPlanetStats(){
        ArrayList<Double> stats = new ArrayList<Double>();
            if(getPlanetMass() > 0){
                stats.add(getPlanetMass());
            }
            else if(getPlanetMass() == 0){
                stats.add(-1.0);
            }
            if(getPl_eqt() > 0){
                stats.add((double)(getPl_eqt()));
            }
            else if(getPl_eqt() == 0){
                stats.add(-1.0);
            }
            if(getPl_orbsmax() > 0){
                stats.add(getPl_orbsmax());
            }
            else if(getPl_orbsmax() == 0){
                stats.add(-1.0);
            }
            if(getSt_lum() > 0){
                stats.add((double)(getSt_lum()));
            }
            else if(getSt_lum() == 0){
                stats.add(-1.0);
            }
            if(getPl_orbeccen() > 0){
                stats.add((double)(getPl_orbeccen()));
            }
            else if(getPl_orbeccen() == 0){
                stats.add(-1.0);
            }
        return stats;
    }

    public ArrayList<Double> planetStatGrades(){
        ArrayList<Double> grades = new ArrayList<Double>();
        for(int i = 0; i < importantPlanetStats().size(); i ++){
            if(importantPlanetStats().get(i) != -1.0) {
                grades.add(gradeStat(earthData().get(i), importantPlanetStats().get(i)));
            }
            else if(importantPlanetStats().get(i) == -1.0){
                grades.add(-1.0);
            }
        }
        return grades;
    }

    public double lifePredictor(){
        ArrayList<Double> weight = new ArrayList<Double>();
        double denomenator = 0.0;
        double numerator = 0.0;
        for(int i = 0; i < planetStatGrades().size(); i++){
            weight.add(0.20 + 1.0 - planetStatGrades().get(i));
        }
        for(int i = 0; i < planetStatGrades().size(); i++){
            numerator += planetStatGrades().get(i)*weight.get(i);
            denomenator += weight.get(i);
        }
        return numerator/denomenator;
    }

}
