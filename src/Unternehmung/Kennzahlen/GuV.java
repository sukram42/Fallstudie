package Unternehmung.Kennzahlen;

import Rules.Game;
import Unternehmung.Abteilungen.HR;
import Unternehmung.Abteilungen.Produktion;
import Unternehmung.Unternehmen;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by oehlersj on 13.01.2017.
 */
public class GuV {

    private transient Unternehmen unternehmen;

    private transient Queue<Map.Entry> archiv = new LinkedList<>(); // Sammlung der GuV's am Monatsende
    private float aufwendungenArchiv;
    private float erloeseArchiv;

    //Aufwendungen
    private float aufwendungenFuerRohstoffe; //alle Produktionskosten
    private float aufwendungenFuerWerbung;
    private float aufwendungenFuerGehaelter;
    private float aufwendungenFuerEnergie;
    private float aufwendungenFuerSozialeLeistungen;
    private float zinsaufwendungen;
    private float fremdinstandhaltung;
    private float geleisteterSchadensersatz;

    //Erlöse
    private float umsatzErloese;

    private float jahresUeberschuss;



    public GuV(Unternehmen unternehmen) {
        this.unternehmen = unternehmen;
    }

    /**
     * Konsturktor zum archivieren der Aufwendungen und Erlöse
     * @param zuKopieren aktuelle GuV, um Aufwendungen und Erlöse zu kopieren und zu archivieren
     */
    private GuV(GuV zuKopieren) {
        this.aufwendungenArchiv = zuKopieren.getAufwendungenArchiv();
        this.erloeseArchiv = zuKopieren.getErloeseArchiv();
    }

    public void jahresabschluss(Bilanz bilanz){
        this.setJahresUeberschuss(0);
        this.jahresUeberschuss=(this.umsatzErloese - (this.aufwendungenFuerEnergie + this.aufwendungenFuerGehaelter +
                this.aufwendungenFuerRohstoffe + this.aufwendungenFuerWerbung + this.zinsaufwendungen + this.fremdinstandhaltung));
        this.setAufwendungenFuerEnergie(0);
        this.setAufwendungenFuerGehaelter(0);
        this.setAufwendungenFuerWerbung(0);
        this.setUmsatzErloese(0);
        this.setZinsaufwendungen(0);
        this.setFremdinstandhaltung(0);
        bilanz.eigenkapitalAnpassen(this.jahresUeberschuss);
    }

    /**
     * holt sich die täglichen Kosten von den einzelnen Abteilungen und fügt sie der GuV hinzu
     */
    public void importAufwandUndErlös()
    {
        aufwendungenFuerWerbung +=  unternehmen.getAbteilung("marketing").getKosten();
        aufwendungenFuerGehaelter += ((HR)unternehmen.getAbteilung("hr")).getTotalGehalt();
        aufwendungenFuerSozialeLeistungen +=unternehmen.getAbteilung("sozialeLeistungen").getKosten();
        Produktion produktion = (Produktion) unternehmen.getAbteilung("produktion");
        aufwendungenFuerRohstoffe += produktion.getTaeglicheHerstellkosten();
        aufwendungenFuerEnergie += produktion.getTaeglicheEnergiekosten();
    }

    /**
     * wird von der update()-Methode der Kennzahlensammlung aufgerufen
     * @return tägliche Liquiditätsveränderung
     */
    public float getTaeglicheLiquiditätsveränderung(){
        float kosten;
        float gehälter = 0;
        float umsatz = 0; // TODO Umsatz ergänzen, wenn Sales-Klasse steht:

        float werbekosten =  unternehmen.getAbteilung("marketing").getKosten();
        float sozialeLeistungen =unternehmen.getAbteilung("sozialeLeistungen").getKosten();
        Produktion produktion = (Produktion) unternehmen.getAbteilung("produktion");
        float herstellkosten = produktion.getTaeglicheHerstellkosten();
        float energiekosten = produktion.getTaeglicheEnergiekosten();
        if (Game.getCalendar().get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)) { // Gehälter nur einmal im Monat (am 26. jeden Monats):
            gehälter = ((HR)unternehmen.getAbteilung("hr")).getTotalGehalt();
        }
        kosten = werbekosten + gehälter + sozialeLeistungen + herstellkosten + energiekosten;
        this.aufwendungenArchiv += kosten;
        this.erloeseArchiv += umsatz;
        return umsatz - kosten;
    }

    /**
     * wird von Kennzahlensammlung.update() am Monatsende aufgerufen
     * klont die GuV, zieht alle vorherigen Kosten und Erlöse ab legt sie in archiv ab
     */
    public void archivieren(){
        GuV archivGuV = new GuV(this);
//        this.archiv.put(Game.getCalendar().getTime(), archivGuV);
        if(archiv.size()==5)
            archiv.remove();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMMM yyyy");
        Map.Entry<Date,GuV> entry = new AbstractMap.SimpleEntry(sdf.format(Game.getCalendar().getTime()), archivGuV);
        archiv.add(entry);

        this.aufwendungenArchiv = 0;
        this.umsatzErloese = 0;
    }

    // add-Methoden (werden bei Zahlungen aufgerufen -> Alternative zu Abteilung.getKosten()):
    public void addZinsaufwendungen(float zinsaufwendungen){
        this.zinsaufwendungen += zinsaufwendungen;
        this.aufwendungenArchiv += zinsaufwendungen;
    }

    public void addFremdinstandhaltung(float fremdinstandhaltung){
        this.fremdinstandhaltung += fremdinstandhaltung;
        this.aufwendungenArchiv += fremdinstandhaltung;
    }

    public void addUmsatz(float umsatz){
        this.umsatzErloese += umsatz;
        this.erloeseArchiv += umsatz;
    }

    public void addGeleisteterSchandsersatz(float schadensersatz){
        this.geleisteterSchadensersatz = schadensersatz;
        this.aufwendungenArchiv += schadensersatz;
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

    public float getUmsatzErloese() {
        return umsatzErloese;
    }

    public void setUmsatzErloese(float umsatzErloese) {
        this.umsatzErloese = umsatzErloese;
    }

    public float getJahresUeberschuss() {
        return jahresUeberschuss;
    }

    public void setJahresUeberschuss(float jahresUeberschuss) {
        this.jahresUeberschuss = jahresUeberschuss;
    }

    public float getZinsaufwendungen() {
        return zinsaufwendungen;
    }

    public void setZinsaufwendungen(float zinsaufwendungen) {
        this.zinsaufwendungen = zinsaufwendungen;
    }

    public float getFremdinstandhaltung() {
        return fremdinstandhaltung;
    }

    public void setFremdinstandhaltung(float fremdinstandhaltung) {
        this.fremdinstandhaltung = fremdinstandhaltung;
    }

    public float getGeleisteterSchadensersatz() {
        return geleisteterSchadensersatz;
    }

    public void setGeleisteterSchadensersatz(float geleisteterSchadensersatz) {
        this.geleisteterSchadensersatz = geleisteterSchadensersatz;
    }

    public Unternehmen getUnternehmen() {
        return unternehmen;
    }

    public float getAufwendungenArchiv() {
        return aufwendungenArchiv;
    }

    public void setAufwendungenArchiv(float aufwendungenArchiv) {
        this.aufwendungenArchiv = aufwendungenArchiv;
    }

    public float getErloeseArchiv() {
        return erloeseArchiv;
    }

    public void setErloeseArchiv(float erloeseArchiv) {
        this.erloeseArchiv = erloeseArchiv;
    }

    public Queue<Map.Entry> getArchiv() {
        return archiv;
    }

}
