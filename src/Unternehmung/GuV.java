package Unternehmung;

/**
 * Created by oehlersj on 13.01.2017.
 */
public class GuV {

    //Aufwendungen
    private double AufwendungenFürRohstoffe; //alle Produktionskosten
    private double AufwendungenFürWerbung;
    private double AufwendungenFürGehaelter;
    private double AufwendungenFürEnergie;

    //Erlöse
    private double UmsatzErlöse;

    public double jahresUeberschuss;

    public GuV() {
    }

    public double jahresUeberschussBerechnen(){
        this.jahresUeberschuss=(this.UmsatzErlöse - (this.AufwendungenFürEnergie + this.AufwendungenFürGehaelter + this.AufwendungenFürRohstoffe + this.AufwendungenFürWerbung));
        return jahresUeberschuss;
    }

    //getter und setter

    public double getAufwendungenFürWerbung() {
        return AufwendungenFürWerbung;
    }

    public void setAufwendungenFürWerbung(double aufwendungenFürWerbung) {
        AufwendungenFürWerbung = aufwendungenFürWerbung;
    }

    public double getAufwendungenFürGehaelter() {
        return AufwendungenFürGehaelter;
    }

    public void setAufwendungenFürGehaelter(double aufwendungenFürGehaelter) {
        AufwendungenFürGehaelter = aufwendungenFürGehaelter;
    }

    public double getAufwendungenFürEnergie() {
        return AufwendungenFürEnergie;
    }

    public void setAufwendungenFürEnergie(double aufwendungenFürEnergie) {
        AufwendungenFürEnergie = aufwendungenFürEnergie;
    }

    public double getUmsatzErlöse() {
        return UmsatzErlöse;
    }

    public void setUmsatzErlöse(double umsatzErlöse) {
        UmsatzErlöse = umsatzErlöse;
    }

    public double getJahresUeberschuss() {
        return jahresUeberschuss;
    }

    public void setJahresUeberschuss(double jahresUeberschuss) {
        jahresUeberschuss = jahresUeberschuss;
    }
}
