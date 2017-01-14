package Unternehmung.Kennzahlen;

/**
 * Created by oehlersj on 13.01.2017.
 */
public class GuV {

    //Aufwendungen
    private double aufwendungenFürRohstoffe; //alle Produktionskosten
    private double aufwendungenFürWerbung;
    private double aufwendungenFürGehaelter;
    private double aufwendungenFürEnergie;

    //Erlöse
    private double umsatzErlöse;

    private double jahresUeberschuss;

    public GuV() {
    }

    public double jahresUeberschussBerechnen(){
        this.jahresUeberschuss=(this.umsatzErlöse - (this.aufwendungenFürEnergie + this.aufwendungenFürGehaelter + this.aufwendungenFürRohstoffe + this.aufwendungenFürWerbung));
        return jahresUeberschuss;
    }

    //getter und setter

    public double getAufwendungenFürWerbung() {
        return aufwendungenFürWerbung;
    }

    public void setAufwendungenFürWerbung(double aufwendungenFürWerbung) {
        this.aufwendungenFürWerbung = aufwendungenFürWerbung;
    }

    public double getAufwendungenFürGehaelter() {
        return aufwendungenFürGehaelter;
    }

    public void setAufwendungenFürGehaelter(double aufwendungenFürGehaelter) {
        this.aufwendungenFürGehaelter = aufwendungenFürGehaelter;
    }

    public double getAufwendungenFürEnergie() {
        return aufwendungenFürEnergie;
    }

    public void setAufwendungenFürEnergie(double aufwendungenFürEnergie) {
        this.aufwendungenFürEnergie = aufwendungenFürEnergie;
    }

    public double getUmsatzErlöse() {
        return umsatzErlöse;
    }

    public void setUmsatzErlöse(double umsatzErlöse) {
        this.umsatzErlöse = umsatzErlöse;
    }

    public double getJahresUeberschuss() {
        return jahresUeberschuss;
    }

    public void setJahresUeberschuss(double jahresUeberschuss) {
        jahresUeberschuss = jahresUeberschuss;
    }
}
