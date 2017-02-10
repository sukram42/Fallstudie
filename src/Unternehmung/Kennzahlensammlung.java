package Unternehmung;

import Exceptions.BankruptException;
import Rules.Game;
import Unternehmung.Kennzahlen.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Diese Klasse beinhaltet alle Kennzahlensammlung eines Unternehmens
 * diese werden unterschieden in "weiche" und faktische Kennzahlensammlung
 * zur Berechnung weicher Kennzahlensammlung wird die Klasse "Kennzahl" genutzt, faktische Kennzahlensammlung werden in double-Werten gespeichert
 * Created by lucadommes on 02.01.2017.
 */
public class Kennzahlensammlung {

    private transient Unternehmen unternehmen;

    private Map<Date, Kennzahlensammlung> archiv = new HashMap<>(); // Archiv für Jahresabschlüsse (Bilanz + GuV)

    // "weiche" Kennzahlensammlung:
    private Map<String, Kennzahl> weicheKennzahlen = new HashMap<>();

    // faktische Kennzahlensammlung:
    private double marktanteil;

    private Bilanz bilanz;
    private GuV guv;

    private transient int maxNeueMitarbeiter; // abhängig von HR-Mitarbeitern (für 10 Mitarbeiter ist ein HR-Mitarbeiter (=Manager) zuständig)
                                            // wird pro neu eingestelltem HR-Mitarbeiter um 10 hochgesetzt
                                            // wird pro neu eingestelltem (Nicht-HR-) Mitarbeiter um 1 runtergesetzt
    private boolean bankrupt = false;

    /**
     * Konstruktor zum Erstellen einen Kennzahlenobjekts eines Unternehmens (wird im Unternehmenskonstruktor aufgerufen)
     * @param eigenkapital muss bei Gründung des Unternehmens definiert werden
     */
    public Kennzahlensammlung(Unternehmen unternehmen,float eigenkapital) {
        // TODO alle Defaultwerte definieren (zumindest solche, die nicht 0 sein sollen)
        this.guv = new GuV(unternehmen);
        this.bilanz = new Bilanz(unternehmen);
        this.getBilanz().setEigenkapital(eigenkapital);
        this.getBilanz().setLiquideMittel(eigenkapital);
        this.unternehmen = unternehmen;
        this.maxNeueMitarbeiter = 0;
    }

    /**
     * Konstruktor ausschließlich für Archivierung des Jahresabschlusses (siehe archivieren())
     * @param zuKopieren aktuelle Kennzahlensammlung, die dann im archiv abgelegt wird
     */
    private Kennzahlensammlung(Kennzahlensammlung zuKopieren) {
        this.bilanz = zuKopieren.getBilanz();
        this.guv = zuKopieren.getGuv();
    }

    // Berechnungen:
    public void berechnen()
    {
        for(Kennzahl kennzahl  : weicheKennzahlen.values())
        {
            kennzahl.berechnen();
        }
        bilanz.berechnen();
    }

    public void update()
    {
        kennzahlenRuntersetzen();
        berechnen();
        this.guv.importAufwandUndErlös(); // GuV updaten
        try {
            this.getBilanz().liquiditaetAnpassen(this.guv.getTaeglicheLiquiditaetsveraenderung());
        } catch (BankruptException e){
            e.printStackTrace();
        }
        if (Game.getCalendar().get(Calendar.DAY_OF_MONTH) == Game.getCalendar().getActualMaximum(Calendar.DAY_OF_MONTH)) {
            this.getGuv().archivieren();
        }

    }

    /**
     * setzt alle Kennzahlen bei jedem Timer Count um 0.1 herunter
     */
    private void kennzahlenRuntersetzen(){
        for (Kennzahl kennzahl : this.weicheKennzahlen.values()){
            kennzahl.setModifier(kennzahl.getModifier() - 0.01f);
        }
    }

    /**
     * wird am Jahresende aufgerufen, um Jahresabschluss (Bilanz + GuV) zu archivieren
     */
    public void archivieren(){
        Kennzahlensammlung jahresabschluss = new Kennzahlensammlung(this);
        this.archiv.put(Game.getCalendar().getTime(), jahresabschluss);
    }

    public void initWeicheKennzahlen(){
        weicheKennzahlen.put("mitarbeiterzufriedenheit",new Mitarbeiterzufriedenheit(unternehmen));
        weicheKennzahlen.put("kundenzufriedenheit",new Kundenzufriedenheit(unternehmen));
        weicheKennzahlen.put("image",new Image(unternehmen));
        weicheKennzahlen.put("bekanntheitsgrad", new Bekanntheitsgrad(unternehmen));
        weicheKennzahlen.put("verkaufswahrscheinlichkeit", new Verkaufswahrscheinlichkeit(unternehmen));
    }

    public Kennzahl getWeicheKennzahl(String kennzahl){
            return weicheKennzahlen.containsKey(kennzahl)?weicheKennzahlen.get(kennzahl):null;
    }


    // Getter und Setter:

    public  Kennzahl getMitarbeiterzufriedenheit() {
        return weicheKennzahlen.get("mitarbeiterzufriedenheit");
    }

    public double getMarktanteil() {
        return marktanteil;
    }

    public void setMarktanteil(double marktanteil) {
        this.marktanteil = marktanteil;
    }

    public Bilanz getBilanz() {
        return bilanz;
    }

    public GuV getGuv() {
        return guv;
    }

    public void setGuv(GuV guv) {
        this.guv = guv;
    }

    public int getMaxNeueMitarbeiter() {
        return maxNeueMitarbeiter;
    }

    public void setMaxNeueMitarbeiter(int maxNeueMitarbeiter) {
        this.maxNeueMitarbeiter = maxNeueMitarbeiter;
    }

    public Map<String, Kennzahl> getWeicheKennzahlen() {
        return weicheKennzahlen;
    }

    public void setBankrupt() {
        this.bankrupt = true;
    }

    public boolean isBankrupt()
    {
        return this.bankrupt;
    }

    public Map<Date, Kennzahlensammlung> getArchiv() {
        return archiv;
    }
}
