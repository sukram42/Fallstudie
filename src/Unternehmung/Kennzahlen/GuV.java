package Unternehmung.Kennzahlen;

import Unternehmung.Abteilungen.HR;
import Unternehmung.Abteilungen.Produktion;
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
    private float zinsaufwendungen;

    //Erlöse
    private float umsatzErlöse;

    private float jahresUeberschuss;



    public GuV(Unternehmen unternehmen) {
        this.unternehmen = unternehmen;
    }

    public float jahresUeberschussBerechnen(){
        this.jahresUeberschuss=(this.umsatzErlöse - (this.aufwendungenFuerEnergie + this.aufwendungenFuerGehaelter +
                this.aufwendungenFuerRohstoffe + this.aufwendungenFuerWerbung + this.zinsaufwendungen));
        float x = jahresUeberschuss;
        this.setAufwendungenFuerEnergie(0);
        this.setAufwendungenFuerGehaelter(0);
        this.setAufwendungenFuerWerbung(0);
        this.setJahresUeberschuss(0);
        this.setUmsatzErlöse(0);
        this.setZinsaufwendungen(0);
        return x;
    }

    public void importAufwandUndErlös()
    {
        aufwendungenFuerWerbung +=  unternehmen.getAbteilung("marketing").getKosten();
        aufwendungenFuerGehaelter += ((HR)unternehmen.getAbteilung("hr")).getTotalGehalt();
        aufwendungenFuerSozialeLeistungen +=unternehmen.getAbteilung("sozialeLeistungen").getKosten();
        Produktion produktion = (Produktion) unternehmen.getAbteilung("produktion");
        aufwendungenFuerRohstoffe += produktion.getTaeglicheHerstellkosten();
        aufwendungenFuerEnergie += produktion.getTaeglicheEnergiekosten();

        // TODO Umsatz ergänzen, wenn Sales-Klasse steht:
        //umsatzErlöse += ;
    }

    public float getTaeglicheLiquiditätsveränderung(){
        float kosten = 0;

        float umsatz = 0; // TODO Umsatz ergänzen, wenn Sales-Klasse steht:

        float werbekosten =  unternehmen.getAbteilung("marketing").getKosten();
        float gehälter = ((HR)unternehmen.getAbteilung("hr")).getTotalGehalt();
        float sozialeLeistungen =unternehmen.getAbteilung("sozialeLeistungen").getKosten();
        Produktion produktion = (Produktion) unternehmen.getAbteilung("produktion");
        float herstellkosten = produktion.getTaeglicheHerstellkosten();
        float energiekosten = produktion.getTaeglicheEnergiekosten();
        kosten = werbekosten + gehälter + sozialeLeistungen + herstellkosten + energiekosten;

        return umsatz - kosten;
    }

    // add-Methoden:
    public void addZinsaufwendungen(float zinsaufwendungen){
        this.zinsaufwendungen += zinsaufwendungen;
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

    public float getZinsaufwendungen() {
        return zinsaufwendungen;
    }

    public void setZinsaufwendungen(float zinsaufwendungen) {
        this.zinsaufwendungen = zinsaufwendungen;
    }
}
