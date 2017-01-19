package Unternehmung;

/**
 * Created by oehlersj on 19.01.2017.
 */
public class Vertrag {
    private Produktlinie produktlinie;
    private float preis;
    private float strafe;
    private String kunde;

    public Vertrag(Produktlinie produktlinie, String kunde) {
        this.produktlinie = produktlinie;
        this.kunde = kunde;
        this.preis=0;
        this.strafe= 0;
    }


//getter und setter

    public Produktlinie getProduktlinie() {
        return produktlinie;
    }

    public void setProduktlinie(Produktlinie produktlinie) {
        this.produktlinie = produktlinie;
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }

    public float getStrafe() {
        return strafe;
    }

    public void setStrafe(float strafe) {
        this.strafe = strafe;
    }

    public String getKunde() {
        return kunde;
    }

    public void setKunde(String kunde) {
        this.kunde = kunde;
    }
}
