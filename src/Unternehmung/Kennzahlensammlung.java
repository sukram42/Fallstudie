package Unternehmung;

import Exceptions.BankruptException;
import Unternehmung.Kennzahlen.*;
import Unternehmung.Kennzahlen.Bilanz;
import Unternehmung.Kennzahlen.GuV;
import Unternehmung.Kennzahlen.Kennzahl;
import Unternehmung.Kennzahlen.Mitarbeiterzufriedenheit;

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
    // "weiche" Kennzahlensammlung:

    private Map<String,  Kennzahl> weicheKennzahlen = new HashMap<>();

    // faktische Kennzahlensammlung:
    private double marktanteil;
    private double ausschussrate;
    private double reklamationsrate;
    private Bekanntheitsgrad bekanntheitsgrad;

    private Bilanz bilanz;
    private transient GuV guv;

    private int maxNeueMitarbeiter; // abhängig von HR-Mitarbeitern (für 10 Mitarbeiter ist ein HR-Mitarbeiter (=Manager) zuständig)
                                    // wird pro neu eingestelltem HR-Mitarbeiter um 10 hochgesetzt
                                    // wird pro neu eingestelltem (Nicht-HR-) Mitarbeiter um 1 runtergesetzt

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
        this.bekanntheitsgrad = new Bekanntheitsgrad(unternehmen);
        this.maxNeueMitarbeiter = 0;
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
        berechnen();
        this.guv.importAufwandUndErlös(); // GuV updaten
        try {
            this.getBilanz().liquiditaetAnpassen(this.guv.getTaeglicheLiquiditätsveränderung());
        } catch (BankruptException e){
            e.printStackTrace();
        }
    }

    public void initWeicheKennzahlen(){
        weicheKennzahlen.put("mitarbeiterzufriedenheit",new Mitarbeiterzufriedenheit(unternehmen));
        weicheKennzahlen.put("kundenzufriedenheit",new Kundenzufriedenheit(unternehmen));
        weicheKennzahlen.put("image",new Image(unternehmen));
        weicheKennzahlen.put("produktqualität",new Produktqualität(unternehmen));
        weicheKennzahlen.put("verkaufswahrscheinlichkeit", new Verkaufswahrscheinlichkeit(unternehmen));
    }

    public Kennzahl getWeicheKennzahl(String kennzahl){
            return weicheKennzahlen.containsKey(kennzahl)?weicheKennzahlen.get(kennzahl):null;
    }


    // Getter und Setter:
    public Bekanntheitsgrad getBekanntheitsgrad() {
        return bekanntheitsgrad;
    }

    public  Kennzahl getMitarbeiterzufriedenheit() {
        return weicheKennzahlen.get("mitarbeiterzufriedenheit");
    }

    public double getMarktanteil() {
        return marktanteil;
    }

    public void setMarktanteil(double marktanteil) {
        this.marktanteil = marktanteil;
    }

    public double getAusschussrate() {
        return ausschussrate;
    }

    public void setAusschussrate(double ausschussrate) {
        this.ausschussrate = ausschussrate;
    }

    public double getReklamationsrate() {
        return reklamationsrate;
    }

    public void setReklamationsrate(double reklamationsrate) {
        this.reklamationsrate = reklamationsrate;
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
}
