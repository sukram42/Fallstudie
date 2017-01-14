package Unternehmung.Kennzahlen;

import Unternehmung.Abteilungen.HR;
import Unternehmung.Unternehmen;

/**
 * Created by oehlersj on 13.01.2017.
 */
public class GuV {

    private transient Unternehmen unternehmen;

    //Aufwendungen
    private float aufwendungenFuerRohstoffe; //alle Produktionskosten
    private float aufwendungenFuerWerbung;
    private float aufwendungenFuerGehaelter;
    private float aufwendungenFuerEnergie;
    private float aufwendungenFuerSozialeLeistungen;

    //Erlöse
    private float umsatzErlöse;

    private float jahresUeberschuss;



    public GuV(Unternehmen unternehmen) {
        this.unternehmen = unternehmen;
    }

    public float jahresUeberschussBerechnen(){
        this.jahresUeberschuss=(this.umsatzErlöse - (this.aufwendungenFuerEnergie + this.aufwendungenFuerGehaelter + this.aufwendungenFuerRohstoffe + this.aufwendungenFuerWerbung));
        return jahresUeberschuss;
    }

    public void importAufwand()
    {
        aufwendungenFuerWerbung +=  unternehmen.getAbteilung("marketing").getKosten();
        aufwendungenFuerGehaelter += ((HR)unternehmen.getAbteilung("hr")).getTotalGehalt();
        aufwendungenFuerSozialeLeistungen +=unternehmen.getAbteilung("sozialeLeistungen").getKosten();

        //TODO den Rest noch
    }


    //getter und setter

    public float getAufwendungenFuerWerbung() {
        return aufwendungenFuerWerbung;
    }

    public void setAufwendungenFuerWerbung(float aufwendungenFuerWerbung) {
        this.aufwendungenFuerWerbung = aufwendungenFuerWerbung;
    }

    public float getAufwendungenFuerGehaelter() {
        return aufwendungenFuerGehaelter;
    }

    public void setAufwendungenFuerGehaelter(float aufwendungenFuerGehaelter) {
        this.aufwendungenFuerGehaelter = aufwendungenFuerGehaelter;
    }

    public float getAufwendungenFuerEnergie() {
        return aufwendungenFuerEnergie;
    }

    public void setAufwendungenFuerEnergie(float aufwendungenFuerEnergie) {
        this.aufwendungenFuerEnergie = aufwendungenFuerEnergie;
    }

    public float getUmsatzErlöse() {
        return umsatzErlöse;
    }

    public void setUmsatzErlöse(float umsatzErlöse) {
        this.umsatzErlöse = umsatzErlöse;
    }

    public float getJahresUeberschuss() {
        return jahresUeberschuss;
    }

    public void setJahresUeberschuss(float jahresUeberschuss) {
        jahresUeberschuss = jahresUeberschuss;
    }
}
