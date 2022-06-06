import java.util.ArrayList;

public class Planet {
//    pl_name,pl_masse,ra,dec
    private String pl_name;
    private float pl_masse;
    private double ra;
    private double dec;

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
    public double getRa() {
        return ra;
    }
    public void setRa(double ra) {
        this.ra = ra;
    }
    public double getDec() {
        return dec;
    }
    public void setDec(double dec) {
        this.dec = dec;
    }
}
